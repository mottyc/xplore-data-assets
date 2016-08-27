'use strict';

/**
 * @ngdoc service
 * @name mypp.systemsManager
 * @description Systems data manager
 */
angular.module('myApp')
    .factory('systemsManager', [
        '$q', 'ApiRequest', 'MdaSystemModel',
        function ($q, ApiRequest, MdaSystemModel) {

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
                        .get('systems/' + key, {})
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
                        .get('systems', {})
                        .then(function (response) {
                            try {
                                var list = [];

                                response.data.list.forEach(function (data) {
                                    var item = scope._retrieveInstance(data.systemKey, data);
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
                        .get('systems', {
                            search: currentSearch
                        })
                        .then(function (response) {

                            try {
                                var list = [];

                                response.data.list.forEach(function (data) {
                                    var item = scope._retrieveInstance(data.systemKey, data);
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
                    var item = this._search(data.systemKey);
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
