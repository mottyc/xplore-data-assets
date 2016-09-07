'use strict';

/**
 * @ngdoc service
 * @name myApp.dashboardManager
 * @description Dashboard data manager
 */
angular.module('myApp')
    .factory('dashboardManager', [
        '$q', 'ApiRequest', 'StatisticsModel',
        function ($q, ApiRequest, StatisticsModel) {

            var resourceName = "dashboard";
            
            return {

                //region Private methods & properties

                _collection: {},
                _retrieveInstance: function (key, data) {
                    var instance = this._collection[key];

                    if (instance) {
                        instance.setData(data);
                    } else {
                        instance = new StatisticsModel(data);
                        this._collection[key] = instance;
                    }

                    return instance;
                },

                //endregion

                //region Public methods

                // Get statistics
                getStatistics: function () {
                    var deferred = $q.defer();

                    ApiRequest
                        .get(resourceName + '/', {})
                        .then(function (response) {
                            deferred.resolve(response);
                        }, function (error) {
                            deferred.reject(error);
                        });

                    return deferred.promise;
                },


                //endregion
            };

        }
    ]);
