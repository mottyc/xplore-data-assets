/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('tableController', tableController);

    tableController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'tablesManager'];

    function tableController($rootScope, $scope, $state, $stateParams, tablesManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.table = {};
        self.relatedEntities = [];
        

        // Get info
        tablesManager
             .get(self.key)
             .then(function (result) {
                 self.table = result.data.entity;
             });

        self.save = function () {
            tablesManager
                .set(self.table)
                .then(function (result) {
                    self.table = result.data.entity;
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
