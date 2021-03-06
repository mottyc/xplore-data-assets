'use strict';

/**
 * @ngdoc Systems data manager
 * @name myApp.systemsManager
 * @description Systems data manager
 */
angular.module('myApp')
    .factory('systemsManager', [
        '$q', 'ApiRequest', 'MdaSystemModel',
        function ($q, ApiRequest, MdaSystemModel) {

            var resourceName = "systems";
            
            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new MdaSystemModel(data);
                        this._collection[key] = instance;
                    }

                    return instance;
                },

                //endregion

                //region Public methods

                // Get MdaSystemModel by ID
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
                                /**
                                var list = [];

                                response.data.list.forEach(function (data) {
                                    var item = scope._retrieveInstance(data.systemKey, data);
                                    list.push(item);
                                });

                                deferred.resolve(list);**/
                                deferred.resolve(response.data);

                            } catch (e) {
                                deferred.reject(e);
                            }

                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Load related servers
                getRelatedServers: function(key) {
                    var deferred = $q.defer();
                    ApiRequest
                        .get(resourceName + '/' + key + '/servers', {})
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

                // Load related servers
                getRelatedEntities: function(key) {
                    var deferred = $q.defer();
                    ApiRequest
                        .get(resourceName + '/' + key + '/entities', {})
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
                    var item = this.get(data.systemKey);
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
                        .put(resourceName + '/' + data.systemKey, {}, data)
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Link servers to system
                linkServers: function (systemKey, serversKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + systemKey + '/servers', {}, serversKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Unlink servers from system
                unlinkServers: function (systemKey, serversKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .delete(resourceName + '/' + systemKey + '/servers', {}, serversKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Link entities to system
                linkEntities: function (systemKey, entitiesKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + systemKey + '/entities', {}, entitiesKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Unlink entities from system
                unlinkEntities: function (systemKey, entitiesKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .delete(resourceName + '/' + systemKey + '/entities', {}, entitiesKeys)
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
