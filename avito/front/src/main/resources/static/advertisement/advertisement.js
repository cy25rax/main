angular.module('market').controller('advertisementController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8000/core/advertisement';
    const productId = window.location.href.split('/').pop();

    $scope.loadAdvertisement = function () {
        $http.get(contextPath + "/" + productId).then(function (response) {
            $scope.product = response.data;
        });
    }

    $scope.functionFeedback = function () {
    console.log($scope.text);
        $http.post(contextPath + "/" + productId + "/feedback", $scope.text).then(function (response) {
        });
    }

    $scope.loadAdvertisement();
});