angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage, $window) {

var pageNumber = 1;

    $scope.loadProducts = function () {
        $http.get('http://localhost:8100/core/products/v1').then(function (response) {
            $scope.productsList = response.data;
        });
    }

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
            alert(response.data.title);
        });
    }

    $scope.addToCart = function (productId) {
//    $window.alert('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId + '/add/' + productId);
        $http.get('http://localhost:8100/cart/v1/' + $localStorage.winterMarketGuestCartId + '/add/' + productId).then(function (response) {
            $scope.loadCart();
        });
    }

    $scope.nextPage = function () {
    pageNumber = pageNumber + 1;
//    $window.alert('http://localhost:8100/core/products/v1?page=' + pageNumber);
            $http.get('http://localhost:8100/core/products/v1?page=' + pageNumber).then(function (response) {
                $scope.productsList = response.data;
            });
    }

    $scope.prevPage = function () {
        if (pageNumber > 1) {
            pageNumber = pageNumber - 1;
//            $window.alert('http://localhost:8100/core/products/v1?page=' + pageNumber);
            $http.get('http://localhost:8100/core/products/v1?page=' + pageNumber).then(function (response) {
                $scope.productsList = response.data;
            });

        }
    }

    $scope.loadProductsSort = function () {
        if (typeof ($scope.sort.minCost) == "undefined" ) {
            $scope.sort.minCost = ""
        }
        if (typeof ($scope.sort.maxCost) == "undefined" ) {
            $scope.sort.maxCost = ""
        }
        if (typeof ($scope.sort.title) == "undefined" ) {
            $scope.sort.title = ""
        }

//        $window.alert("http://localhost:8050/products/v1?minCost="+$scope.sort.minCost+
//                                      "&maxCost="+$scope.sort.maxCost+"&title="+$scope.sort.title);

        $http.get('http://localhost:8100/core/products/v1?minCost='+ $scope.sort.minCost +
            '&maxCost=' + $scope.sort.maxCost + '&title=' + $scope.sort.title).then(function (response) {
            $scope.productsList = response.data;
        });
   }

    $scope.loadProducts();

});