/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc System model
 * @name myApp.MdaSystemModel
 * @description
 * # MdaSystemModel
 * {
 *    systemKey: int,
 *    systemName: string,
 *    appName: string,
 *    displayName: string,
 *    description: string,
 *    systemDepartment: string,
 *    
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaSystemModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaSystemModel(data) {
                this.systemKey = null;
                this.systemName = null;
                this.appName = null;
                this.displayName = null;
                this.description = null;
                this.systemDepartment = null;
                
                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaSystemModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                    return this;
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('systems/' + this.systemKey, {}, angular.toJson(this))
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

            return MdaSystemModel;
        }
    ]);
