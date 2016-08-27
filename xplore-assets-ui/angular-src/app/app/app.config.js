'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngAnimate',
    'ngMessages',
    'ngResource',
    'ui.router',
    'ui.bootstrap',
    'underscore',
    'patternfly',
    'patternfly.charts'
])
    .constant('CONFIG', {
        DebugMode: true,
        StepCounter: 0,
        APIHost: 'http://localhost:8080/api/v0/'
    })

    .config(['settingsProvider', function (settingsProvider) {
        settingsProvider.setConfigs();
        }])

    .config(function ($httpProvider, settingsProvider) {
        $httpProvider.defaults.headers.common = {};
        $httpProvider.defaults.headers.post = {};
        $httpProvider.defaults.headers.put = {};
        $httpProvider.defaults.headers.patch = {};
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json; charset=utf-8';
        $httpProvider.defaults.headers.common['X-Access-Token'] = settingsProvider.config.token;
    })
    
    .run(function () {
        /**
         * Function to format currencies.
         * @param n The number of zeros after the given value (xxx.n)
         * @param x The number of digits between the delimiters
         * @returns {string}
         */
        Number.prototype.formatCurrency = function (n, x) {
            var re = '\\d(?=(\\d{' + (x || 3) + '})+' + (n > 0 ? '\\.' : '$') + ')';
            return this.toFixed(Math.max(0, ~~n)).replace(new RegExp(re, 'g'), '$&,');
        };
    })

;