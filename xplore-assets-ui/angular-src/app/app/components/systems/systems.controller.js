/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('systemsController', systemsController);

    systemsController.$inject = ["$scope", 'pfViewUtils', 'systemsManager'];

    function systemsController($scope, pfViewUtils, systemsManager) {

        var self = this;
        
        $scope.totalItems = 0;
        $scope.allItems = [];
        $scope.items = [];

        // region --- Data Handlers ------------------------------------------------------------------------------------

        $scope.loadEntities = function () {
            systemsManager.getAll().then(function (result) {
                $scope.allItems = result;
                $scope.items = $scope.allItems;
                $scope.totalItems = $scope.items.length;
                $scope.filterConfig.resultsCount = $scope.totalItems;

                console.debug("Load suceess with " + $scope.totalItems + " Items");
            })
        };

        // endregion


        // region --- Event handlers -----------------------------------------------------------------------------------

        $scope.eventText = '';
        var handleSelect = function (item, e) {
            $scope.eventText = item.name + ' selected\r\n' + $scope.eventText;
        };

        var handleSelectionChange = function (selectedItems, e) {
            $scope.eventText = selectedItems.length + ' items selected\r\n' + $scope.eventText;
        };

        var handleClick = function (item, e) {
            $scope.eventText = item.name + ' clicked\r\n' + $scope.eventText;
        };

        var handleDblClick = function (item, e) {
            $scope.eventText = item.name + ' double clicked\r\n' + $scope.eventText;
        };

        var handleCheckBoxChange = function (item, selected, e) {
            $scope.eventText = item.name + ' checked: ' + item.selected + '\r\n' + $scope.eventText;
        };

        var checkDisabledItem = function(item) {
            return $scope.showDisabled && (item.name === "John Smith");
        };

        $scope.enableButtonForItemFn = function(action, item) {
            return (action.name !=='Action 2') || (item.name !== "Frank Livingston");
        };

        $scope.updateMenuActionForItemFn = function(action, item) {
            if (action.name === 'Another Action') {
                action.isVisible = (item.name !== "John Smith");
            }
        };
        // endregion

        // region --- List View config ---------------------------------------------------------------------------------
        $scope.selectType = 'none'; //'checkbox';
        $scope.updateSelectionType = function() {
            if ($scope.selectType === 'checkbox') {
                $scope.listConfig.selectItems = false;
                $scope.listConfig.showSelectBox = true;
            } else if ($scope.selectType === 'row') {
                $scope.listConfig.selectItems = true;
                $scope.listConfig.showSelectBox = false;
            } else {
                $scope.listConfig.selectItems = false
                $scope.listConfig.showSelectBox = false;
            }

            console.debug("Selection changed: " + $scope.selectType);

        };

        $scope.showDisabled = false;

        $scope.listConfig = {
            selectItems: false,
            multiSelect: false,
            dblClick: false,
            selectionMatchProp: 'name',
            selectedItems: [],
            checkDisabled: checkDisabledItem,
            showSelectBox: true,
            onSelect: handleSelect,
            onSelectionChange: handleSelectionChange,
            onCheckBoxChange: handleCheckBoxChange,
            onClick: handleClick,
            onDblClick: handleDblClick
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
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView(), pfViewUtils.getCardView()],
            onViewSelect: viewSelected
        };
        $scope.viewsConfig.currentView = $scope.viewsConfig.views[1].id;
        $scope.viewType = $scope.viewsConfig.currentView;

        var sortChange = function (sortId, isAscending) {
            //$scope.items.sort(compareFn);
            $scope.loadEntities();
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
