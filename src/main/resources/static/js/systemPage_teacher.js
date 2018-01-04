var app = angular.module('system_admin',['ngRoute'])
    .config(['$routeProvider', function($routeProvider){
        $routeProvider
            .when('/',{template:''})
            // .when('/courseModify',{templateUrl:'courseModify'})
            // .when('/gradeRegist',{templateUrl:'gradeRegist'})
            .when('/courseModify',{template:'not finish'})
            .when('/gradeRegist',{template:'not finish'})
            .when('/salaryList',{templateUrl:'salaryList'})
            .otherwise({redirectTo:'/'});
    }]);
