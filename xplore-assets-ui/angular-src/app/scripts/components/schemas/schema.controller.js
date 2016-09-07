/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('schemaController', schemaController);

    schemaController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'schemasManager', 'MdaSchemaModel'];

    function schemaController($rootScope, $scope, $state, $stateParams, schemasManager, MdaSchemaModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.schema = new MdaSchemaModel();

        // Get info
        schemasManager
             .get(self.key)
             .then(function (result) {
                 self.schema.setData(result.data.entity)
             });

        self.save = function () {
            self.schema
                .save()
                .then(function (result) {
                    self.schema.setData(result.data.entity)
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
