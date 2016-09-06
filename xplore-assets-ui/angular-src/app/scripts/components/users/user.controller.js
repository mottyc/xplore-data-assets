/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('userController', userController);

    userController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'usersManager'];

    function userController($rootScope, $scope, $state, $stateParams, usersManager) {

        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.user = {};

        // Get info
        usersManager
             .get(self.key)
             .then(function (result) {
                 self.user = result.data.entity;
             });

        self.save = function () {
            usersManager
                .set(self.user)
                .then(function (result) {
                    self.user = result.data.entity;
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
