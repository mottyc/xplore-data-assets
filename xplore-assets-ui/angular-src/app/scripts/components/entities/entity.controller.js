/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entityController', entityController);

    entityController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'entitiesManager', 'MdaBusinessEntityModel', 'Notifications', '$mdDialog', '$mdMedia'];

    function entityController($rootScope, $scope, $state, $stateParams, entitiesManager, MdaBusinessEntityModel, Notifications, $mdDialog, $mdMedia) {

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

        self.linkSystems = function(event) {
            var confirm = $mdDialog.confirm()
                .title('Confirmation')
                .textContent('Lint systems to Entity?')
                .ariaLabel('')
                .targetEvent(event)
                .ok('Yes')
                .cancel('Cancel');

            $mdDialog
                .show(confirm)
                .then(function () {
                    console.debug("Confirmation accepted");
                });
        }

        self.linkTables = function(event) {

            $mdDialog
                .show({
                    controller: 'tablesDialogController',
                    controllerAs: 'ctrl',
                    bindToController: true,
                    templateUrl: 'views/components/tables/tables.dialog.html',
                    parent: angular.element(document.body),
                    targetEvent: event,
                    clickOutsideToClose: true,
                    fullscreen: $mdMedia('sm')
                })
                .then(function () {
                    //self.search();
                });
        }
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
