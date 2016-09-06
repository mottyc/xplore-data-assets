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
                    url: '/columns/{key}',
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
                    url: '/databases/{key}',
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
                .state('entity', {
                    url: '/entities/{key}',
                    templateUrl: 'views/components/entities/entity.html',
                    controller: 'entityController',
                    controllerAs: 'ctrl'
                })
                .state('relations', {
                    url: '/relations',
                    templateUrl: 'views/components/relations/relations.html',
                    controller: 'relationsController',
                    controllerAs: 'ctrl'
                })
                .state('relation', {
                    url: '/relations/{key}',
                    templateUrl: 'views/components/relations/relation.html',
                    controller: 'relationController',
                    controllerAs: 'ctrl'
                })
                .state('schemas', {
                    url: '/schemas',
                    templateUrl: 'views/components/schemas/schemas.html',
                    controller: 'schemasController',
                    controllerAs: 'ctrl'
                })
                .state('schema', {
                    url: '/schemas/{key}',
                    templateUrl: 'views/components/schemas/schema.html',
                    controller: 'schemaController',
                    controllerAs: 'ctrl'
                })
                .state('servers', {
                    url: '/servers',
                    templateUrl: 'views/components/servers/servers.html',
                    controller: 'serversController',
                    controllerAs: 'ctrl'
                })
                .state('server', {
                    url: '/servers/{key}',
                    templateUrl: 'views/components/servers/server.html',
                    controller: 'serverController',
                    controllerAs: 'ctrl'
                })
                .state('systems', {
                    url: '/systems',
                    templateUrl: 'views/components/systems/systems.html',
                    controller: 'systemsController',
                    controllerAs: 'ctrl'
                })
                .state('system', {
                    url: '/systems/{key}',
                    templateUrl: 'views/components/systems/system.html',
                    controller: 'systemController',
                    controllerAs: 'ctrl'
                })
                .state('tables', {
                    url: '/tables',
                    templateUrl: 'views/components/tables/tables.html',
                    controller: 'tablesController',
                    controllerAs: 'ctrl'
                })
                .state('table', {
                    url: '/tables/{key}',
                    templateUrl: 'views/components/tables/table.html',
                    controller: 'tableController',
                    controllerAs: 'ctrl'
                })
                .state('users', {
                    url: '/users',
                    templateUrl: 'views/components/users/users.html',
                    controller: 'usersController',
                    controllerAs: 'ctrl'
                })
                .state('user', {
                    url: '/users/{key}',
                    templateUrl: 'views/components/users/user.html',
                    controller: 'userController',
                    controllerAs: 'ctrl'
                })
            ;

            $urlRouterProvider.otherwise('/dashboard');
        }]);