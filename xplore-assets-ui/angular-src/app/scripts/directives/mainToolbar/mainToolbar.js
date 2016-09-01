/*
 * Copyright (c) 2016. ArtRunners Ltd.
 *
 */

'use strict';

/**
 * @ngdoc directive
 * @name arApp.directive:mainToolbar
 * @description
 * # mainToolbar
 */
angular.module('myApp')
    .directive('mainToolbar', function () {
        return {
            restrict: 'E',
            templateUrl: 'app/directives/mainToolbar/mainToolbar.html',
            controllerAs: 'mainToolbarCtrl',
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
