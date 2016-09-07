/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('dashboardController', dashboardController);

    dashboardController.$inject = ['$rootScope', '$scope', '$state', '$stateParams', 'dashboardManager'];

    function dashboardController($rootScope, $scope, $state, $stateParams, dashboardManager) {
        var self = this;
        self.key = $stateParams.key;

        // region --- Data Handlers ------------------------------------------------------------------------------------

        self.statistics = {};

        // Get info
        dashboardManager
            .getStatistics()
            .then(function (result) {
                self.statistics = result.data.entity;
            });

        // endregion

        return self;
    }
    
})();