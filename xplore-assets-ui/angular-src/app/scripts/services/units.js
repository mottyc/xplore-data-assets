'use strict';

/**
 * @ngdoc service
 * @name myApp.units
 * @description
 * # units
 * Provider in the arApp.
 */
angular.module('myApp')
    .provider('units',
        function () {
            var self = this;

            self.units = {

                sortTypes: [
                    {name: 'asc', value: 'Newest - Oldest'},
                    {name: 'desc', value: 'Oldest - Newest'}
                ],

                orderSortType: [
                    {name: 'orderKey', value: 'Order Number'},
                    {name: 'name', value: 'Name'},
                    {name: 'phase', value: 'Phase'},
                    {name: 'status', value: 'Status'},
                    {name: 'SubmittedOn', value: 'Date of submitting'}
                ],

                addressFloorLevel: [
                    {name: 'BASEMENT', value: 'Basement'},
                    {name: 'GROUND', value: 'Ground Floor'},
                    {name: '1FLOOR', value: '1st Floor'},
                    {name: '2FLOOR', value: '2nd Floor'},
                    {name: 'OTHER', value: 'Other'},
                    {name: 'UNKNOWN', value: 'Don\'t know'}
                ],

                addressParking: [
                    {name: 'NO_DEDICATED_PARKING', value: 'No dedicated parking'},
                    {name: 'LOADING_ZONE', value: 'Loading zone'},
                    {name: 'STREET_PARKING', value: 'Street parking'},
                    {name: 'PARKING_LOT', value: 'Parking lot 10m distance'}
                ],

                artItemSortType: [
                    {name: 'artItemKey', value: 'Key'},
                    {name: 'artName', value: 'Name'},
                    {name: 'creationYear', value: 'Year of creation'},
                    {name: 'artType', value: 'Type'}
                ],

                OrderImportancePreferences: [
                    {name: 'S', value: 'Service Level'},
                    {name: 'X', value: 'Experience on ArtRunners'},
                    {name: 'R', value: 'Average Rating'},
                    {name: 'C', value: 'Cost / Value'}
                ],

                // Default Currencies Types
                currencyDefaults: [
                    {name: 'DEFAULT', value: 'Default'},
                    {name: 'BOOKING', value: 'Booking'}
                ],

                // REGISTERED_CARD Type
                registeredCard: {name: 'REGISTERED_CARD', value: 'Registered card'}
            };

            this.$get = ['$q', 'ApiRequest', function ($q, ApiRequest) {
                var scope = this;
                return {
                    loadAll: function () {
                        var deferred = $q.defer();

                        ApiRequest.get('lookups', {}).then(
                            function (response) {

                                if (_.isArray(response.data.list)) {

                                    response.data.list.forEach(function (data) {

                                        scope.units[data.name] = data.entries;
                                        if (scope.units[data.name][0].name === 'UNDEFINED') {
                                            scope.units[data.name].splice(0, 1);
                                        }

                                    });

                                    deferred.resolve(scope.units);

                                } else {
                                    deferred.reject(response);
                                }

                            },
                            function () {
                                deferred.reject();
                            }
                        );

                        return deferred.promise;
                    },
                    loadCountries: function () {
                        var deferred = $q.defer();

                        ApiRequest.get('lookups/country', {}).then(
                            function (response) {

                                if (_.isArray(response.data.entity.entries)) {
                                    scope.units.countryList = response.data.entity.entries;

                                    if (scope.units.countryList[0].name === 'UNDEFINED') {
                                        scope.units.countryList.splice(0, 1);
                                    }

                                    deferred.resolve(scope.units);

                                } else {
                                    deferred.reject(response);
                                }

                            },
                            function () {
                                deferred.reject();
                            }
                        );

                        return deferred.promise;
                    },
                    loadCurrencies: function () {
                        var deferred = $q.defer();

                        ApiRequest.get('lookups/currency', {}).then(
                            function (response) {

                                if (_.isArray(response.data.entity.entries)) {

                                    scope.units.currencyList = response.data.entity.entries;
                                    if (scope.units.currencyList[0].name === 'UNDEFINED') {
                                        scope.units.currencyList.splice(0, 1);
                                    }

                                    deferred.resolve(scope.units);

                                } else {
                                    deferred.reject(response);
                                }

                            },
                            function () {
                                deferred.reject();
                            }
                        );

                        return deferred.promise;
                    },
                    getAllUnits: function() {
                        return scope.units;
                    }
                };

            }];

        });
