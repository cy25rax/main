angular.module('market').controller('profileController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8000/core/profile/';
    const contextPathFeedback = 'http://localhost:8000/core/profile/feedbackList';

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

    $scope.loadFeedbackList = function () {
        $http.get(contextPathFeedback).then(function (response) {
            $scope.feedbackList = response.data;
        });
    }

    $scope.loadProfile();
    $scope.loadFeedbackList();
});