/**
 * Created by Okan Menevseoglu on 14.11.2016.
 */

var app = angular.module('app', [
    'ui.router',
    'app.config',
    'app.product',
    'ui-notification'
]);

app.config(function ($urlRouterProvider, $httpProvider) {
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $urlRouterProvider.otherwise("/products");
});
