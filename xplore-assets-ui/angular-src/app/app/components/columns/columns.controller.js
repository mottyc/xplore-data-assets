/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('columnsController', columnsController);

    columnsController.$inject = ["$scope", "$q"];

    function columnsController($scope, $q) {

        var self = this;

        self.Heading = "Columns Page";
        self.Text = "This is a columns list page.";

        return self;
    }
})();
