angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8050/auth', $scope.user)
            .then(function successCallback(response) {
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

    $scope.authCheck = function () {
        $http.get('http://localhost:8050/auth').then(function (response) {
            alert(response.data.value);
        });
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
        $http.get('http://localhost:8050/v1/products').then(function (response) {
            $scope.productsList = response.data;
        });
    }

//    $scope.showProductInfo = function (productId) {
//        $http.get('http://localhost:8050/v1/products' + productId).then(function (response) {
//            alert(response.data.title);
//        });
//    }

//    $scope.deleteProductById = function (productId) {
//        $http.delete('http://localhost:8050/v1/products' + productId).then(function (response) {
//            $scope.loadProducts();
//        });
//    }

    $scope.addToCart = function (productId) {
        $http.get('http://localhost:8150/v1/cart/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8150/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.eraseCart = function () {
        $http.delete('http://localhost:8150/v1/cart/eraseCart').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteCartItem = function (productId) {
        $http.get('http://localhost:8150/v1/cart/deleteCartItem/'+ productId).then(function (response) {
            $scope.loadCart();
        });
    }

   $scope.addQuantity = function (productId, n) {
        $http.get('http://localhost:8150/v1/cart/addQuantity/' + productId + '?quantity=' + n).then(function (response) {
            $scope.loadCart();
        });
   }

    $scope.createOrder = function () {
        $http.get('http://localhost:8050/v1/orders/createOrder').then(function (response) {
//        $http.get('http://localhost:8050/v1/cart/createOrder?userName=' + $localStorage.winterMarketUser.username).then(function (response) {
            $scope.loadCart();
        });
   }

    $scope.loadProducts();
    $scope.loadCart();
});