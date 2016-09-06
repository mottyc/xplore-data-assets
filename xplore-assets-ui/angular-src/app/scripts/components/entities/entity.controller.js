/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entityController', entityController);

    entityController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'entitiesManager'];

    function entityController($rootScope, $scope, $state, $stateParams, entitiesManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.entity = {};
        self.relatedSystems = [];
        self.relatedTables = [];

        // Get info
        entitiesManager
             .get(self.key)
             .then(function (result) {
                 self.database = result.data.entity;
             });

        self.save = function () {
            entitiesManager
                .set(self.entity)
                .then(function (result) {
                    self.entity = result.data.entity;
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
