/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('userController', userController);

    userController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'usersManager', 'Notifications'];

    function userController($rootScope, $scope, $state, $stateParams, usersManager, Notifications) {

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

        self.user = {};

        // Get info
        usersManager
             .get(self.key)
             .then(function (result) {
                 self.user = result.data.entity;
             });

        self.save = function () {
            usersManager
                .set(self.user)
                .then(function (result) {
                    self.user = result.data.entity;
                });
        };

        // Save changes
        self.saveChanges = function () {
            self.user
                .save()
                .then(function (result) {
                    self.user.setData(result.data.entity);
                    self.notifySuccess("Changes updated for user: " + result.data.entity.usernameKey);
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
