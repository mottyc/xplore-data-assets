/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('databasesController', databasesController);

    databasesController.$inject = ["$scope", "$q"];

    function databasesController($scope, $q) {

        var self = this;

        // region --- Data (Items) -------------------------------------------------------------------------------------

        $scope.allItems = [
            {
                name: "Active Directory",
                key: 1,
                address: "20 Dinosaur Way, Bedrock, Washingstone",
                birthMonth: 'February'
            },
            {
                name: "Analizer",
                key: 2,
                address: "415 East Main Street, Norfolk, Virginia",
                birthMonth: 'October'
            },
            {
                name: "BRIO",
                key: 3,
                address: "234 Elm Street, Pittsburgh, Pennsylvania",
                birthMonth: 'March'
            },
            {
                name: "Citrix",
                key: 4,
                address: "2 Apple Boulevard, Cincinatti, Ohio",
                birthMonth: 'December'
            },
            {
                name: "CMS",
                key: 5,
                address: "50 Second Street, New York, New York",
                birthMonth: 'February'
            }
        ];
        $scope.items = $scope.allItems;
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
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView(), pfViewUtils.getCardView()],
            onViewSelect: viewSelected
        };
        $scope.viewsConfig.currentView = $scope.viewsConfig.views[1].id;
        $scope.viewType = $scope.viewsConfig.currentView;

        var sortChange = function (sortId, isAscending) {
            //$scope.items.sort(compareFn);
            self.loadEntities();
            console.debug("sortChange: " + sortId + " ASC?:" + isAscending)
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
                    title: 'Add system',
                    actionFn: performAction
                }
            ],
            moreActions: [
                {
                    name: 'Action',
                    title: 'Perform an action',
                    actionFn: performAction
                },
                {
                    name: 'Another Action',
                    title: 'Do something else',
                    actionFn: performAction
                },
                {
                    name: 'Disabled Action',
                    title: 'Unavailable action',
                    actionFn: performAction,
                    isDisabled: true
                },
                {
                    name: 'Something Else',
                    title: '',
                    actionFn: performAction
                },
                {
                    isSeparator: true
                },
                {
                    name: 'Grouped Action 1',
                    title: 'Do something',
                    actionFn: performAction
                },
                {
                    name: 'Grouped Action 2',
                    title: 'Do something similar',
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

        self.loadEntities = function () {
            systemsManager.getAll().then(function (result) {
                $scope.allItems = result;
                $scope.items = $scope.allItems;
                console.debug("Load success");
                console.debug($scope.items);
            })
        };

        return self;
    }
})();
