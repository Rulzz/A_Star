package A_Star_Algo;

import java.util.ArrayList;

import AStar_Darshan.RepeatedForwardAStar;
import AStar_Darshan.AdaptiveAStar;
import AStar_Darshan.MazeCreator;

public class GridService {
	
	MazeCreator mazeCreator = new MazeCreator();
	AdaptiveAStar adaptive = new AdaptiveAStar();
	
	public Grid solveDefaultGrid() {
		GridParameters defaultParam = getDefaultGridParameters();
		Grid grid = mazeCreator.createMaze(defaultParam);
		
		grid.setGoalReached(ExecuteAStar.execute(grid.getMaze(), defaultParam));
		
		return grid;
	}

	public GridParameters getDefaultGridParameters() {
		GridParameters param = new GridParameters();
		param.setLength(5);
		param.setBreadth(5);
		param.setxGoal(4);
		param.setyGoal(4);
		param.setxStart(0);
		param.setyStart(0);
		
		return param;
	}

	public Grid solveCustomizedGrid(GridParameters param) {
		
		Grid grid = mazeCreator.createMaze(param);
		
		grid.setGoalReached(ExecuteAStar.execute(grid.getMaze(), param));
		return grid;
	}

	public Grid getBlankGrid(GridParameters blankGridParam) {
		Grid grid = mazeCreator.createBasicMaze(blankGridParam);
		return grid;
	}

	public Grid solveCreatedMaze(Grid grid, GridParameters gridParam) {
		mazeCreator.populateChildren(grid, gridParam);
		grid.setGoalReached(ExecuteAStar.execute(grid.getMaze(), gridParam));
		return grid;
	}

	public ArrayList<Grid> solveRepeatedAStar(GridParameters gridParam) {
		// TODO Auto-generated method stub
		int rows = gridParam.length;
		int columns = gridParam.breadth;
		int startX,startY,goalX,goalY;
		startX = gridParam.xStart;
		startY = gridParam.yStart;
		goalX = gridParam.xGoal;
		goalY = gridParam.yGoal;
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		MazeCreator mazeCreator = new MazeCreator(gridParam);
		maze = mazeCreator.getMaze();

		start = maze[startX][startY];
		goal = maze[goalX][goalY];
		
		RepeatedForwardAStar repeatedForwardAStar = new RepeatedForwardAStar(maze, start, goal);
		
		repeatedForwardAStar.executeAStar();

		grids = repeatedForwardAStar.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveAdaptiveAStar(GridParameters gridParam) {
		
		MazeCreator mazeCreator = new MazeCreator();
		Grid initialGrid = mazeCreator.createMaze(gridParam);

		ArrayList<Grid> allGrids = adaptive.solveAdaptiveAStar(initialGrid, gridParam);
		
		return allGrids;
	}
	
	
}
