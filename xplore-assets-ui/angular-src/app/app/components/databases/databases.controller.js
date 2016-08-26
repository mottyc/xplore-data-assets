/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('databasesController', databasesController);

    databasesController.$inject = ["$scope", "$q"];

    function databasesController($scope, $q) {

        var self = this;

        self.Heading = "Database Page";
        self.Text = "This is a database list page.";

        return self;
    }
})();
