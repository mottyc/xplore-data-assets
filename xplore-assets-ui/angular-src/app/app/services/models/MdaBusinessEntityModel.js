/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Business entity model
 * @name myApp.MdaBusinessEntityModel
 * @description
 * # MdaBusinessEntity
 * {
 *    businessEntityKey: int,
 *    businessEntityName: string,
 *    displayName: string,
 *    description: string,
 *    
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 * }
 */
angular.module('myApp')
    .factory('MdaBusinessEntityModel', [
        function () {
            var self = this;

            function MdaBusinessEntityModel(data) {
                this.businessEntityKey = null;
                this.businessEntityName = null;
                this.displayName = null;
                this.description = null;

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaBusinessEntityModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return MdaBusinessEntityModel;
        }
    ]);