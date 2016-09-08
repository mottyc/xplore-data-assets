/*
 * Copyright (c) 2016. xplore.io Ltd.
 *
 */

'use strict';

/**
 * @ngdoc directive
 * @name myApp.directive:dashboardCard
 * @description
 */
angular.module('myApp')
    .directive('dashboardCard', function () {
        return {
            restrict: 'E',
            templateUrl: 'views/directives/cards/dashboardCard.html',
            scope: {
                title: '@',
                image: '@',
                count: '=',
                state: "@"
            },
            controllerAs: 'ctrl',
            controller: [ '$scope', '$state', '$element',
                function ($scope, $$state, element) {
                    var self = this;

                    self.title = "Title"
                    self.count = "12";

                    self.goTo = function (state) {
                        $state.go(state);
                    };

                }
            ]
        };
    });