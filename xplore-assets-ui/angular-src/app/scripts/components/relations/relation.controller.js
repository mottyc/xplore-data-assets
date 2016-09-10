/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('relationController', relationController);

    relationController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'relationsManager', 'MdaRelationModel', 'Notifications'];

    function relationController($rootScope, $scope, $state, $stateParams, relationsManager, MdaRelationModel, Notifications) {

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

        self.relation = new MdaRelationModel();

        // Get info
        relationsManager
             .get(self.key)
             .then(function (result) {
                 self.relation.setData(result.data.entity)
             });
        
        // Save changes
        self.saveChanges = function () {
            self.relation
                .save()
                .then(function (result) {
                    self.relation.setData(result.data.entity);
                    self.notifySuccess("Changes updated for relation: " + result.data.entity.relationKey);
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
