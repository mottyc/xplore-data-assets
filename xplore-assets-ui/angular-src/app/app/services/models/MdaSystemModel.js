/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc System model
 * @name myApp.MdaSystemModel
 * @description
 * # MdaSystemModel
 * {
 *    systemKey: int,
 *    systemName: string,
 *    appName: string,
 *    displayName: string,
 *    description: string,
 *    systemDepartment: string,
 *    
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaSystemModel', [
        function () {
            var self = this;

            function MdaSystemModel(data) {
                this.systemKey = null;
                this.systemName = null;
                this.appName = null;
                this.displayName = null;
                this.description = null;
                this.systemDepartment = null;
                
                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaSystemModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return MdaSystemModel;
        }
    ]);
