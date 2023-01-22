angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {

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

    $scope.loadCart();
});