'use strict';

/**
 * @ngdoc service
 * @name mypp.databasesManager
 * @description Databases data manager
 */
angular.module('myApp')
    .factory('databasesManager', [
        '$q', 'ApiRequest', 'MdaDatabaseModel',
        function ($q, ApiRequest, MdaDatabaseModel) {

            var resourceName = "databases";
            
            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new MdaDatabaseModel(data);
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
                getAll: function () {
                    var deferred = $q.defer();
                    var scope = this;

                    ApiRequest
                        .get(resourceName, {})
                        .then(function (response) {
                            try {
                                var list = [];

                                response.data.list.forEach(function (data) {
                                    var item = scope._retrieveInstance(data.domainKey, data);
                                    list.push(item);
                                });

                                deferred.resolve(list);

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
                    var scope = this;
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
                                var list = [];

                                response.data.list.forEach(function (data) {
                                    var item = scope._retrieveInstance(data.domainKey, data);
                                    list.push(item);
                                });

                                deferred.resolve(list);

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
                    var item = this._search(data.domainKey);
                    if (item) {
                        item.setData(data);
                    } else {
                        item = scope._retrieveInstance(data);
                    }
                    return item;
                }

                //endregion
            };

        }
    ]);
