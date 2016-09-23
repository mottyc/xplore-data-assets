/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('systemController', systemController);

    systemController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'systemsManager', 'MdaSystemModel', 'Notifications'];

    function systemController($rootScope, $scope, $state, $stateParams, systemsManager, MdaSystemModel, Notifications) {

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

        self.system = new MdaSystemModel();
        self.relatedServers = [];
        self.relatedEntities = [];

        // Get info
        systemsManager
             .get(self.key)
             .then(function (result) {
                 self.system.setData(result.data.entity)
             });


        // Save changes
        self.saveChanges = function () {
            self.system
                .save()
                .then(function (result) {
                    self.system.setData(result.data.entity);
                    self.notifySuccess("Changes updated for system: " + result.data.entity.systemKey);
                });
        };

        self.loadRelatedServers = function() {
            systemsManager
                .getRelatedServers(self.key)
                .then(function (result) {
                    self.relatedServers = result.list;
                });
        };

        self.loadRelatedEntities = function() {
            systemsManager
                .getRelatedEntities(self.key)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };

        // Link servers
        self.linkServers = function(serversKeys) {
            systemsManager
                .linkServers(self.key, serversKeys)
                .then(function (result) {
                    self.relatedServers = result.list;
                });
        };

        // Unlink servers
        self.unlinkServers = function(serversKeys) {
            systemsManager
                .unlinkServers(self.key, serversKeys)
                .then(function (result) {
                    self.relatedServers = result.list;
                });
        };

        // Link entities
        self.linkEntities = function(entitiesKeys) {
            systemsManager
                .linkEntities(self.key, entitiesKeys)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };

        // Unlink entities
        self.unlinkEntities = function(entitiesKeys) {
            systemsManager
                .unlinkEntities(self.key, entitiesKeys)
                .then(function (result) {
                    self.relatedEntities = result.list;
                });
        };
        // endregion

        // region --- Link Servers Modal dialog -------------------------------------------------------------------------

        self.linkServersTitle = "Link Servers";

        self.openLinkServersDialog = function() {
            self.isOpenServersDlg = true;
        };

        self.onCloseServersDlg = function() {
            self.isOpenServersDlg = false;
        };

        self.onSaveServersDlg = function(selectedItems) {
            self.linkServers(selectedItems);
            self.isOpenServersDlg = false;
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

        // region --- Tabs config --------------------------------------------------------------------------------------
        self.tabId = "info";
        self.tabSelected = function(tab_id) {
            if (tab_id === "servers") {
                self.loadRelatedServers();
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
