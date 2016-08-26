/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('relationsController', relationsController);

    relationsController.$inject = ["$scope", "$q"];

    function relationsController($scope, $q) {

        var self = this;

        self.Heading = "Relations Page";
        self.Text = "This is a relations list page.";

        return self;
    }
})();
