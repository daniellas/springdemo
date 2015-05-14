var app = angular.module('demo', [ 'ngResource', 'ngRoute', 'ui.bootstrap' ]);

app.factory('CategorySrv', function($resource) {
    return $resource('categories');
});

app.factory('ArticleSrv', function($resource) {
    return $resource('articles');
});

app.factory('SearchSrv', function($resource) {
    return $resource('articles/search');
});

app.factory('CartSrv', function($resource) {
    return $resource('cart');
});

app.factory('SecuritySrv', function($resource) {
    return $resource('security');
});

app.factory('OrderSrv', function($resource) {
    return $resource('orders');
});

app.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'partials/index.html',
        controller : 'RoutingCtrl'
    }).when('/cat-add', {
        templateUrl : 'partials/cat-add.html',
        controller : 'RoutingCtrl'
    }).when('/art-add', {
        templateUrl : 'partials/art-add.html',
        controller : 'RoutingCtrl'
    }).when('/cart', {
        templateUrl : 'partials/cart.html',
        controller : 'RoutingCtrl'
    }).when('/orders', {
        templateUrl : 'partials/orders.html',
        controller : 'RoutingCtrl'
    }).otherwise({
        templateUrl : 'partials/index.html',
        controller : 'RoutingCtrl'
    });
});

app.controller('RoutingCtrl', function($rootScope) {
    $rootScope.$broadcast('route.update');
});

app.controller('AppCtrl', function($scope, $location, CategorySrv, ArticleSrv, CartSrv, SecuritySrv, OrderSrv, SearchSrv) {

    SecuritySrv.get(function(response) {
        $scope.currentUser = response;
    });

    CategorySrv.query(function(response) {
        $scope.categories = response;
    });

    ArticleSrv.query(function(response) {
        $scope.articles = response;
    });

    CartSrv.get(function(response) {
        $scope.cartInfo = response;
    });

    $scope.saveCategory = function(category) {
        CategorySrv.save(category, function(response) {
            CategorySrv.query(function(response) {
                $scope.categories = response;
            });
        }, function(response) {
            showError(response);
        });
    }

    $scope.saveArticle = function(article) {
        ArticleSrv.save(article, function(response) {
            ArticleSrv.query(function(response) {
                $scope.articles = response;
            });
        }, function(response) {
            showError(response);
        });
    }

    $scope.allArticles = function() {
        ArticleSrv.query(function(response) {
            $scope.articles = response;
            $location.path('/');
        }, function(response) {
            showError(response);
        });
    }

    $scope.filterArticles = function(category) {
        ArticleSrv.query(category, function(response) {
            $scope.articles = response;
            $location.path('/');
        }, function(response) {
            showError(response);
        });
    }

    $scope.addToCart = function(article) {
        CartSrv.save(article, function(response) {
            $scope.cartInfo = response;
        }, function(response) {
            showError(response);
        });
    }

    $scope.removeFromCart = function(article) {
        CartSrv.remove({
            id : article.id
        }, function(response) {
            $scope.cartInfo = response;
        }, function(response) {
            showError(response);
        });
    }

    $scope.acceptOrder = function(order) {
        OrderSrv.save(order, function(response) {
            $scope.cartInfo = response;
            alert('Twoje zamówienie zostało przyjęte');
        }, function(response) {
            showError(response);
        });
    }

    $scope.searchArticle = function(name) {
        SearchSrv.query({
            name : name
        }, function(response) {
            $scope.articles = response;
        }, function(response) {
            showError(response);
        });
    }

    $scope.isAdmin = function() {
        if ($scope.currentUser && $scope.currentUser.authorities) {
            var authorities = $scope.currentUser.authorities;

            for (var i = 0; i < authorities.length; i++) {
                if (authorities[i].authority == 'ROLE_ADMIN') {
                    return true;
                }
            }
        }

        return false;
    }

    $scope.$on('route.update', function() {
        if ($scope.collapse) {
            $scope.collapse = !$scope.collapse;
        }
    });

    function showError(response) {
        if (response.headers('X-ErrMsg')) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        } else {
            alert('Wystąpił błąd');
        }
    }
});

app.controller('OrderCtrl', function($scope, OrderSrv) {
    OrderSrv.query(function(response) {
        $scope.orders = response;
    }, function(response) {
        alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
    });

    $scope.selectOrder = function(order) {
        $scope.orderDetails = order;
    }
});

app.directive('btrValidation', function() {
    return {
        restrict : 'A',
        require : 'ngModel',
        link : function(scope, el, attrs, inputCtrl) {
            var parentEl = el.parent();
            do {
                if (parentEl.hasClass('form-group')) {
                    scope.$watch(function() {
                        return inputCtrl.$valid;
                    }, function() {
                        parentEl.toggleClass('has-error', inputCtrl.$invalid);
                        parentEl.toggleClass('has-success', inputCtrl.$valid);
                    });
                    break;
                }
                parentEl = parentEl.parent();
            } while (parentEl.length > 0);
        }
    }
});