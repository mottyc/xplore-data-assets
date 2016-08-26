/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

(function () {
    'use strict';

    angular.module('myApp')
        .controller('usersController', usersController);

    usersController.$inject = ["$scope", "$q"];

    function usersController($scope, $q) {

        var self = this;

        self.Heading = "Users Page";
        self.Text = "This is a users list page.";

        return self;
    }
})();
