/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entityController', entityController);

    entityController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'entitiesManager', 'MdaBusinessEntityModel', 'Notifications', '$mdDialog'];

    function entityController($rootScope, $scope, $state, $stateParams, entitiesManager, MdaBusinessEntityModel, Notifications, $mdDialog) {

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

        self.entity = new MdaBusinessEntityModel();
        self.relatedSystems = [];
        self.relatedTables = [];

        // Get info
        entitiesManager
             .get(self.key)
             .then(function (result) {
                 self.entity.setData(result.data.entity)
             });

        self.saveChanges = function () {
            self.entity
                .save()
                .then(function (result) {
                    self.entity.setData(result.data.entity);
                    self.notifySuccess("Changes updated for entity: " + result.data.entity.businessEntityKey);
                });
        };

        self.loadRelatedSystems = function() {
            entitiesManager
                .getRelatedSystems(self.key)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        self.loadRelatedTables = function() {
            entitiesManager
                .getRelatedTables(self.key)
                .then(function (result) {
                    self.relatedTables = result.list;
                });
        };

        self.linkTables = function(tablesKeys) {
            entitiesManager
                .linkTables(self.key, tablesKeys)
                .then(function (result) {

                    console.log("Refresh tables");
                    console.log(angular.toJson(result));

                    self.relatedTables = result.list;
                    //self.loadRelatedTables();
                });
        };

        self.unlinkTables = function(tablesKeys) {
            entitiesManager
                .unlinkTables(self.key, tablesKeys)
                .then(function (result) {
                    self.relatedTables = result.list;
                });
        };

        self.linkSystems = function(systemsKeys) {
            entitiesManager
                .linkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        self.unlinkSystems = function(systemsKeys) {
            entitiesManager
                .unlinkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        // endregion

        // region --- Link Tables Modal dialog -------------------------------------------------------------------------

        self.linkTablesTitle = "Link Tables";

        self.openLinkTablesDialog = function() {
            self.isOpenTablesDlg = true;
        };

        self.unLinkSelectedTables = function() {
            var confirm = $mdDialog.confirm()
                .title('Unlink Tables')
                .textContent('Unlink selected tables from this entity?')
                .ariaLabel('')
                .ok('Yes')
                .cancel('Cancel');

            $mdDialog
                .show(confirm)
                .then(function () {
                    self.unlinkTables(self.selectedTables);
                    self.selectedTables = [];
                });
        };

        self.onCloseTablesDlg = function() {
            self.isOpenTablesDlg = false;
        };

        self.onSaveTablesDlg = function(selectedItems) {
            console.log("On save Items: " + selectedItems);
            self.linkTables(selectedItems);
            self.isOpenTablesDlg = false;
        };

        self.selectedTables = [];

        self.tableChecked = function(event) {
            var index = self.selectedTables.indexOf(event.item.tableKey);

            if ((event.item.isChecked == true) && (index < 0)) {
                self.selectedTables.push(event.item.tableKey);
            }

            if ((event.item.isChecked == false) && (index > -1)) {
                self.selectedTables.splice(index, 1);
            }
        };
        // endregion

        // region --- Link Systems Modal dialog ------------------------------------------------------------------------

        self.linkSystemsTitle = "Link Systems";

        self.openLinkSystemsDialog = function() {
            self.isOpenSystemsDlg = true;
        };

        self.unLinkSelectedSystems = function() {
            var confirm = $mdDialog.confirm()
                .title('Unlink Systems')
                .textContent('Unlink selected systems from this entity?')
                .ariaLabel('')
                .ok('Yes')
                .cancel('Cancel');

            $mdDialog
                .show(confirm)
                .then(function () {
                    self.unlinkSystems(self.selectedSystems);
                    self.selectedSystems = [];
                });
        };

        self.onCloseSystemsDlg = function() {
            self.isOpenSystemsDlg = false;
        };

        self.onSaveSystemsDlg = function(selectedItems) {
            console.log("On save Items: " + selectedItems);
            self.linkSystems(selectedItems);
            self.isOpenSystemsDlg = false;
        };


        self.selectedSystems = [];

        self.systemChecked = function(event) {
            var index = self.selectedSystems.indexOf(event.item.systemKey);

            if ((event.item.isChecked == true) && (index < 0)) {
                self.selectedSystems.push(event.item.systemKey);
            }

            if ((event.item.isChecked == false) && (index > -1)) {
                self.selectedSystems.splice(index, 1);
            }
        };
        // endregion

        // region --- Tabs config --------------------------------------------------------------------------------------
        self.tabId = "info";
        self.tabSelected = function(tab_id) {
            if (tab_id === "systems") {
                self.loadRelatedSystems();
            }
            if (tab_id === "tables") {
                self.loadRelatedTables();
            }
            self.tabId = tab_id;
        }; 
        // endregion

        return self;
    }
})();
