angular.module('app', []).controller('indexController', function ($scope, $http) {
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
        $http.get('http://localhost:8050/v1/cart/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.loadCart = function () {
        $http.get('http://localhost:8050/v1/cart').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.eraseCart = function (productId) {
        $http.get('http://localhost:8050/v1/cart/eraseCart').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteCartItem = function (productId) {
        $http.get('http://localhost:8050/v1/cart/deleteCartItem/'+ productId).then(function (response) {
            $scope.loadCart();
        });
    }

   $scope.addQuantity = function (productId, n) {
        $http.get('http://localhost:8050/v1/cart/addQuantity/' + productId + '?quantity=' + n).then(function (response) {
            $scope.loadCart();
        });
   }

    $scope.loadProducts();
    $scope.loadCart();
});