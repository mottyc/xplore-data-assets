/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('tablesDialogController', tablesDialogController);

    tablesDialogController.$inject = ['tablesManager', 'MdaTableModel'];

    function tablesDialogController(tablesManager, MdaTableModel) {

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
                tablesManager.search(self.currentPage, self.querySort, self.queryFilters).then(self.postLoad);
            } else {
                tablesManager.getAll(self.currentPage).then(self.postLoad);
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
                { id: 'tableName', title:  'Name', placeholder: 'Filter by table name...', filterType: 'text' },
                { id: 'tableNameDisplay', title:  'Display Name', placeholder: 'Filter by display name...', filterType: 'text' },
                { id: 'tableDesc', title:  'Description', placeholder: 'Filter by description...', filterType: 'text' }
            ],
            resultsCount: self.items.length,
            appliedFilters: [],
            onFilterChange: filterChange
        };

        // endregion

        // region --- Sorting ------------------------------------------------------------------------------------------

        var sortChange = function (sortId, isAscending) {
            self.querySort = sortId + ":" + (isAscending) ? "asc" : "desc";
            self.loadEntities();
        };

        self.sortConfig = {
            fields: [
                { id: 'tableName', title:  'Name', sortType: 'alpha' },
                { id: 'tableNameDisplay', title:  'Display Name', sortType: 'alpha' }
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

        // region --- List Config --------------------------------------------------------------------------------------

        self.listConfig = {
            selectItems: true,
            multiSelect: true,
            dblClick: false,
            selectedItems: [],
            checkDisabled: false,
            showSelectBox: false,
            //onSelect: handleSelect,
            //onSelectionChange: handleSelectionChange,
            //onCheckBoxChange: handleCheckBoxChange,
            //onClick: handleClick,
            //onDblClick: handleDblClick
        };
        
        // endregion


        self.isDisabled = false;
        self.noCache = true;

        // list of `state` value/display objects
        self.states       = [];
        self.query        = [];
        self.selectedItem = null;

        self.newState = function (state) {
            alert("Sorry! You'll need to create a Constitution for " + state + " first!");
        };

        // ******************************
        // Internal methods
        // ******************************

        /**
         * Search for states... use $timeout to simulate
         * remote dataservice call.
         */
        self.querySearch = function (query) {
            console.info('querySearch of: ' + query);

            // var results = query ? self.states.filter( self.createFilterFor(query) ) : self.states, deferred;

            self.query = query ? self.states.filter( self.createFilterFor(query) ) : self.states;
            console.info('results: ' + JSON.stringify(self.query));
        };

        self.searchTextChange = function (text) {
            console.info('Text changed to: ' + text);
            self.querySearch(text);
        };

        self.selectedItemChange = function (item) {
            // console.info('Item changed to: ' + JSON.stringify(item));
        };

        /**
         * Build `states` list of key/value pairs
         */
        self.loadAll = function() {
            var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
              Wisconsin, Wyoming';

            self.states = allStates.split(/, +/g).map( function (state) {
                return {
                    value: state.toLowerCase(),
                    display: state
                };
            });
        };

        /**
         * Create filter function for a query string
         */
        self.createFilterFor = function (query) {
            var lowercaseQuery = angular.lowercase(query);

            return function filterFn(state) {
                return (state.value.indexOf(lowercaseQuery) === 0);
            };
        };

        //self.loadEntities();
        self.loadAll();
        return self;
    }
})();
