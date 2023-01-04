angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage, $window) {

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8100/auth', $scope.user)
            .then(function successCallback(response) {
//                $window.alert(response.data.token);
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };

    $scope.clearUser = function () {
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.winterMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.winterMarketUser) {
        try {
            let jwt = $localStorage.winterMarketUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);
            if (currentTime > payload.exp) {
                console.log("Token is expired!!!");
                delete $localStorage.winterMarketUser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {
        }

        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.winterMarketUser.token;
    }

    $scope.loadProducts = function () {
        $http.get('http://localhost:8100/core/products/v1').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8100/cart/v1/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8100/cart/v1').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.eraseCart = function () {
        $http.get('http://localhost:8100/cart/v1/eraseCart').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteCartItem = function (productId) {
        $http.get('http://localhost:8100/cart/v1/deleteCartItem/'+ productId).then(function (response) {
            $scope.loadCart();
        });
    }

   $scope.addQuantity = function (productId, n) {
        $http.get('http://localhost:8100/cart/v1/addQuantity/' + productId + '?quantity=' + n).then(function (response) {
            $scope.loadCart();
        });
   }

    $scope.createOrder = function () {
        $http.get('http://localhost:8100/core/orders/v1/createOrder').then(function (response) {
            $scope.loadCart();
        });
   }

    $scope.loadProductsSort = function () {
        if (typeof ($scope.sort.minCost) == "undefined" ) {
            $scope.sort.minCost = ""
        }
        if (typeof ($scope.sort.maxCost) == "undefined" ) {
            $scope.sort.maxCost = ""
        }
        if (typeof ($scope.sort.title) == "undefined" ) {
            $scope.sort.title = ""
        }

//        $window.alert("http://localhost:8050/products/v1?minCost="+$scope.sort.minCost+
//                                  "&maxCost="+$scope.sort.maxCost+"&title="+$scope.sort.title);

        $http.get('http://localhost:8100/core/products/v1?minCost='+ $scope.sort.minCost +
            '&maxCost=' + $scope.sort.maxCost + '&title=' + $scope.sort.title).then(function (response) {
            $scope.productsList = response.data;
        });
   }

    $scope.loadProducts();
    $scope.loadCart();
});