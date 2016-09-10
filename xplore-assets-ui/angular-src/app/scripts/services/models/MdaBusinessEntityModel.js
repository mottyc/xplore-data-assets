/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Business entity model
 * @name myApp.MdaBusinessEntityModel
 * @description
 * # MdaBusinessEntity
 * {
 *    businessEntityKey: int,
 *    businessEntityName: string,
 *    displayName: string,
 *    description: string,
 *    
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 * }
 */
angular.module('myApp')
    .factory('MdaBusinessEntityModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaBusinessEntityModel(data) {
                this.businessEntityKey = null;
                this.businessEntityName = null;
                this.displayName = null;
                this.description = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaBusinessEntityModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                    return this;
                },
                
                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('entities/' + this.businessEntityKey, {}, angular.toJson(this))
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

            return MdaBusinessEntityModel;
        }
    ]);
