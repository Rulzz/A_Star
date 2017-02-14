(function () {
    'use strict';
 
    var app= angular.module('myApp');
    app.controller('GridLayoutCtrl', ['$scope', 'GridLayoutService', function ($scope, GridLayoutService) {
    	/*$scope.gridtest =  {"maze":[[{"xCoordinate":0,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":4,"onFinalPath":true,"steps":0,"start":true,"end":false},{"xCoordinate":0,"yCoordinate":1,"obstacle":false,"visited":false,"heuristic":3,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":0,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":1,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":3,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":1,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":true,"steps":0,"start":false,"end":false},{"xCoordinate":1,"yCoordinate":2,"obstacle":false,"visited":true,"heuristic":1,"onFinalPath":true,"steps":0,"start":false,"end":false}],
    							[{"xCoordinate":2,"yCoordinate":0,"obstacle":false,"visited":true,"heuristic":2,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":1,"obstacle":true,"visited":false,"heuristic":1,"onFinalPath":false,"steps":0,"start":false,"end":false},{"xCoordinate":2,"yCoordinate":2,"obstacle":false,"visited":false,"heuristic":0,"onFinalPath":true,"steps":0,"start":false,"end":true}]],
    					"goalReached":true}
    	*/
    	
    	
    	
    	getDefaultGrid();

        function getDefaultGrid() {
        	GridLayoutService.getDefaultGrid() .then(function (response) {
		                    $scope.grid = response.data;
		                }, function (error) {
		                	console.log('Unable to load default data: ' + error.message);
		                });
        }
    	
    }])
 
}());