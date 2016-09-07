/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('schemaController', schemaController);

    schemaController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'schemasManager'];

    function schemaController($rootScope, $scope, $state, $stateParams, schemasManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.schema = {};

        // Get info
        schemasManager
             .get(self.key)
             .then(function (result) {
                 self.schema = result.data.entity;
             });

        self.save = function () {
            schemasManager
                .save(self.schema)
                .then(function (result) {
                    self.schema = result.data.entity;
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
