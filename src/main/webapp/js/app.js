var app = angular.module('demo', [ 'ngResource', 'ngRoute', 'ui.bootstrap' ]);

app.factory('CategorySrv', function($resource) {
    return $resource('categories');
});

app.factory('ArticleSrv', function($resource) {
    return $resource('articles');
});

app.factory('CartSrv', function($resource) {
    return $resource('cart');
});

app.factory('SecuritySrv', function($resource) {
    return $resource('security');
});

app.config(function($routeProvider) {
    $routeProvider.when('/', {
        templateUrl : 'partials/index.html'
    }).when('/cat-add', {
        templateUrl : 'partials/cat-add.html'
    }).when('/art-add', {
        templateUrl : 'partials/art-add.html'
    }).when('/cart', {
        templateUrl : 'partials/cart.html'
    }).otherwise({
        templateUrl : 'partials/index.html'
    });
});

app.controller('AppCtrl', function($scope, $location, CategorySrv, ArticleSrv, CartSrv, SecuritySrv) {

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
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        });
    }

    $scope.saveArticle = function(article) {
        ArticleSrv.save(article, function(response) {
            ArticleSrv.query(function(response) {
                $scope.articles = response;
            });
        }, function(response) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        });
    }

    $scope.allArticles = function() {
        ArticleSrv.query(function(response) {
            $scope.articles = response;
            $location.path('/');
        }, function(response) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        });
    }

    $scope.filterArticles = function(category) {
        ArticleSrv.query(category, function(response) {
            $scope.articles = response;
            $location.path('/');
        }, function(response) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        });
    }

    $scope.addToCart = function(article) {
        CartSrv.save(article, function(response) {
            $scope.cartInfo = response;
        }, function(response) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
        });
    }

    $scope.removeFromCart = function(article) {
        CartSrv.remove({
            id : article.id
        }, function(response) {
            $scope.cartInfo = response;
        }, function(response) {
            alert('Wystąpił błąd: ' + response.headers('X-ErrMsg'));
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
});
