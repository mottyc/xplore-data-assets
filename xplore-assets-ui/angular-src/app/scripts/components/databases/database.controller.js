/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('databaseController', databaseController);

    databaseController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'databasesManager', 'MdaDatabaseModel'];

    function databaseController($rootScope, $scope, $state, $stateParams, databasesManager, MdaDatabaseModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.database = new MdaDatabaseModel();

        // Get info
        databasesManager
             .get(self.key)
             .then(function (result) {
                 self.database.setData(result.data.entity)
             });

        self.save = function () {
            self.database
                .save()
                .then(function (result) {
                    self.database.setData(result.data.entity)
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
