'use strict';

/**
 * @ngdoc service
 * @name myApp.ApiRequest
 * @description
 * # apiRequest
 * Factory in the myApp.
 */
angular.module('myApp')
    .factory('ApiRequest', [
        '$http', '$q', 'settings',
        function ($http, $q, settings) {
            var self = this;
            self.apiHost = settings.config().apiHost;

            return {

                /**
                 * GET Request
                 * @param url
                 * @param params
                 * @returns {Function}
                 */
                get: function (url, params) {
                    var q = $q.defer();

                    $http({
                        url: self.apiHost + url,
                        method: 'GET',
                        params: params,
                        data: ''
                    })
                        .then(function (result) {
                            q.resolve(result);
                        }, function (error) {
                            q.reject(error);
                        });

                    return q.promise;
                },

                /**
                 * POST Request
                 * @param url
                 * @param params
                 * @param data
                 * @returns {Function}
                 */
                post: function (url, params, data) {
                    var q = $q.defer();

                    $http({
                        url: self.apiHost + url,
                        method: 'POST',
                        params: params,
                        data: data
                    })
                        .then(function (result) {
                            q.resolve(result);
                        }, function (error) {
                            q.reject(error);
                        });

                    return q.promise;
                },

                /**
                 *
                 * @param url
                 * @param params
                 * @param data
                 * @returns {Function}
                 */
                put: function (url, params, data) {
                    var q = $q.defer();

                    $http({
                        url: self.apiHost + url,
                        method: 'PUT',
                        params: params,
                        data: data
                    })
                        .then(function (result) {
                            q.resolve(result);
                        }, function (error) {
                            q.reject(error);
                        });

                    return q.promise;
                },

                /**
                 *
                 * @param url
                 * @param params
                 * @param data
                 * @returns {Function}
                 */
                delete: function (url, params, data) {
                    var q = $q.defer();

                    $http({
                        url: self.apiHost + url,
                        method: 'DELETE',
                        params: params,
                        data: data
                    })
                        .then(function (result) {
                            q.resolve(result);
                        }, function (error) {
                            q.reject(error);
                        });

                    return q.promise;
                },

                upload: function (url, data, params) {
                    var q = $q.defer();

                    $http({
                        url: self.apiHost + url,
                        method: 'POST',
                        params: params,
                        data: data,
                        transformRequest: angular.identity,
                        headers: {
                            'Content-Type': undefined
                        }
                    })
                        .then(function (result) {
                            q.resolve(result);
                        }, function (error) {
                            q.reject(error);
                        });

                    return q.promise;
                }

            };
        }
    ]);
