/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Column model
 * @name myApp.MdaColumnModel
 * @description
 * # MdaColumnModel
 * {
 *    columnKey: int,
 *    fullColumnName: string,
 *    tableKey: int,
 *    dbName: string,
 *    schemaName: string,
 *    tableName: string,
 *    columnName: string,
 *    displayName: string,
 *    description: string,
 *    columnDataType: string,
 *    isPk: boolean,
 *    columnPkSource: string,
 *    columnId: string,
 *    tableId: string,
 *    dxpColumnGk: int,
 *    dxpTableGk: int,
 *    
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *
 * }
 */
angular.module('myApp')
    .factory('MdaColumnModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaColumnModel(data) {

                this.columnKey = null;
                this.fullColumnName = null;
                this.tableKey = null;
                this.dbName = null;
                this.schemaName = null;
                this.tableName = null;
                this.columnName = null;
                this.displayName = null;
                this.description = null;
                this.columnDataType = null;
                this.isPk = null;
                this.columnPkSource = null;
                this.columnId = null;
                this.tableId = null;
                this.dxpColumnGk = null;
                this.dxpTableGk = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaColumnModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('columns/' + this.columnKey, {}, angular.toJson(this))
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

            return MdaColumnModel;
        }
    ]);
