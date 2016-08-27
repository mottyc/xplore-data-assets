/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Schema model
 * @name myApp.MdaSchemaModel
 * @description
 * # MdaSchemaModel
 * {
 *    schemaKey: int,
 *    fullSchemaName: string,
 *    domainKey: int,
 *    dbName: string,
 *    schemaName: string,
 *    displayName: string,
 *    description: string,
 *    tables: [...MdaTableModel],
 *
 *    createDate: string (ISO8601 date),
 *    updateDate: string (ISO8601 date),
 *    updateBy: string
 *    
 * }
 */
angular.module('myApp')
    .factory('MdaSchemaModel', [
        function () {
            var self = this;

            function MdaSchemaModel(data) {
                this.schemaKey = null;
                this.fullSchemaName = null;
                this.domainKey = null;
                this.dbName = null;
                this.schemaName = null;
                this.displayName = null;
                this.description = null;
                this.tables = [];

                this.createDate = null;
                this.updateDate = null;
                this.updateBy = null;

                if (data) {
                    this.setData(data);
                }
            }

            MdaSchemaModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return MdaSchemaModel;
        }
    ]);
