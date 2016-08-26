/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('tablesController', tablesController);

    tablesController.$inject = ["$scope", "$q"];

    function tablesController($scope, $q) {

        var self = this;

        self.Heading = "Tables Page";
        self.Text = "This is a tables list page.";

        return self;
    }
})();
