angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8080';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/api/v1/advertisement').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'api/v1/advertisement/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.loadProducts();
});