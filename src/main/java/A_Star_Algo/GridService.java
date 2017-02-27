package A_Star_Algo;

import java.util.ArrayList;

import AStar_Darshan.RFAStar;
import AStar_Darshan.RFAStarWithLG;
import AStar_Darshan.RFAStarWithSG;
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

	public ArrayList<Grid> solveRFAStar(GridParameters gridParam) {
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		MazeCreator mazeCreator = new MazeCreator(gridParam);
		maze = mazeCreator.getMaze();

		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		RFAStar repeatedForwardAStar = new RFAStar(maze, start, goal);
		
		repeatedForwardAStar.executeAStar();

		grids = repeatedForwardAStar.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveCustomizedRFAStar(Grid grid, GridParameters gridParam) {
		
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		maze = grid.getMaze();
		
		maze = MazeCreator.getCopy(maze, gridParam);
		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		RFAStar repeatedForwardAStar = new RFAStar(maze, start, goal);
		repeatedForwardAStar.executeAStar();
		
		grids = repeatedForwardAStar.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveRFAStarWithLG(GridParameters gridParam) {
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		MazeCreator mazeCreator = new MazeCreator(gridParam);
		maze = mazeCreator.getMaze();

		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		RFAStarWithLG rfAStarWithLG = new RFAStarWithLG(maze, start, goal);
		rfAStarWithLG.executeAStar();

		grids = rfAStarWithLG.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveCustomizedRFAStarWithLG(Grid grid, GridParameters gridParam) {
		
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		maze = grid.getMaze();
		
		maze = MazeCreator.getCopy(maze, gridParam);
		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		System.out.println("Caalling LG.");
		RFAStarWithLG rfAStarWithLG = new RFAStarWithLG(maze, start, goal);
		rfAStarWithLG.executeAStar();
		
		grids = rfAStarWithLG.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveRFAStarWithSG(GridParameters gridParam) {
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		MazeCreator mazeCreator = new MazeCreator(gridParam);
		maze = mazeCreator.getMaze();

		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		RFAStarWithSG rfAStarWithSG = new RFAStarWithSG(maze, start, goal);
		rfAStarWithSG.executeAStar();

		grids = rfAStarWithSG.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveCustomizedRFAStarWithSG(Grid grid, GridParameters gridParam) {
		
		Cell[][] maze;
		Cell start,goal;
		ArrayList<Grid> grids;
		
		maze = grid.getMaze();
		
		maze = MazeCreator.getCopy(maze, gridParam);
		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		
		RFAStarWithSG rfAStarWithSG = new RFAStarWithSG(maze, start, goal);
		rfAStarWithSG.executeAStar();
		
		grids = rfAStarWithSG.getGrids();
		
		return grids;
	}
	
	public ArrayList<Grid> solveAdaptiveAStar(GridParameters gridParam) {
		
		MazeCreator mazeCreator = new MazeCreator();
		Grid initialGrid = mazeCreator.createMaze(gridParam);

		ArrayList<Grid> allGrids = adaptive.solveAdaptiveAStar(initialGrid, gridParam);
		
		return allGrids;
	}

	public ArrayList<Grid> solveCustomizedAdaptiveAStar(Grid grid, GridParameters gridParam) {
		mazeCreator.setXYandH(grid.getMaze(), gridParam);
		mazeCreator.generateChildren(grid.getMaze());
		ArrayList<Grid> allGrids = adaptive.solveAdaptiveAStar(grid, gridParam);
		return allGrids;
	}
	
	
	
	
}
