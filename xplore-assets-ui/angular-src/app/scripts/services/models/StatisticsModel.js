/*
 * Copyright (c) 2016.
 *
 */

'use strict';

/**
 * @ngdoc Statistics model
 * @name myApp.StatisticsModel
 * @description
 * # StatisticsModel
 * {
 *    entitiesCount: int,
 *    systemsCount: int,
 *    serversCount: int,
 *    databasesCount: int,
 *    schemasCount: int,
 *    tablesCount: int,
 *    columnsCount: int,
 *    relationsCount: int,
 *    usersCount: int
 * }
 */
angular.module('myApp')
    .factory('StatisticsModel', [
        function () {
            var self = this;

            function StatisticsModel(data) {
                this.entitiesCount = null;
                this.systemsCount = null;
                this.serversCount = null;
                this.databasesCount = null;
                this.schemasCount = null;
                this.tablesCount = null;
                this.columnsCount = null;
                this.relationsCount = null;
                this.usersCount = null;

                if (data) {
                    this.setData(data);
                }
            }

            StatisticsModel.prototype = {
                setData: function (data) {
                    angular.extend(this, data, this);
                }
            };

            return StatisticsModel;
        }
    ]);
