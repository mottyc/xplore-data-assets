/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('systemController', systemController);

    systemController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'systemsManager', 'MdaSystemModel'];

    function systemController($rootScope, $scope, $state, $stateParams, systemsManager, MdaSystemModel) {

        var self = this;
        self.key = $stateParams.key;

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

        self.save = function () {
            self.system
                .save()
                .then(function (result) {
                    self.system.setData(result.data.entity)
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
