/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('systemsController', systemsController);

    systemsController.$inject = ["$scope", "$q"];

    function systemsController($scope, $q) {

        var self = this;

        self.Heading = "Systems Page";
        self.Text = "This is a systems list page.";

        return self;
    }
})();
