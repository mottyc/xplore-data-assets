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
                    templateUrl: 'views/components/dashboard/dashboard.html',
                    controller: 'dashboardController',
                    controllerAs: 'ctrl'
                })
                .state('columns', {
                    url: '/columns',
                    templateUrl: 'views/components/columns/columns.html',
                    controller: 'columnsController',
                    controllerAs: 'ctrl'
                })
                .state('column', {
                    url: '/column/{key}',
                    templateUrl: 'views/components/columns/column.html',
                    controller: 'columnController',
                    controllerAs: 'ctrl'
                })
                .state('databases', {
                    url: '/databases',
                    templateUrl: 'views/components/databases/databases.html',
                    controller: 'databasesController',
                    controllerAs: 'ctrl'
                })
                .state('database', {
                    url: '/database/{key}',
                    templateUrl: 'views/components/databases/database.html',
                    controller: 'databaseController',
                    controllerAs: 'ctrl'
                })
                .state('entities', {
                    url: '/entities',
                    templateUrl: 'views/components/entities/entities.html',
                    controller: 'entitiesController',
                    controllerAs: 'ctrl'
                })
                .state('relations', {
                    url: '/relations',
                    templateUrl: 'views/components/relations/relations.html',
                    controller: 'relationsController',
                    controllerAs: 'ctrl'
                })
                .state('schemas', {
                    url: '/schemas',
                    templateUrl: 'views/components/schemas/schemas.html',
                    controller: 'schemasController',
                    controllerAs: 'ctrl'
                })
                .state('servers', {
                    url: '/servers',
                    templateUrl: 'views/components/servers/servers.html',
                    controller: 'serversController',
                    controllerAs: 'ctrl'
                })
                .state('systems', {
                    url: '/systems',
                    templateUrl: 'views/components/systems/systems.html',
                    controller: 'systemsController',
                    controllerAs: 'ctrl'
                    // resolve: {
                    //     items: function (systemsManager) {
                    //         return systemsManager.getAll();
                    //     }
                    // }
                })
                .state('tables', {
                    url: '/tables',
                    templateUrl: 'views/components/tables/tables.html',
                    controller: 'tablesController',
                    controllerAs: 'ctrl'
                })
                .state('users', {
                    url: '/users',
                    templateUrl: 'views/components/users/users.html',
                    controller: 'usersController',
                    controllerAs: 'ctrl'
                })
            ;

            $urlRouterProvider.otherwise('/dashboard');
        }]);