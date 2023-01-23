angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage, $window) {

    $scope.loadCart = function () {
//        $window.alert('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId);

        $http.get('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId).then(function (response) {
//        $http.get('http://localhost:8100/cart/v1/generate_uuid').then(function (response) {
            $scope.cart = response.data;
        });
    }

    $scope.eraseCart = function () {
        $http.get('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId + '/clear').then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.deleteCartItem = function (productId) {
        $http.get('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId + '/remove/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

   $scope.addQuantity = function (productId, n) {
        $http.get('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId + '/addQuantity/' + productId + '?quantity=' + n).then(function (response) {
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