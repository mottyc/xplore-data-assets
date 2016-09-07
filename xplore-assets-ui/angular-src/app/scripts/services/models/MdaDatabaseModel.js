/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Database model
 * @name myApp.MdaDatabaseModel
 * @description
 * # MdaDatabaseModel
 * {
 *    domainKey: int,
 *    fullDbName: string,
 *    serverKey: int,
 *    dbName: string,
 *    displayName: string,
 *    description: string,
 *    dbTypeCd: string,
 *    technicalOwnerUsernameKey: String,
 *    dxpConnectionId: int,
 *    schemas: [...MdaSchemaModel],
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaDatabaseModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaDatabaseModel(data) {
                this.domainKey = null;
                this.fullDbName = null;
                this.serverKey = null;
                this.dbName = null;
                this.displayName = null;
                this.description = null;

                this.dbTypeCd = null;
                this.technicalOwnerUsernameKey = null;
                this.dxpConnectionId = null;
                this.schemas = [];

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaDatabaseModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('databases/' + this.domainKey, {}, angular.toJson(this))
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

            return MdaDatabaseModel;
        }
    ]);
