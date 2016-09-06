/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('databaseController', databaseController);

    databaseController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'databasesManager'];

    function databaseController($rootScope, $scope, $state, $stateParams, databasesManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.database = {};

        // Get info
        databasesManager
             .get(self.key)
             .then(function (result) {
                 self.database = result.data.entity;
             });

        self.save = function () {
            databasesManager
                .set(self.database)
                .then(function (result) {
                    self.database = result.data.entity;
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
