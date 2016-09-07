/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('relationController', relationController);

    relationController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'relationsManager', 'MdaRelationModel'];

    function relationController($rootScope, $scope, $state, $stateParams, relationsManager, MdaRelationModel) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.relation = new MdaRelationModel();

        // Get info
        relationsManager
             .get(self.key)
             .then(function (result) {
                 self.relation.setData(result.data.entity)
             });

        self.save = function () {
            self.relation
                .save()
                .then(function (result) {
                    self.relation.setData(result.data.entity)
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
