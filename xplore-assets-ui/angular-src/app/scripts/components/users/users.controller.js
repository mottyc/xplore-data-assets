/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('usersController', usersController);

    usersController.$inject = ["pfViewUtils", "usersManager"];

    function usersController(pfViewUtils, usersManager) {

        var self = this;

        self.allItems = [];
        self.items = [];
        self.totalItems = 0;

        self.currentPage = 1;
        self.totalPages = 1;

        self.querySort = "";
        self.queryFilters = [];

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.loadEntities = function () {

            // Validate range
            self.currentPage = Math.min(self.totalPages, self.currentPage);
            self.currentPage = Math.max(1, self.currentPage);

            if (self.queryFilters && self.queryFilters.length > 0) {
                usersManager.search(self.currentPage, self.querySort, self.queryFilters).then(self.postLoad);
            } else {
                usersManager.getAll(self.currentPage).then(self.postLoad);
            }

        };

        self.postLoad = function(result) {
            self.allItems = result.list;
            self.items = self.allItems;

            self.totalItems = result.count;
            self.currentPage = result.page;
            self.totalPages = result.pages;

            var str = "Page: " + self.currentPage + " / " + self.totalPages;
            self.filterConfig.resultsCount = str + ",      Total: " + self.totalItems + "";
        };

        // Navigation
        self.navStart = function () { self.currentPage = 1; self.loadEntities(); };
        self.navPrev = function () { self.currentPage -= 1; self.loadEntities(); };
        self.navNext = function () { self.currentPage += 1; self.loadEntities(); };
        self.navEnd = function () { self.currentPage = self.totalPages; self.loadEntities(); };

        var performAction = function(action, item) {
            console.debug("Action: " + action + " On: " + item);
        };
        // endregion

        // region --- Filters ------------------------------------------------------------------------------------------
        self.filtersText = '';

        var matchesFilter = function (item, filter) {
            var match = true;

            if (filter.id === 'name') {
                match = item.name.match(filter.value) !== null;
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
            self.items = [];
            if (filters && filters.length > 0) {
                self.allItems.forEach(function (item) {
                    if (matchesFilters(item, filters)) {
                        self.items.push(item);
                    }
                });
            } else {
                self.items = self.allItems;
            }
        };

        var filterChange = function (filters) {
            self.filtersText = "";
            filters.forEach(function (filter) {
                self.filtersText += filter.title + " : " + filter.value + "\n";
            });
            applyFilters(filters);
            self.toolbarConfig.filterConfig.resultsCount = self.items.length;
        };


        self.filterConfig = {
            fields: [
                {
                    id: 'name',
                    title:  'Name',
                    placeholder: 'Filter by Name...',
                    filterType: 'text'
                }
            ],
            resultsCount: self.items.length,
            appliedFilters: [],
            onFilterChange: filterChange
        };

        // endregion

        // region --- Toolbar config -----------------------------------------------------------------------------------
        var viewSelected = function(viewId) {
            self.viewType = viewId;
        };

        self.viewsConfig = {
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView(), pfViewUtils.getCardView()],
            onViewSelect: viewSelected
        };
        self.viewsConfig.currentView = self.viewsConfig.views[0].id;
        self.viewType = self.viewsConfig.currentView;

        var sortChange = function (sortId, isAscending) {
            console.debug("Sort: " + sortId + " - " + isAscending);
            self.loadEntities();
        };

        self.sortConfig = {
            fields: [
                {
                    id: 'name',
                    title:  'Name',
                    sortType: 'alpha'
                }
            ],
            onSortChange: sortChange
        };

        self.actionsText = "";

        self.actionsConfig = {
            primaryActions: [
                { name: '|<<', title: 'Start', actionFn: self.navStart },
                { name: '<', title: 'Prev', actionFn: self.navPrev },
                { name: '>', title: 'Next', actionFn: self.navNext },
                { name: '>>|', title: 'End', actionFn: self.navEnd }
            ],
            actionsInclude: true
        };

        self.toolbarConfig = {
            viewsConfig: self.viewsConfig,
            filterConfig: self.filterConfig,
            sortConfig: self.sortConfig,
            actionsConfig: self.actionsConfig
        };

        // endregion

        // region --- List Config --------------------------------------------------------------------------------------

        self.listConfig = {
            selectItems: false,
            multiSelect: false,
            dblClick: false,
            selectionMatchProp: 'columnKey',
            selectedItems: [],
            //checkDisabled: checkDisabledItem,
            showSelectBox: false,
            //onSelect: handleSelect,
            //onSelectionChange: handleSelectionChange,
            //onCheckBoxChange: handleCheckBoxChange,
            //onClick: handleClick,
            //onDblClick: handleDblClick
        };

        self.listActionButtons = [
            {
                name: 'Edit',
                title: 'Edit',
                actionFn: performAction
            },
            {
                name: 'Link',
                title: 'Link',
                actionFn: performAction
            }
        ];
        // endregion

        // region --- Card Config --------------------------------------------------------------------------------------

        self.cardConfig = {
            selectItems: false,
            multiSelect: false,
            dblClick: false,
            selectionMatchProp: 'columnKey',
            selectedItems: [],
            //checkDisabled: checkDisabledItem,
            showSelectBox: false,
            //onSelect: handleSelect,
            //onSelectionChange: handleSelectionChange,
            //onCheckBoxChange: handleCheckBoxChange,
            //onClick: handleClick,
            //onDblClick: handleDblClick
        };
        // endregion


        return self;
    }
})();
