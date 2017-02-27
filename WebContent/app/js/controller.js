(function () {
    'use strict';
 
    var app= angular.module('myApp');
    app.controller('GridLayoutCtrl', ['$scope', 'GridLayoutService', function ($scope, GridLayoutService) {
    	/*$scope.gridtest =  {"maze":[[{"xCoordinate":0,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":4,"onFinalPath":true,"steps":0,"start":true,"end":false},{"xCoordinate":0,"yCoordinate":1,"obstacle":false,"visited":false,"heuristic":3,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":0,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":1,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":3,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":1,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":2,"obstacle":false,"visited":true,"heuristic":1,"onFinalPath":true,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":2,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":1,"obstacle":true,"visited":false,"heuristic":1,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":0,"onFinalPath":true,"steps":0,"start":false,"end":true}]],
    					"goalReached":true}
    	*/
    	
    	$scope.gridListtest =  [{"maze":[[{"xCoordinate":0,"yCoordinate":0,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":1,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":2,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":3,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":1,"yCoordinate":0,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":1,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":3,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":2,"yCoordinate":0,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":1,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":2,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":3,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":3,"yCoordinate":0,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":1,"heuristic":0,"cellStatus":"Visited","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":3,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"}]],"goalReached":false},{"maze":[[{"xCoordinate":0,"yCoordinate":0,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":2,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":1,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":2,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":2,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":2,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":3,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":2,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":3,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"}]],"goalReached":false},{"maze":[[{"xCoordinate":0,"yCoordinate":0,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":1,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":2,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":1,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":1,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":2,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":2,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":3,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":3,"yCoordinate":0,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":3,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"}]],"goalReached":false},{"maze":[[{"xCoordinate":0,"yCoordinate":0,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":2,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":0,"yCoordinate":3,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":1,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":1,"yCoordinate":3,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":2,"yCoordinate":0,"heuristic":0,"cellStatus":"Blank","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":2,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":2,"yCoordinate":3,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"}],[{"xCoordinate":3,"yCoordinate":0,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":1,"heuristic":0,"cellStatus":"FinalPath","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":2,"heuristic":0,"cellStatus":"Block","steps":0,"stepsTillNow":0,"direction":"Up"},{"xCoordinate":3,"yCoordinate":3,"heuristic":0,"cellStatus":"Start","steps":0,"stepsTillNow":0,"direction":"Up"}]],"goalReached":true}];
    	
    	$scope.gridEditable=false;
    	$scope.gridIndex=0;
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
    	
    	$scope.getFirstStep = function() {
    		$scope.grid = $scope.gridList[0];
            $scope.gridIndex=0;
    	}
    	
    	$scope.getLastStep = function() {
    		$scope.grid = $scope.gridList[$scope.gridList.length-1];
            $scope.gridIndex=$scope.gridList.length-1;
    	}
    	
    	$scope.getPrevious = function() {
    		if($scope.gridIndex>0) {
    			$scope.gridIndex= $scope.gridIndex - 1;
        		$scope.grid = $scope.gridList[$scope.gridIndex];
    		}
    	}
    	
    	$scope.getNext = function() {
    		
    		if($scope.gridIndex<$scope.gridList.length-1) {
    			$scope.gridIndex= $scope.gridIndex + 1;
	    		$scope.grid = $scope.gridList[$scope.gridIndex];
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
    	$scope.getRepeatedAStar = function() {
			$scope.grid = null;
			$scope.gridList = null;
			$scope.gridEditable=false;
			
			/*$scope.gridList = $scope.gridListtest;
            $scope.grid = $scope.gridList[0];
            $scope.completeGrid = $scope.gridList[0];
            $scope.gridIndex=0;*/
			
			var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
			GridLayoutService.getRepeatedAStar(customizedParam) .then(function (response) {
                $scope.gridList = response.data;
                $scope.grid = $scope.gridList[0];
                $scope.completeGrid = $scope.gridList[0];
                $scope.gridIndex=0;
            }, function (error) {
            	console.log('Unable to load default data: ' + error.message);
            });	
		
    	}
    	
    	$scope.solveCreatedRFAStar = function() {
    		$scope.gridList = null;
			$scope.gridEditable=false;
			var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
			GridLayoutService.solveCreatedRFAStar(customizedParam+ '|' + angular.toJson($scope.grid)) .then(function (response) {
				$scope.gridList = response.data;
                $scope.grid = $scope.gridList[0];
                $scope.completeGrid = $scope.gridList[0];
                $scope.gridIndex=0;
            }, function (error) {
            	console.log('Unable to load default data: ' + error.message);
            });	
    	}
    	
    	
    	$scope.solveAdaptiveAStar = function() {
			$scope.grid = null;
			$scope.gridList = null;
			$scope.gridEditable=false;
			
			var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
			GridLayoutService.getAdaptiveAStar(customizedParam) .then(function (response) {
                $scope.gridList = response.data;
                $scope.grid = $scope.gridList[0];
                $scope.completeGrid = $scope.gridList[0];
                $scope.gridIndex=0;
            }, function (error) {
            	console.log('Unable to load default data: ' + error.message);
            });	
		}
    	
    	$scope.solveCreatedAdaptiveAStar = function() {
    		$scope.gridList = null;
			$scope.gridEditable=false;
			var customizedParam = '{"length":' + $scope.length + ',"breadth":' + $scope.breadth + ',"xStart":' + $scope.xStart + ',"yStart":' + $scope.yStart + ',"xGoal":' + $scope.xGoal + ',"yGoal":' + $scope.yGoal + '}';
			GridLayoutService.solveCreatedAdaptiveAStar(customizedParam+ '|' + angular.toJson($scope.grid)) .then(function (response) {
				$scope.gridList = response.data;
                $scope.grid = $scope.gridList[0];
                $scope.completeGrid = $scope.gridList[0];
                $scope.gridIndex=0;
            }, function (error) {
            	console.log('Unable to load default data: ' + error.message);
            });	
    	}
    	
    }])
}());