/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('serverController', serverController);

    serverController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'serversManager'];

    function serverController($rootScope, $scope, $state, $stateParams, serversManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.server = {};
        self.relatedSystems = [];
        self.relatedTables = [];
        
        // Get info
        serversManager
             .get(self.key)
             .then(function (result) {
                 self.server = result.data.entity;
             });

        self.save = function () {
            serversManager
                .set(self.server)
                .then(function (result) {
                    self.server = result.data.entity;
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
