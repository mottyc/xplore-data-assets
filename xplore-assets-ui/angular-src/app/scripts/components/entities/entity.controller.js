/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entityController', entityController);

    entityController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'entitiesManager', 'MdaBusinessEntityModel', 'Notifications'];

    function entityController($rootScope, $scope, $state, $stateParams, entitiesManager, MdaBusinessEntityModel, Notifications) {

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

        self.save = function () {
            self.entity
                .save()
                .then(function (result) {
                    self.entity.setData(result.data.entity);
                    self.notifySuccess("Data saved");
                });
        };
        
        self.saveChanges = function(data, item) {
            console.debug("Data: " + data + ", Item: " + item);
            
            var update = new MdaBusinessEntityModel();
            update.setData(item)
                .save()
                .then(function (result) {
                    if (result.status == 200) {
                        if (result.data.code == 0) {
                            self.notifySuccess("Changes updated for entity: " + item.businessEntityKey);
                        } else {
                            self.notifyWarning(result.data.error);
                        }
                    } else {
                        self.notifyError(result.statusText);
                    }
                });
        }
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
