(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);


    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .when('/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/advertisement', {
                templateUrl: 'advertisement/advertisement.html',
                controller: 'advertisementController'
            })
            .when('/advertisement/:page', {
                templateUrl: 'advertisement/advertisement.html',
                controller: 'advertisementController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
            if ($localStorage.marketUser) {
                try {
                    let jwt = $localStorage.marketUser.token;
                    let payload = JSON.parse(atob(jwt.split('.')[1]));
                    let currentTime = parseInt(new Date().getTime() / 1000);
                    if (currentTime > payload.exp) {
                        console.log("Token is expired!!!");
                        delete $localStorage.marketUser;
                        $http.defaults.headers.common.Authorization = '';
                    }
                } catch (e) {
                }

                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.marketUser.token;
            }
        }
})();

angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8000/auth/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.marketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.marketUser) {
            return true;
        } else {
            return false;
        }
    };
});