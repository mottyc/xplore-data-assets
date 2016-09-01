/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Server model
 * @name myApp.MdaServerModel
 * @description
 * # MdaServerModel
 * {
 *    serverKey: int,
 *    serverName: string,
 *    displayName: string,
 *    description: string,
 *    serverTypeCd: string,
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *
 * }
 */
angular.module('myApp')
    .factory('MdaServerModel', [
        function () {
            var self = this;

            function MdaServerModel(data) {
                this.serverKey = null;
                this.serverName = null;
                this.displayName = null;
                this.description = null;
                this.serverTypeCd = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaServerModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return MdaServerModel;
        }
    ]);