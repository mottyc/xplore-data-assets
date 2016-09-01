'use strict';

/**
 * @ngdoc service
 * @name myApp.settings
 * @description
 * # settings
 * Provider in the myApp.
 */
angular.module('myApp')
    .provider('settings', function () {
        var self = this;

        // Private variables
        // self.accountKey = 2;
        // self.itemsPerPage = 10;
        // self.notificationsPerPage = 15;
        // self.defaultSortType = 'asc';
        // self.orderPhase = 'OPEN'; // default order phase code
        // self.orderStatus = 'UNDEFINED';
        // self.orderSort = {
        //     key: 'orderKey',
        //     asc: true
        // };
        // self.orderSearch = null;
        // self.dateTimeFormat = 'YYYY-MM-DDThh:mm:ss+00:00'; // default date/time format
        // self.shipmentDateFormat = 'YYYY-MM-DD'; // default date format for shipment

        self.config = {
            token: 'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0IiwiaWF0IjoxNDYwNTM2MjU5LCJzdWIiOiJjdXN0b21lciIsImlzcyI6IjIifQ.cHh9uW2YVszpFmvlsgHAUZMiRzXymme_RAQ9Py5lsvQ',
            userName: 'Maccabi',
            userAvatar: 'images/demo/user_avatar.png',
            apiHost: 'http://localhost:8080/api/v0/',
            accountStatus: 'INCOMPLETE',
            locale: 'GB',
            dateFormat: {
                year: 'numeric',
                day: '2-digit',
                month: 'short',
                hour: 'numeric',
                minute: '2-digit',
                hour12: true
            },
            dateOnlyFormat: {
                year: 'numeric',
                month: 'long',
                day: 'numeric'
            }
        };


        // Private constructor
        function Settings() {
            this.config = function () {
                return self.config;
            };
        }

        self.setConfigs = function () {
            if (window.token) {
                self.config.token = window.token;
            }
            if (window.user_name) {
                self.config.userName = window.user_name;
            }
            if (window.user_img) {
                self.config.userAvatar = window.user_img;
            }
            if (window.api_base_url) {
                self.config.apiHost = window.api_base_url + '/';
            }
            if (window.account_status) {
                self.config.accountStatus = window.account_status;
            }
        };


        // Method for instantiating
        this.$get = function () {
            return new Settings();
        };
    });
