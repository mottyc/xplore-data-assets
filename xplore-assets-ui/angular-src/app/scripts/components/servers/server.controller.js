/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('serverController', serverController);

    serverController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'serversManager', 'MdaServerModel'];

    function serverController($rootScope, $scope, $state, $stateParams, serversManager, MdaServerModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.server = new MdaServerModel();
        self.relatedSystems = [];
        self.relatedTables = [];
        
        // Get info
        serversManager
             .get(self.key)
             .then(function (result) {
                 self.server.setData(result.data.entity)
             });

        self.save = function () {
            self.server
                .save()
                .then(function (result) {
                    self.server.setData(result.data.entity)
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
