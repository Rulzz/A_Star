package A_Star_Algo;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.jersey.spi.monitoring.ResponseListenerAdapter;

import AStar_Darshan.AStar;
import AStar_Darshan.MazeCreator;

public class GridService {
	
	MazeCreator mazeCreator = new MazeCreator();
	
	public Grid solveDefaultGrid() {
		GridParameters defaultParam = getDefaultGridParameters();
		Grid grid = mazeCreator.createMaze(defaultParam);
		
		ExecuteAStar.execute(grid, defaultParam);
		
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
		
		ExecuteAStar.execute(grid, param);
		return grid;
	}

	public Grid getBlankGrid(GridParameters blankGridParam) {
		Grid grid = mazeCreator.createBasicMaze(blankGridParam);
		return grid;
	}

	public Grid solveCreatedMaze(Grid grid, GridParameters gridParam) {
		mazeCreator.populateChildren(grid, gridParam);
		ExecuteAStar.execute(grid, gridParam);
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
		
		MazeCreator mazeCreator = new MazeCreator(rows, columns);
		maze = mazeCreator.getMaze();

		start = maze[startX][startY];
		goal = maze[goalX][goalY];
		
		AStar repeatedForwardAStar = new AStar(maze, start, goal);
		
		repeatedForwardAStar.executeAStar();

		grids = repeatedForwardAStar.getGrids();
		
		return null;
	}
	
	
}
