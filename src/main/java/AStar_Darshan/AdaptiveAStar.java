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
		
		Cell start = discoveredMaze[param.getxStart()][param.getyStart()];
		start.setVisited(true);
		
		
		//set heuristic on initial discovered maze
		
		ArrayList<Cell> openList = new ArrayList<>();
		openList.add(start);
		
		while(!openList.isEmpty()) {
			boolean isReached =getAdaptiveStep(initialMaze, openList, discoveredMaze, param);
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

	private boolean getAdaptiveStep(Cell[][] initialMaze, ArrayList<Cell> openList, Cell[][] discoveredMaze, GridParameters param) {
		isAstarExecuted=false;
		Cell toExpand = getBestCellToExpand(openList);
		System.out.println("Expanding cell : " + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
		
		if (toExpand.getxCoordinate()==param.getxGoal() && toExpand.getyCoordinate()==param.getyGoal()) {
			openList.clear();
			return true;
		}
		
		for(Cell child : toExpand.getChildrenList()) {   //gets all the children including blocks
			
			if(discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].isVisited()) {
				continue;
			}
			
			
			if(initialMaze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
					discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
			}
			child.setParent(toExpand);
			openList.add(child);	
		}
		
		if(toExpand.isObstacle()) {
			//run Astar on discovered maze
			GridParameters newStartParam = new GridParameters();
			newStartParam=param;
			newStartParam.setxStart(toExpand.getParent().getxCoordinate());
			newStartParam.setyStart(toExpand.getParent().getyCoordinate());
			newStartParam.setxGoal(param.getxGoal());
			newStartParam.setyGoal(param.getyGoal());
			isAstarExecuted=true;
			Cell[][] mazeCopy = mazeCreator.getMazeCopy(discoveredMaze, true, true);//add goal start
			return aStar.execute(mazeCopy, param);
		}
		
		//tracingMaze[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setOnFinalPath(true);
		//tracingMaze[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setVisited(true);
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
