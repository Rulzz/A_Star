(function () {
    'use strict';
 
    var app= angular.module('myApp');
    app.controller('GridLayoutCtrl', ['$scope', 'GridLayoutService', function ($scope, GridLayoutService) {
    	/*$scope.gridtest =  {"maze":[[{"xCoordinate":0,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":4,"onFinalPath":true,"steps":0,"start":true,"end":false},{"xCoordinate":0,"yCoordinate":1,"obstacle":false,"visited":false,"heuristic":3,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":0,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":1,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":3,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":1,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":2,"obstacle":false,"visited":true,"heuristic":1,"onFinalPath":true,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":2,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":1,"obstacle":true,"visited":false,"heuristic":1,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":0,"onFinalPath":true,"steps":0,"start":false,"end":true}]],
    					"goalReached":true}
    	*/
    	
    	$scope.gridEditable=false;
    	$scope.change = function() {
    			$scope.xy='Random';
    	}
    	
    	$scope.change = function(cell) {
    		if($scope.gridEditable) {
    			if(cell.cellStatus=='Blank') {
        			cell.cellStatus='Block';
        		} else if (cell.cellStatus=='Block') {
        			cell.cellStatus='Blank';
        		}
    		}
    	}
    	
    	$scope.getRandomGrid = function() {
    							$scope.grid = null;
    							$scope.gridEditable=false;
        						GridLayoutService.getRandomGrid() .then(function (response) {
				                    $scope.grid = response.data;
				                }, function (error) {
				                	console.log('Unable to load default data: ' + error.message);
				                });	
        }
    	$scope.getCustomizedGrid = function() {
    							$scope.grid = null;
    							$scope.gridEditable=false;
    							var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
    							GridLayoutService.getCustomizedGrid(customizedParam) .then(function (response) {
					                $scope.grid = response.data;
					            }, function (error) {
					            	console.log('Unable to load default data: ' + error.message);
					            });	
    	}
    	
    	$scope.getBasicGrid = function() {
    							$scope.grid = null;
    							$scope.gridEditable=true;
								var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
								GridLayoutService.getBasicGrid(customizedParam) .then(function (response) {
					                $scope.grid = response.data;
					            }, function (error) {
					            	console.log('Unable to load default data: ' + error.message);
					            });	
    	}
    	$scope.solveCreatedMaze = function() {
								$scope.gridEditable=false;
								var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
								GridLayoutService.solveCreatedMaze(customizedParam+ '|' + angular.toJson($scope.grid)) .then(function (response) {
					                $scope.grid = response.data;
					            }, function (error) {
					            	console.log('Unable to load default data: ' + error.message);
					            });	
		}
    	
    }])
}());