/**
 * @ngdoc directive
 * @name patternfly.modals.directive:pfAboutModal
 *
 * @description
 * Directive for rendering modal windows.
 *
 * @param {string=} additionalInfo Text explaining the version or copyright
 * @param {string=} copyright Product copyright information
 * @param {string=} imgAlt The alt text for the corner grahpic
 * @param {string=} imgSrc The source for the corner grahpic
 * @param {boolean=} isOpen Flag indicating that the modal should be opened
 * @param {function=} onClose Function to call when modal is closed
 * @param {object=} productInfo data for the modal:<br/>
 * <ul style='list-style-type: none'>
 * <li>.product - the product label
 * <li>.version - the product version
 * </ul>
 * @param {string=} title The product title for the modal
 *
 */
angular.module('myApp')

    .directive("pfSelectEntitiesModalTransclude", function ($parse) {
        'use strict';
        return {
            link: function (scope, element, attrs) {
                element.append($parse(attrs.pfSelectEntitiesModalTransclude)(scope));
            }
        };
    })

    .directive('selectEntitiesModal', function () {
        'use strict';
        return {
            restrict: 'A',
            scope: {
                close: "&onClose",
                save: "&onSave",
                isOpen: '=?',
                title: '=?'
            },
            templateUrl: 'views/directives/selection/select-entities.html',
            transclude: true,
            controller: ['$scope', '$modal', '$transclude', function ($scope, $modal, $transclude) {
                if ($scope.isOpen === undefined) {
                    $scope.isOpen = false;
                }

                // The ui-bootstrap modal only supports either template or templateUrl as a way to specify the content.
                // When the content is retrieved, it is compiled and linked against the provided scope by the $modal service.
                // Unfortunately, there is no way to provide transclusion there.
                //
                // The solution below embeds a placeholder directive (i.e., pfAboutModalTransclude) to append the transcluded DOM.
                // The transcluded DOM is from a different location than the modal, so it needs to be handed over to the
                // placeholder directive. Thus, we're passing the actual DOM, not the parsed HTML.
                $scope.openModal = function () {
                    $modal.open({
                        controller: 'selectEntitiesController',
                        controllerAs: 'ctrl',
                        resolve: {
                            content: function () {
                                var transcludedContent;
                                $transclude(function (clone) {
                                    transcludedContent = clone;
                                });
                                return transcludedContent;
                            }
                        },
                        scope: $scope,
                        templateUrl: "select-entities-template.html"
                    })
                        .result.then(
                        function () {
                            $scope.close(); // closed
                        },
                        function () {
                            $scope.close(); // dismissed
                        }
                    );
                };
            }],
            link: function (scope, element, attrs) {
                // watching isOpen attribute to dispay modal when needed
                var isOpenListener = scope.$watch('isOpen', function (newVal, oldVal) {
                    if (newVal === true) {
                        scope.openModal();
                    }
                });
                scope.$on('$destroy', isOpenListener);
            }
        };
    });