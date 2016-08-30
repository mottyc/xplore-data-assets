/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('serversController', serversController);

    serversController.$inject = ["$scope", "pfViewUtils", "serversManager"];

    function serversController($scope, pfViewUtils, serversManager) {

        var self = this;

        self.allItems = [];
        self.items = [];
        self.totalItems = 0;

        self.currentPage = 1;
        self.totalPages = 1;

        self.querySort = "";
        self.queryFilter = "";

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.loadEntities = function (page) {
            serversManager.getAll(page).then(function (result) {
                self.allItems = result.list;
                self.items = self.allItems;

                self.totalItems = result.count;
                self.currentPage = result.page;
                self.totalPages = result.pages;

                var str = "Page: " + self.currentPage + " / " + self.totalPages;
                self.filterConfig.resultsCount = str + ",  Total: " + self.totalItems + "";
            })
        };

        // Navigation
        self.navStart = function () { self.loadEntities(1); }
        self.navPrev = function () { self.loadEntities(self.currentPage - 1); }
        self.navNext = function () { self.loadEntities(self.currentPage + 1); }
        self.navEnd = function () { self.loadEntities(self.totalPages); }
        // endregion

        // region --- Filters ------------------------------------------------------------------------------------------
        self.filtersText = '';

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
            self.viewType = viewId
        };

        self.viewsConfig = {
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView()],
            onViewSelect: viewSelected
        };
        self.viewsConfig.currentView = self.viewsConfig.views[0].id;
        self.viewType = self.viewsConfig.currentView;

        var sortChange = function (sortId, isAscending) {
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
        var performAction = function (action) {
            self.actionsText = action.name + "\n" + self.actionsText;
        };

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

        return self;
    }
})();
