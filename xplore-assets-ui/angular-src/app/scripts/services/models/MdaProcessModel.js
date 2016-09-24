/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Process model
 * @name myApp.MdaProcessModel
 * @description
 * # MdaProcessModel
 * {
 *    processKey: int,
 *    processName: string,
 *    displayName: string,
 *    description: string,
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *
 * }
 */
angular.module('myApp')
    .factory('MdaProcessModel', [
        '$q', 'ApiRequest',
        function ($q, ApiRequest) {
            var self = this;

            function MdaProcessModel(data) {
                this.processKey = null;
                this.processName = null;
                this.displayName = null;
                this.description = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaProcessModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                    return this;
                },

                save: function() {
                    var deferred = $q.defer();
                    ApiRequest
                        .put('processes/' + this.processKey, {}, angular.toJson(this))
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

            return MdaProcessModel;
        }
    ]);
