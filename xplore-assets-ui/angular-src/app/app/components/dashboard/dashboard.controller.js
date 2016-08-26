/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('dashboardController', dashboardController);

    dashboardController.$inject = ["$scope", "$q"];

    function dashboardController($scope, $q) {

        var self = this;

        self.Heading = "Dashboard Page";
        self.Text = "This is a dashboard page.";

        return self;
    }
})();