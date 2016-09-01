/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

'use strict';

/**
 * @ngdoc directive
 * @name myApp.directive:mainNavbar
 * @description
 * # mainNavbar
 */
angular.module('myApp')
    .directive('mainNavbar', function () {
        return {
            restrict: 'E',
            templateUrl: 'app/directives/mainNavbar/mainNavbar.html',
            controllerAs: 'mainNavbarCtrl',
            controller: [
                '$rootScope', '$scope', '$state', 'settings',
                function ($rootScope, $scope, $state, settings) {
                    var self = this;

                    self.notifications = {};
                    self.userName = settings.config().userName;
                    self.userAvatar = settings.config().userAvatar;

                    self.getFormatterDate = function (date) {
                        var sentOn = new Date(date);
                        return sentOn.toLocaleTimeString(settings.config().locale, settings.config().dateFormat);
                    };


                    self.goTo = function (state) {
                        $state.go(state);
                    };

                }
            ]
        };
    });
