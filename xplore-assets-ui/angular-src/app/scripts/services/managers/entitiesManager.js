'use strict';

/**
 * @ngdoc Entities data manager
 * @name myApp.entitiesManager
 * @description Entities data manager
 */
angular.module('myApp')
    .factory('entitiesManager', [
        '$q', 'ApiRequest', 'MdaBusinessEntityModel',
        function ($q, ApiRequest, MdaBusinessEntityModel) {

            var resourceName = "entities";

            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new MdaBusinessEntityModel(data);
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
                
                // Load related systems
                getRelatedTables: function(key) {
                    var deferred = $q.defer();
                    ApiRequest
                        .get(resourceName + '/' + key + '/tables', {})
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
                    var item = this.get(data.businessEntityKey);
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
                        .put(resourceName + '/' + data.businessEntityKey, {}, data)
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Link tables to entity
                linkTables: function (businessEntityKey, tablesKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + businessEntityKey + '/tables', {}, tablesKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Unlink tables from entity
                unlinkTables: function (businessEntityKey, tablesKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .delete(resourceName + '/' + businessEntityKey + '/tables', {}, tablesKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Link systems to entity
                linkSystems: function (businessEntityKey, systemsKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .put(resourceName + '/' + businessEntityKey + '/systems', {}, systemsKeys)
                        .then(function (response) {
                            deferred.resolve(response.data);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },

                // Unlink systems from entity
                unlinkSystems: function (businessEntityKey, systemsKeys) {
                    var deferred = $q.defer();

                    ApiRequest
                        .delete(resourceName + '/' + businessEntityKey + '/systems', {}, systemsKeys)
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
