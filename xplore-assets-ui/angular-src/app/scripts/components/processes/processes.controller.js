/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('processesController', processesController);

    processesController.$inject = ['pfViewUtils', 'processesManager', 'MdaProcessModel', 'Notifications'];

    function processesController(pfViewUtils, processesManager, MdaProcessModel, Notifications) {

        var self = this;

        self.items = [];
        self.totalItems = 0;

        self.currentPage = 1;
        self.totalPages = 1;

        self.querySort = "";
        self.queryFilters = [];

        // region --- Notifications ------------------------------------------------------------------------------------

        self.showClose = true;

        self.handleClose = function (data) { Notifications.remove(data); };
        self.updateViewing = function (viewing, data) { Notifications.setViewing(data, viewing); };
        self.notifySuccess = function (message) { Notifications.message ('success', '', message, false); }
        self.notifyWarning = function (message) { Notifications.message ('warning', '', message, false); }
        self.notifyError = function (message) { Notifications.message ('danger', '', message, false); }

        self.notifications = Notifications.data;
        // endregion

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.loadEntities = function () {

            // Validate range
            self.currentPage = Math.min(self.totalPages, self.currentPage);
            self.currentPage = Math.max(1, self.currentPage);

            if (self.queryFilters && self.queryFilters.length > 0) {
                processesManager.search(self.currentPage, self.querySort, self.queryFilters).then(self.postLoad);
            } else {
                processesManager.getAll(self.currentPage).then(self.postLoad);
            }

        };

        self.postLoad = function(result) {

            self.items = result.list;
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
        
        self.saveChanges = function(data, item) {
            var update = new MdaProcessModel();
            update.setData(item)
                .save()
                .then(function (result) {
                    if (result.status == 200) {
                        if (result.data.code == 0) {
                            self.notifySuccess("Changes updated for process: " + item.processKey);
                        } else {
                            self.notifyWarning(result.data.error);
                        }
                    } else {
                        self.notifyError(result.statusText);
                    }
                });
        };
        
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
                { id: 'processName', title:  'Name', placeholder: 'Filter by process name...', filterType: 'text' },
                { id: 'processNameDisplay', title:  'Display Name', placeholder: 'Filter by display name...', filterType: 'text' },
                { id: 'processDesc', title:  'Description', placeholder: 'Filter by description...', filterType: 'text' }
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
                { id: 'processName', title:  'Name', sortType: 'alpha' },
                { id: 'processNameDisplay', title:  'Display Name', sortType: 'alpha' }
            ],
            onSortChange: sortChange
        };

        // endregion
        
        // region --- Toolbar config -----------------------------------------------------------------------------------
        var viewSelected = function(viewId) {
            self.viewType = viewId;
        };

        self.viewsConfig = {
            views: [pfViewUtils.getTableView(), pfViewUtils.getListView()],
            onViewSelect: viewSelected
        };
        self.viewsConfig.currentView = self.viewsConfig.views[0].id;
        self.viewType = self.viewsConfig.currentView;
        
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
            actionsConfig: self.actionsConfig
        };

        // endregion

        // region --- List Config --------------------------------------------------------------------------------------

        self.listConfig = {
            selectItems: false,
            multiSelect: false,
            dblClick: false,
            selectionMatchProp: 'processKey',
            selectedItems: [],
            showSelectBox: false
        };

        // endregion

        // region --- Card Config --------------------------------------------------------------------------------------

        self.cardConfig = {
            selectItems: false,
            multiSelect: false,
            dblClick: false,
            selectionMatchProp: 'processKey',
            selectedItems: [],
            showSelectBox: false
        };
        // endregion

        self.loadEntities();
        return self;
    }
})();
