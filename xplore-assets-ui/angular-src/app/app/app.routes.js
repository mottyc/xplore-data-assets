'use strict';

/**
 * Load states for application
 * more info on UI-Router states can be found at
 * https://github.com/angular-ui/ui-router/wiki
 */
angular.module('myApp')

    .config([
        '$locationProvider', '$stateProvider', '$urlRouterProvider',
        function ($locationProvider, $stateProvider, $urlRouterProvider) {
            //$locationProvider.html5Mode(true);

            $stateProvider
                .state('dashboard', {
                    url: '/dashboard',
                    templateUrl: 'app/components/dashboard/dashboard.html',
                    controller: 'dashboardController',
                    controllerAs: 'ctrlDashboard'
                })
                .state('columns', {
                    url: '/columns',
                    templateUrl: 'app/components/columns/columns.html',
                    controller: 'columnsController',
                    controllerAs: 'ctrlColumns'
                })
                .state('databases', {
                    url: '/databases',
                    templateUrl: 'app/components/databases/databases.html',
                    controller: 'databasesController',
                    controllerAs: 'ctrlDatabases'
                })
                .state('entities', {
                    url: '/entities',
                    templateUrl: 'app/components/entities/entities.html',
                    controller: 'entitiesController',
                    controllerAs: 'ctrlEntities'
                })
                .state('relations', {
                    url: '/relations',
                    templateUrl: 'app/components/relations/relations.html',
                    controller: 'relationsController',
                    controllerAs: 'ctrlRelations'
                })
                .state('schemas', {
                    url: '/schemas',
                    templateUrl: 'app/components/schemas/schemas.html',
                    controller: 'schemasController',
                    controllerAs: 'ctrlSchemas'
                })
                .state('servers', {
                    url: '/servers',
                    templateUrl: 'app/components/servers/servers.html',
                    controller: 'serversController',
                    controllerAs: 'ctrlServers'
                })
                .state('systems', {
                    url: '/systems',
                    templateUrl: 'app/components/systems/systems.html',
                    controller: 'systemsController',
                    controllerAs: 'ctrlSystems'
                    // resolve: {
                    //     items: function (systemsManager) {
                    //         return systemsManager.getAll();
                    //     }
                    // }
                })
                .state('tables', {
                    url: '/tables',
                    templateUrl: 'app/components/tables/tables.html',
                    controller: 'tablesController',
                    controllerAs: 'ctrlTables'
                })
                .state('users', {
                    url: '/users',
                    templateUrl: 'app/components/users/users.html',
                    controller: 'usersController',
                    controllerAs: 'ctrlUsers'
                })
            ;

            $urlRouterProvider.otherwise('/dashboard');
        }]);