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
        // endregion
        
        // region --- Tabs config --------------------------------------------------------------------------------------
        self.tabId = "info";
        self.tabSelected = function(tab_id) {
            self.tabId = tab_id;
        }; 
        // endregion

        return self;
    }
})();
