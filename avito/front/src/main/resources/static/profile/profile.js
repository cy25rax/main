angular.module('market').controller('profileController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8000/core/profile/';

    $scope.loadProfile = function () {
        $http.get(contextPath).then(function (response) {
            $scope.profile = response.data;
        });
    }

    $scope.functionAddAdvertisement = function () {
        $http.post(contextPath + 'add', $scope.adv).then(function (response) {
            alert(response.data);
        });
    }

    $scope.loadProfile();
});