package AStar_Darshan;

import java.util.ArrayList;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

public class AdaptiveAStar {
	
	MazeCreator mazeCreator = new MazeCreator();
	AStar aStar = new AStar();
	
	private boolean isAstarExecuted = false;
	
	public ArrayList<Grid> solveAdaptiveAStar(Grid grid, GridParameters param) {
		
		ArrayList<Grid> allGrids = new ArrayList<>();
		allGrids.add(grid);
		Cell[][] initialMaze = grid.getMaze();
		Cell[][] discoveredMaze = mazeCreator.getCopyWithoutObstacle(initialMaze);
		ArrayList<Cell> finalPath = new ArrayList<>();
		
		Cell start = grid.getMaze()[param.getxStart()][param.getyStart()];
		start.setVisited(true);
		
		
		//set heuristic on initial discovered maze
		
		ArrayList<Cell> openList = new ArrayList<>();
		openList.add(start);
		
		while(!openList.isEmpty()) {
			boolean isReached =getAdaptiveStep(finalPath, openList, initialMaze, discoveredMaze, param);
			if(isAstarExecuted) {
				Grid discoveredGrid = new Grid();
				discoveredGrid.setMaze(discoveredMaze);
				discoveredGrid.setGoalReached(isReached);
				allGrids.add(discoveredGrid);
				updateDiscoveredMaze(discoveredMaze, param);
			}
		}
		
		return allGrids;
	}

	private boolean getAdaptiveStep(ArrayList<Cell> finalPath, ArrayList<Cell> openList, Cell[][] initialMaze, Cell[][] discoveredMaze, GridParameters param) {
		isAstarExecuted=false;
		Cell toExpand = getBestCellToExpand(openList);
		
		finalPath.add(toExpand);
		if (toExpand.getxCoordinate()==param.getxGoal() && toExpand.getyCoordinate()==param.getyGoal()) {
			openList.clear();
			return true;
		}
		
		for(Cell child : toExpand.getChildrenList()) {   //gets all the children including blocks
			child.setParent(toExpand);
			if(child.isObstacle()) {
				discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
			} else {
				openList.add(child);
			}
		}
		
		if(toExpand.isObstacle()) {
			//run Astar on discovered maze
			GridParameters newStartParam = new GridParameters();
			newStartParam=param;
			newStartParam.setxStart(toExpand.getParent().getxCoordinate());
			newStartParam.setyStart(toExpand.getParent().getyCoordinate());
			isAstarExecuted=true;
			return aStar.execute(discoveredMaze, param);
		}
		
		toExpand.setOnFinalPath(true);
		openList.remove(toExpand);
		
		return false;
		
	}

	private void updateDiscoveredMaze(Cell[][] discoveredMaze,GridParameters param) {
		for (int i = 0; i < discoveredMaze.length; i++) {
			for (int j = 0; j < discoveredMaze[1].length; j++) {
				discoveredMaze[i][j].setNewHeuristic(discoveredMaze[param.getxGoal()][param.getyGoal()].getSteps() -discoveredMaze[i][j].getSteps());
				discoveredMaze[i][j].setHeuristic(discoveredMaze[i][j].getNewHeuristic());
				discoveredMaze[i][j].setParent(null);
				discoveredMaze[i][j].setSteps(0);
				discoveredMaze[i][j].setStepsTillNow(0);
				discoveredMaze[i][j].setOnFinalPath(false);
			}
		}
		
	}

	private Cell getBestCellToExpand(ArrayList<Cell> openList) {
		int lowestH = Integer.MAX_VALUE;
		Cell lowestHCell = new Cell();
		for(Cell cell : openList) {
			if(cell.getHeuristic()<lowestH) {
				lowestH = cell.getHeuristic();
				lowestHCell=cell;
			}
		}
		return lowestHCell;
	}
	
	
}
