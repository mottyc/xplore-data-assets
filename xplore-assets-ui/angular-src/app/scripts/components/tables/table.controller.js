/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('tableController', tableController);

    tableController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'tablesManager', 'MdaTableModel'];

    function tableController($rootScope, $scope, $state, $stateParams, tablesManager, MdaTableModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.table = new MdaTableModel();
        self.relatedEntities = [];
        

        // Get info
        tablesManager
             .get(self.key)
             .then(function (result) {
                 self.table.setData(result.data.entity)
             });

        self.save = function () {
            self.table
                .save()
                .then(function (result) {
                    self.table.setData(result.data.entity)
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
