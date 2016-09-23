/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('selectServersController', selectServersController);

    selectServersController.$inject = ['$scope', '$modalInstance', 'content', 'serversManager'];

    function selectServersController($scope, $modalInstance, content, serversManager) {

        $scope.template = content;
        $scope.close = function () {
            $modalInstance.close();
        };
        $scope.$watch(
            function () {
                return $scope.isOpen;
            },
            function (newValue) {
                if (newValue === false) {
                    $modalInstance.close();
                }
            }
        );

        var self = this;

        self.allItems = [];
        self.items = [];
        self.selectedItems = [];
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
                serversManager.search(self.currentPage, self.querySort, self.queryFilters).then(self.postLoad);
            } else {
                serversManager.getAll(self.currentPage).then(self.postLoad);
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


        self.saveChanges = function() {
            $scope.save({selectedItems: self.selectedItems});
            $scope.close();
        }

        // endregion

        // region --- Filters ------------------------------------------------------------------------------------------
        var filterChange = function (filters) {

            self.queryFilters = [];

            filters.forEach(function (filter) {
                self.queryFilters.push({field:filter.id, value:filter.value});
            });

            self.loadEntities();
        };

        self.filterConfig = {
            fields: [
                { id: 'serverName', title:  'Name', placeholder: 'Filter by server name...', filterType: 'text' },
                { id: 'serverNameDisplay', title:  'Display Name', placeholder: 'Filter by display name...', filterType: 'text' }
            ],
            resultsCount: self.items.length,
            appliedFilters: [],
            onFilterChange: filterChange
        };

        self.itemChanged = function(event) {
            var index = self.selectedItems.indexOf(event.item.serverKey);

            if ((event.item.isChecked == true) && (index < 0)) {
                self.selectedItems.push(event.item.serverKey);
            }

            if ((event.item.isChecked == false) && (index > -1)) {
                self.selectedItems.splice(index, 1);
            }

            console.log(self.selectedItems);

        };

        // endregion

        // region --- Sorting ------------------------------------------------------------------------------------------

        var sortChange = function (sortId, isAscending) {
            self.querySort = sortId + ":" + (isAscending) ? "asc" : "desc";
            self.loadEntities();
        };

        self.sortConfig = {
            fields: [
                { id: 'serverName', title:  'Name', sortType: 'alpha' },
                { id: 'serverNameDisplay', title:  'Display Name', sortType: 'alpha' },
                { id: 'serverTypeCd', title:  'Type', sortType: 'alpha' }
            ],
            onSortChange: sortChange
        };

        // endregion

        // region --- Toolbar config -----------------------------------------------------------------------------------

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
            filterConfig: self.filterConfig,
            actionsConfig: self.actionsConfig
        };

        // endregion

        self.loadEntities();
        return self;
    }
})();
