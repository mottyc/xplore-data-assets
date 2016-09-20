/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entityController', entityController);

    entityController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'entitiesManager', 'MdaBusinessEntityModel', 'Notifications', '$mdDialog', 'ngDialog'];

    function entityController($rootScope, $scope, $state, $stateParams, entitiesManager, MdaBusinessEntityModel, Notifications, $mdDialog, ngDialog) {

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
            /***
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
             ***/
            // ngDialog.open({ template: 'views/components/tables/tables.dialog.html', className: 'ngdialog-theme-default' });
            ngDialog.open({
                template: 'views/components/tables/tables.dialog.html',
                controller: 'tablesDialogController',
                controllerAs: 'ctrl',
                width: '60%',
                height: '40%'

            });
        }

        self.linkTables = function(event) {

            /***
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

             ***/
            self.open();
        }
        // endregion

        // region --- Modal dialog -------------------------------------------------------------------------------------

        self.additionalInfo = "Donec consequat dignissim neque, sed suscipit quam egestas in. Fusce bibendum " +
            "laoreet lectus commodo interdum. Vestibulum odio ipsum, tristique et ante vel, iaculis placerat nulla. " +
            "Suspendisse iaculis urna feugiat lorem semper, ut iaculis risus tempus.";
        self.copyright = "Trademark and Copyright Information";
        self.imgAlt = "Patternfly Symbol";
        self.imgSrc = "img/logo-alt.svg";
        self.title = "Product Title";
        self.productInfo = [
            { name: 'Version', value: '1.0.0.0.20160819142038_51be77c' },
            { name: 'Server Name', value: 'Localhost' },
            { name: 'User Name', value: 'admin' },
            { name: 'User Role', value: 'Administrator' }];
        self.open = function () {
            self.isOpen = true;
        }
        self.onClose = function() {
            self.isOpen = false;
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
