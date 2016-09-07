/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('systemController', systemController);

    systemController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'systemsManager'];

    function systemController($rootScope, $scope, $state, $stateParams, systemsManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.system = {};
        self.relatedServers = [];
        self.relatedEntities = [];

        // Get info
        systemsManager
             .get(self.key)
             .then(function (result) {
                 self.system = result.data.entity;
             });

        self.save = function () {
            systemsManager
                .save(self.server)
                .then(function (result) {
                    self.system = result.data.entity;
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
