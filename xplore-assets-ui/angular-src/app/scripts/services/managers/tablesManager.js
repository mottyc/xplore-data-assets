'use strict';

/**
 * @ngdoc Tables data manager
 * @name myApp.tablesManager
 * @description Tables data manager
 */
angular.module('myApp')
    .factory('tablesManager', [
        '$q', 'ApiRequest', 'MdaTableModel',
        function ($q, ApiRequest, MdaTableModel) {

            var resourceName = "tables";
            
            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new MdaTableModel(data);
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
                    var item = this.get(data.tableKey);
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
                        .put(resourceName + '/' + data.tableKey, {}, data)
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                }

                //endregion
            };

        }
    ]);
