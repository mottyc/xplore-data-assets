/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Table model
 * @name myApp.MdaTableModel
 * @description
 * # MdaTableModel
 * {
 *    tableKey: int,
 *    fullTableName: string,
 *    schemaKey: int,
 *    dbName: string,
 *    schemaName: string,
 *    tableName: string,
 *    displayName: string,
 *    description: string,
 *    tableRowCount: int,
 *    tableNumOfCols: int,
 *    objectType: string,
 *    tablePk: string,
 *    tableId: int,
 *    dxpTableGk: int,
 *
 *    columns: [...MdaColumnModel],
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaTableModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaTableModel(data) {
                this.tableKey = null;
                this.fullTableName = null;
                this.schemaKey = null;
                this.dbName = null;
                this.schemaName = null;
                this.tableName = null;
                this.displayName = null;
                this.description = null;
                this.tableRowCount = null;
                this.tableNumOfCols = null;
                this.objectType = null;
                this.tablePk = null;
                this.tableId = null;
                this.dxpTableGk = null;

                this.columns = [];

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaTableModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('tables/' + this.tableKey, {}, angular.toJson(this))
                        .then(function (response) {
                            if (response.data.error !== null) {
                                deferred.reject(response);
                            }
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });
                    return deferred.promise;
                }
            };

            return MdaTableModel;
        }
    ]);
