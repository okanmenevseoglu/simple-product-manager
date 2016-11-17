/**
 * Created by Okan Menevseoglu on 14.11.2016.
 */
var product = angular.module('app.product', []);

product.config(function ($stateProvider) {
    $stateProvider.state('products', {
        url: '/products',
        views: {
            'main': {
                templateUrl: 'app/product/products.tpl.html',
                controller: 'ProductCtrl'
            }
        }
    })
        .state('addProduct', {
            url: '/products/add',
            views: {
                'main': {
                    templateUrl: 'app/product/product.add.tpl.html',
                    controller: 'ProductCtrl'
                }
            }
        })
        .state('editProduct', {
            url: '/products/edit',
            views: {
                'main': {
                    templateUrl: 'app/product/product.edit.tpl.html',
                    controller: 'ProductCtrl'
                }
            }
        })
        .state('success', {
            url: '/products/success',
            views: {
                'main': {
                    templateUrl: 'app/product/success.tpl.html',
                    controller: 'ProductCtrl'
                }
            }
        })
});

product.service('ProductService', ['$http', 'baseUrl', function ($http, baseUrl) {
    var productsUrl = baseUrl + '/products';

    // Product Requests

    this.getProducts = function () {
        return $http.get(productsUrl);
    };

    this.getProductById = function (productId) {
        return $http.get(productsUrl + '/' + productId);
    };

    this.getProductByTitle = function (productTitle) {
        return $http.get(productsUrl + '/' + productTitle);
    };


    this.addProduct = function (productData) {
        return $http.post(productsUrl + '/add', productData);
    };

    this.updateProduct = function (productId, productData) {
        return $http.put(productsUrl + '/update/' + productId, productData);
    };

    this.deleteProduct = function (productId) {
        return $http.delete(productsUrl + '/delete/' + productId);
    };
}]);

product.controller('ProductCtrl', ['$scope', '$rootScope', '$state', '$timeout', 'ProductService', 'Notification', function ($scope, $rootScope, $state, $timeout, ProductService, Notification) {
    // Get Products
    $scope.products = {};

    ProductService.getProducts()
        .then(function (response) {
            $scope.products = response.data;
        });

    // Products Data
    $scope.productData = {
        title: null,
        description: null,
        price: null
    };

    // Get Product By Id
    if ($rootScope.productId != undefined)
        ProductService.getProductById($rootScope.productId)
            .then(function (response) {
                $scope.product = response.data;
            });

    // Add Product
    $scope.submitProductForm = function (valid) {
        if (valid) {
            ProductService.addProduct($scope.productData)
                .success(function () {
                    Notification.success("Product is added!");
                    $state.go('success');
                    $state.transitionTo('success');
                })
                .error(function () {
                    Notification.error("Product is not added! Bad Request!");
                });
        }
    };

    $scope.editProduct = function (valid) {
        if (valid) {
            ProductService.updateProduct($rootScope.productId, $scope.productData)
                .success(function () {
                    Notification.success("Product is updated!");
                    $state.go('success');
                    $state.transitionTo('success');
                })
                .error(function () {
                    Notification.error("Product couldn't updated! Bad Request!");
                });
        }
    };

    $scope.setProductId = function (productId) {
        $rootScope.productId = productId;
    };

    // Delete Product
    $scope.deleteProduct = function (producerId) {
        var answer = confirm("Are you sure?");
        if(answer === true) {
            ProductService.deleteProduct(producerId)
                .success(function () {
                    Notification.success("Product is deleted!");
                    $timeout(function () {
                        window.location.reload()
                    }, 500);
                })
                .error(function () {
                    Notification.error("Product couldn't deleted! Bad Request!");
                });
        }
    };
}]);