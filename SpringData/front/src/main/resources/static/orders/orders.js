angular.module('market').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:8100/core/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders/v1').then(function (response) {
            $scope.orders = response.data;
        });
    }

    $scope.loadOrders();
});