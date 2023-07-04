angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8000';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/core/advertisement').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + '/core/advertisement/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.loadProducts();
});