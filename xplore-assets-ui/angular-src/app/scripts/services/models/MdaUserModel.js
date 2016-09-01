/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc User model
 * @name myApp.MdaUserModel
 * @description
 * # MdaUserModel
 * {
 *    usernameKey: string,
 *    firstName: string,
 *    lastName: string,
 *    displayName: string,
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaUserModel', [
        function () {
            var self = this;

            function MdaUserModel(data) {
                this.usernameKey = null;
                this.firstName = null;
                this.lastName = null;
                this.displayName = null;
                
                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaUserModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return MdaUserModel;
        }
    ]);