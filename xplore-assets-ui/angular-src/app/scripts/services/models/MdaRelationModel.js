/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Relation model
 * @name myApp.MdaRelationModel
 * @description
 * # MdaRelationModel
 * {
 *    relationKey: int,
 *    fullRelationName: string,
 *    columnKeyPar: int,
 *    columnKeyRef: int,
 *    dbName: string,
 *    schemaNamePar: string,
 *    tableNamePar: string,
 *    columnNamePar: string,
 *    schemaNameRef: string
 *    tableNameRef: string,
 *    columnNameRef: string,
 *    displayName: string,
 *    description: string,
 *    relationTypeCd: string,
 *    relationSource: string,
 *    relationScore: double,
 *    dxpColumnGkPar: int,
 *    tableId: string,
 *    dxpColumnGk: int,
 *    dxpTableGk: int,
 *    dxpColumnGkRef: int,
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *
 * }
 */
angular.module('myApp')
    .factory('MdaRelationModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaRelationModel(data) {

                this.relationKey = null;
                this.fullRelationName = null;
                this.columnKeyPar = null;
                this.columnKeyRef = null;
                this.dbName = null;
                this.schemaNamePar = null;
                this.tableNamePar = null;
                this.columnNamePar = null;
                this.schemaNameRef = null;
                this.tableNameRef = null;
                this.columnNameRef = null;
                this.displayName = null;
                this.description = null;
                this.relationTypeCd = null;
                this.relationSource = null;
                this.relationScore = null;
                this.dxpColumnGkPar = null;
                this.dxpColumnGkRef = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaRelationModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('relations/' + this.relationKey, {}, angular.toJson(this))
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

            return MdaRelationModel;
        }
    ]);
