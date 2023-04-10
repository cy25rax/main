angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {

    $scope.loadStudents = function () {
        $http.get('http://localhost:8080/findAll').then(function (response) {
            $scope.studentsList = response.data;
        });
    }

    $scope.addStudent = function () {
        $http.get('http://localhost:8080/addStudent?name=' + $scope.add_name + '&age=' + $scope.add_age)
            .then(function (response) {
                $scope.loadStudents();
        });
    }

    $scope.deleteStudent = function (Id) {
        $http.get('http://localhost:8080/delete/' + Id).then(function (response) {
            $scope.loadStudents();
        });
    }

    $scope.updateStudent = function (Id, name, age) {
        if (typeof ($scope.name) === "undefined" ) {
            $scope.name = ""
        }
        if (typeof ($scope.age) === "undefined" ) {
            $scope.age = ""
        }

        $http.get('http://localhost:8080/update/' + Id +
                                                '?name='+ name +
                                                '&age=' + age).then(function (response) {
            $scope.loadStudents();
        });
    }

    $scope.loadStudents();
});