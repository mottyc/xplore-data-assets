'use strict';

/**
 * @ngdoc Servers data manager
 * @name myApp.serversManager
 * @description Servers data manager
 */
angular.module('myApp')
    .factory('serversManager', [
        '$q', 'ApiRequest', 'MdaRelationModel',
        function ($q, ApiRequest, MdaServerModel) {

            var resourceName = "servers";
            
            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new MdaServerModel(data);
                        this._collection[key] = instance;
                    }

                    return instance;
                },

                //endregion

                //region Public methods

                // Get item by ID
                get: function (key) {
                    var deferred = $q.defer();

                    ApiRequest
                        .get(resourceName + '/' + key, {})
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Load all items
                getAll: function (currentPage) {
                    var deferred = $q.defer();

                    ApiRequest
                        .get(resourceName, {
                            page: currentPage
                        })
                        .then(function (response) {
                            try {
                                deferred.resolve(response.data);
                            } catch (e) {
                                deferred.reject(e);
                            }

                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Load related systems
                getRelatedSystems: function(key) {
                    var deferred = $q.defer();
                    ApiRequest
                        .get(resourceName + '/' + key + '/systems', {})
                        .then(function (response) {
                            try {
                                deferred.resolve(response.data);
                            } catch (e) {
                                deferred.reject(e);
                            }

                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },                
                
                // Search all items
                search: function (currentPage, sort, filters) {
                    var deferred = $q.defer();

                    ApiRequest
                        .post(resourceName + '/search', {
                            page: currentPage,
                            sort: sort
                        }, filters)
                        .then(function (response) {
                            try {
                                deferred.resolve(response.data);
                            } catch (e) {
                                deferred.reject(e);
                            }

                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },
                
                // Search items
                find: function (search) {
                    var deferred = $q.defer();
                    var currentSearch = search || null;

                    if (currentSearch === null) {
                        deferred.reject();
                    }

                    ApiRequest
                        .get(resourceName, {
                            search: currentSearch
                        })
                        .then(function (response) {

                            try {
                                deferred.resolve(response.data);
                            } catch (e) {
                                deferred.reject(e);
                            }

                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Save/Update item and get an its instance in return
                set: function (data) {
                    var scope = this;
                    var item = this.get(data.serverKey);
                    if (item) {
                        item.setData(data);
                    } else {
                        item = scope._retrieveInstance(data);
                    }
                    return item;
                },

                // Save item and get an its instance in return
                save: function (data) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + data.serverKey, {}, data)
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Link systems to server
                linkSystems: function (serverKey, systemsKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + serverKey + '/systems', {}, systemsKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Unlink systems from server
                unlinkSystems: function (serverKey, systemsKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .delete(resourceName + '/' + serverKey + '/systems', {}, systemsKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                }
                //endregion
            };

        }
    ]);
