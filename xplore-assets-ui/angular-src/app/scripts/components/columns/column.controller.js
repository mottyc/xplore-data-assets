/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('columnController', columnController);

    columnController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'columnsManager', 'MdaColumnModel'];

    function columnController($rootScope, $scope, $state, $stateParams, columnsManager, MdaColumnModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.column = new MdaColumnModel();
        
        // Get info
        columnsManager
             .get(self.key)
             .then(function (result) {
                 self.column.setData(result.data.entity)
             });

        self.save = function () {
            self.column
                .save()
                .then(function (result) {
                    self.column.setData(result.data.entity)
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
