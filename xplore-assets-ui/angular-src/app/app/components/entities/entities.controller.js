/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('entitiesController', entitiesController);

    entitiesController.$inject = ["$scope", "$q"];

    function entitiesController($scope, $q) {

        var self = this;

        self.Heading = "Entities Page";
        self.Text = "This is a entities list page.";

        return self;
    }
})();
