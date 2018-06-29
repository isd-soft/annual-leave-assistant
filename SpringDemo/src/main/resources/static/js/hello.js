'use strict'

var app = angular.module('hello',[]);
app.controller('home', function($scope,$http) {
        $http.get('/hello-angularjs').then(function(data) {
            $scope.custom = data;
            console.log(data);
        })
});