/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc service
 * @name myApp.AddressModel
 * @description
 * # AddressModel
 * {
 *    countryCode: {CountryCode},
 *    city: string,
 *    street: string,
 *    state: string,
 *    zip: string,
 *    geo: string,
 * }
 */
angular.module('myApp')
    .factory('AddressModel', [
        function () {
            var self = this;

            function Address(data) {
                this.countryCode = null;
                this.city = null;
                this.street = null;
                this.state = null;
                this.zip = null;
                this.geo = null;

                if (data) {
                    this.setData(data);
                }
            }

            Address.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return Address;
        }
    ]);
