/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('columnController', columnController);

    columnController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'columnsManager'];

    function columnController($rootScope, $scope, $state, $stateParams, columnsManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.column = {};
        
        // Get info
        columnsManager
             .get(self.key)
             .then(function (result) {
                 self.column = result.data.entity;
             });

        self.save = function () {
            columnsManager
                .get(self.column)
                .then(function (result) {
                    self.column = result.data.entity;
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
