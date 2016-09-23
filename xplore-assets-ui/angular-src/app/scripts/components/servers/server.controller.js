/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('serverController', serverController);

    serverController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'serversManager', 'MdaServerModel', 'Notifications'];

    function serverController($rootScope, $scope, $state, $stateParams, serversManager, MdaServerModel, Notifications) {

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

        self.server = new MdaServerModel();
        self.relatedSystems = [];
        
        // Get info
        serversManager
             .get(self.key)
             .then(function (result) {
                 self.server.setData(result.data.entity)
             });
        
        // Save changes
        self.saveChanges = function () {
            self.server
                .save()
                .then(function (result) {
                    self.server.setData(result.data.entity);
                    self.notifySuccess("Changes updated for server: " + result.data.entity.serverKey);
                });
        };

        self.loadRelatedSystems = function() {
            serversManager
                .getRelatedSystems(self.key)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        self.linkSystems = function(systemsKeys) {
            serversManager
                .linkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
        };

        self.unlinkSystems = function(systemsKeys) {
            serversManager
                .unlinkSystems(self.key, systemsKeys)
                .then(function (result) {
                    self.relatedSystems = result.list;
                });
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
            self.tabId = tab_id;
        }; 
        // endregion

        return self;
    }
})();
