var app = angular.module('system',['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/',{template:''})
            .when('/courseTable',{templateUrl:'courseTable'})
            .when('/gradeCheck',{templateUrl:'gradeCheck'})
            .otherwise({redirectTo:'/'});
    }]);
app.controller("test",function ($scope) {
    $scope.message="hha";
    
})
