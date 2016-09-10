/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('columnController', columnController);

    columnController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'columnsManager', 'MdaColumnModel', 'Notifications'];

    function columnController($rootScope, $scope, $state, $stateParams, columnsManager, MdaColumnModel, Notifications) {

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

        self.column = new MdaColumnModel();
        
        // Get info
        columnsManager
             .get(self.key)
             .then(function (result) {
                 self.column.setData(result.data.entity)
             });

        // Save changes
        self.saveChanges = function () {
            self.column
                .save()
                .then(function (result) {
                    self.column.setData(result.data.entity);
                    self.notifySuccess("Changes updated for column: " + result.data.entity.columnKey);
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
