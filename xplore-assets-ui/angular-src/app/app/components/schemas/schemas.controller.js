/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('schemasController', schemasController);

    schemasController.$inject = ["$scope", "$q"];

    function schemasController($scope, $q) {

        var self = this;

        self.Heading = "Schema Page";
        self.Text = "This is a schema list page.";

        return self;
    }
})();
