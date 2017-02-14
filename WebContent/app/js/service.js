(function () {
    'use strict';
 
    var app= angular.module('myApp');
    app.service('GridLayoutService', ['$http', function ($http) {
    	
    	this.getDefaultGrid = function () {
    		return $http.get('http://localhost:8080/A_Star_Algo/rest/GridResource/default', {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept, authorization',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
				        		}})
    	};
    	
    	this.getCustomizedGrid = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/customized', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
				        		}})
    	};
    	
    }])
 
}());