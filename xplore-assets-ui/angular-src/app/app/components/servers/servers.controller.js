/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('serversController', serversController);

    serversController.$inject = ["$scope", "$q"];

    function serversController($scope, $q) {

        var self = this;

        self.Heading = "Servers Page";
        self.Text = "This is a servers list page.";

        return self;
    }
})();
