(function () {
    'use strict';
 
    var app= angular.module('myApp');
    app.service('GridLayoutService', ['$http', function ($http) {
    	
    	this.getRandomGrid = function () {
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
    	
    	this.getBasicGrid = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/basic', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
					    		}})
					};
		this.solveCreatedMaze = function (ParamAndGrid) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/solveCreatedMaze', ParamAndGrid, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
					    		}})
					};
		this.getRFAStar = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStar', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
				        		}})
    	};
    	this.solveCreatedRFAStar = function (ParamAndGrid) {
			return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStarCustomized', ParamAndGrid, {headers:{
									'Access-Control-Allow-Origin': '*',
									'Access-Control-Allow-Headers': 'origin, content-type, accept',
									'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
									'contentType': 'application/json; charset=utf-8',
									'dataType' : 'json',
								 }})
					};
    	
    	this.getAdaptiveAStar = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/adaptiveAStar', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
				        		}})
    	};
    	
    	
    	
    	this.solveCreatedAdaptiveAStar = function (ParamAndGrid) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/adaptiveAStarCustomized', ParamAndGrid, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
					    		}})
		};
		
		this.solveRFAStarWithLG = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStarWithLG', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
				        		}})
    	};
    	
    	this.solveCustomizedRFAStarWithLG = function (ParamAndGrid) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStarWithLGCustomized', ParamAndGrid, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
					    		}})
		};
		
		this.solveRFAStarWithSG = function (customizedParam) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStarWithSG', customizedParam, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
				        		}})
    	};
    	
    	this.solveCustomizedRFAStarWithSG = function (ParamAndGrid) {
    		return $http.post('http://localhost:8080/A_Star_Algo/rest/GridResource/RFAStarWithSGCustomized', ParamAndGrid, {headers:{
						            'Access-Control-Allow-Origin': '*',
						            'Access-Control-Allow-Headers': 'origin, content-type, accept',
						            'Access-Control-Allow-Methods': 'GET, POST, PUT, DELETE, OPTIONS',
						            'contentType': 'application/json; charset=utf-8',
						            'dataType' : 'json',
					    		}})
		};
    	
    }])
 
}());