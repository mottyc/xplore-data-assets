/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('tableController', tableController);

    tableController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'tablesManager', 'MdaTableModel', 'Notifications'];

    function tableController($rootScope, $scope, $state, $stateParams, tablesManager, MdaTableModel, Notifications) {

        var self = this;
        self.key = $stateParams.key;

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

        self.table = new MdaTableModel();
        self.relatedEntities = [];
        self.relatedSystems = [];
        

        // Get info
        tablesManager
             .get(self.key)
             .then(function (result) {
                 self.table.setData(result.data.entity)
             });
        
        // Save changes
        self.saveChanges = function () {
            self.table
                .save()
                .then(function (result) {
                    self.table.setData(result.data.entity);
                    self.notifySuccess("Changes updated for table: " + result.data.entity.tableKey);
                });
        };

        self.loadRelatedSystems = function() {
            tablesManager
                .getRelatedSystems(self.key)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        self.loadRelatedEntities = function() {
            tablesManager
                .getRelatedEntities(self.key)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };

        // Link entities
        self.linkEntities = function(entitiesKeys) {
            tablesManager
                .linkEntities(self.key, entitiesKeys)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };

        // Unlink entities
        self.unlinkEntities = function(entitiesKeys) {
            tablesManager
                .unlinkEntities(self.key, entitiesKeys)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };

        // Link systems
        self.linkSystems = function(systemsKeys) {
            tablesManager
                .linkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        // Unlink systems
        self.unlinkSystems = function(systemsKeys) {
            tablesManager
                .unlinkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };
        // endregion

        // region --- Link Entities Modal dialog ------------------------------------------------------------------------

        self.linkEntitiesTitle = "Link Entities";

        self.openLinkEntitiesDialog = function() {
            self.isOpenEntitiesDlg = true;
        };

        self.onCloseEntitiesDlg = function() {
            self.isOpenEntitiesDlg = false;
        };

        self.onSaveEntitiesDlg = function(selectedItems) {
            self.linkEntities(selectedItems);
            self.isOpenEntitiesDlg = false;
        };

        // endregion

        // region --- Link Systems Modal dialog ------------------------------------------------------------------------

        self.linkSystemsTitle = "Link Systems";

        self.openLinkSystemsDialog = function() {
            self.isOpenSystemsDlg = true;
        };

        self.onCloseSystemsDlg = function() {
            self.isOpenSystemsDlg = false;
        };

        self.onSaveSystemsDlg = function(selectedItems) {
            self.linkSystems(selectedItems);
            self.isOpenSystemsDlg = false;
        };

        // endregion

        // region --- Tabs config --------------------------------------------------------------------------------------
        self.tabId = "info";
        self.tabSelected = function(tab_id) {
            if (tab_id === "systems") {
                self.loadRelatedSystems();
            }
            if (tab_id === "entities") {
                self.loadRelatedEntities();
            }
            self.tabId = tab_id;
        }; 
        // endregion

        return self;
    }
})();
