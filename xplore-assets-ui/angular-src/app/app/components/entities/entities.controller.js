/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entitiesController', entitiesController);

    entitiesController.$inject = ["$scope", "pfViewUtils", "entitiesManager"];

    function entitiesController($scope, pfViewUtils, entitiesManager) {

        var self = this;

        $scope.allItems = [];
        $scope.items = [];
        $scope.totalItems = 0;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        $scope.loadEntities = function () {
            entitiesManager.getAll().then(function (result) {
                $scope.allItems = result;
                $scope.items = $scope.allItems;
                $scope.totalItems = $scope.items.length;
                $scope.filterConfig.resultsCount = $scope.totalItems;

                console.debug("Load success with " + $scope.totalItems + " Items");
            })
        };

        // endregion

        // region --- Filters ------------------------------------------------------------------------------------------
        $scope.filtersText = '';

        var matchesFilter = function (item, filter) {
            var match = true;

            if (filter.id === 'name') {
                match = item.name.match(filter.value) !== null;
            } else if (filter.id === 'age') {
                match = item.age === parseInt(filter.value);
            } else if (filter.id === 'address') {
                match = item.address.match(filter.value) !== null;
            } else if (filter.id === 'birthMonth') {
                match = item.birthMonth === filter.value;
            }
            return match;
        };

        var matchesFilters = function (item, filters) {
            var matches = true;

            filters.forEach(function(filter) {
                if (!matchesFilter(item, filter)) {
                    matches = false;
                    return false;
                }
            });
            return matches;
        };

        var applyFilters = function (filters) {
            $scope.items = [];
            if (filters && filters.length > 0) {
                $scope.allItems.forEach(function (item) {
                    if (matchesFilters(item, filters)) {
                        $scope.items.push(item);
                    }
                });
            } else {
                $scope.items = $scope.allItems;
            }
        };

        var filterChange = function (filters) {
            $scope.filtersText = "";
            filters.forEach(function (filter) {
                $scope.filtersText += filter.title + " : " + filter.value + "\n";
            });
            applyFilters(filters);
            $scope.toolbarConfig.filterConfig.resultsCount = $scope.items.length;
        };


        $scope.filterConfig = {
            fields: [
                {
                    id: 'name',
                    title:  'Name',
                    placeholder: 'Filter by Name...',
                    filterType: 'text'
                },
                {
                    id: 'age',
                    title:  'Age',
                    placeholder: 'Filter by Age...',
                    filterType: 'text'
                },
                {
                    id: 'address',
                    title:  'Address',
                    placeholder: 'Filter by Address...',
                    filterType: 'text'
                },
                {
                    id: 'birthMonth',
                    title:  'Birth Month',
                    placeholder: 'Filter by Birth Month...',
                    filterType: 'select',
                    filterValues: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
                }
            ],
            resultsCount: $scope.items.length,
            appliedFilters: [],
            onFilterChange: filterChange
        };

        // endregion

        // region --- Toolbar config -----------------------------------------------------------------------------------
        var viewSelected = function(viewId) {
            $scope.viewType = viewId
        };

        $scope.viewsConfig = {
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView()],
            onViewSelect: viewSelected
        };
        $scope.viewsConfig.currentView = $scope.viewsConfig.views[0].id;
        $scope.viewType = $scope.viewsConfig.currentView;

        var sortChange = function (sortId, isAscending) {
            //$scope.items.sort(compareFn);
            $scope.loadEntities();
        };

        $scope.sortConfig = {
            fields: [
                {
                    id: 'name',
                    title:  'Name',
                    sortType: 'alpha'
                },
                {
                    id: 'age',
                    title:  'Age',
                    sortType: 'numeric'
                },
                {
                    id: 'address',
                    title:  'Address',
                    sortType: 'alpha'
                },
                {
                    id: 'birthMonth',
                    title:  'Birth Month',
                    sortType: 'alpha'
                }
            ],
            onSortChange: sortChange
        };

        $scope.actionsText = "";
        var performAction = function (action) {
            $scope.actionsText = action.name + "\n" + $scope.actionsText;
            console.debug("Action: " + action);
        };

        $scope.actionsConfig = {
            primaryActions: [
                {
                    name: 'Add',
                    title: 'Add entity',
                    actionFn: performAction
                }
            ],
            actionsInclude: true
        };

        $scope.toolbarConfig = {
            viewsConfig: $scope.viewsConfig,
            filterConfig: $scope.filterConfig,
            sortConfig: $scope.sortConfig,
            actionsConfig: $scope.actionsConfig
        };

        // endregion



        return self;
    }
})();
