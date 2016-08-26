'use strict';

/**
 * @ngdoc service
 * @name myApp.arrayService
 * @description
 * # arrayService
 * Factory in the myApp.
 */
angular.module('myApp')
    .factory('arrayService', function () {
        return {
            deleteElement: function (array, element) {
                var index = array.indexOf(element);

                if (index === -1) {
                    return false;
                }

                array.splice(index, 1);
            },
            moveElementUp: function (array, element) {
                var index = array.indexOf(element);

                // Item non-existent?
                if (index === -1) {
                    return false;
                }

                // If there is a previous element in sections
                if (array[index - 1]) {
                    // Swap elements
                    array.splice(index - 1, 2, array[index], array[index - 1]);
                    return array.indexOf(element);
                } else {
                    // Do nothing
                    return 0;
                }
            },
            moveElementDown: function (array, element) {
                var index = array.indexOf(element);

                // Item non-existent?
                if (index === -1) {
                    return false;
                }

                // If there is a next element in sections
                if (array[index + 1]) {
                    // Swap elements
                    array.splice(index, 2, array[index + 1], array[index]);
                } else {
                    // Do nothing
                    return 0;
                }
            }
        };
    });
