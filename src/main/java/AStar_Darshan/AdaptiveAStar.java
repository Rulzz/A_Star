package AStar_Darshan;

import java.util.ArrayList;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

public class AdaptiveAStar {
	
	MazeCreator mazeCreator = new MazeCreator();
	AStar aStar = new AStar();
	
	private boolean isGoalReached = false;
	
	public ArrayList<Grid> solveAdaptiveAStar(Grid grid, GridParameters param) {
		
		ArrayList<Grid> allGrids = new ArrayList<>();
		allGrids.add(grid);
		Cell[][] initialMaze = grid.getMaze();
		Cell[][] discoveredMaze = mazeCreator.getMazeCopy(initialMaze, false, true);
		ArrayList<Cell> finalPath = new ArrayList<>();
		
		Cell start = discoveredMaze[param.getxStart()][param.getyStart()];
		start.setVisited(true);
		
		
		//set heuristic on initial discovered maze
		
		ArrayList<Cell> openList = new ArrayList<>();
		openList.add(start);
		
		while(!openList.isEmpty() && !isGoalReached) {
			getAdaptiveStep(initialMaze, openList, discoveredMaze, param, allGrids);
		}
		Grid intermediateGrid = new Grid();
		intermediateGrid.setMaze(discoveredMaze);
		intermediateGrid.setGoalReached(isGoalReached);
		allGrids.add(intermediateGrid);
		return allGrids;
	}

	private void getAdaptiveStep(Cell[][] initialMaze, ArrayList<Cell> openList, Cell[][] discoveredMaze, GridParameters param, ArrayList<Grid> allGrids) {
		Cell toExpand = getBestCellToExpand(openList);
		
		if (toExpand.getxCoordinate()==param.getxGoal() && toExpand.getyCoordinate()==param.getyGoal()) {
			openList.clear();
			System.out.println("GOAL REACHED!");
			isGoalReached=true;
			return;
		}
		
		if(toExpand.isObstacle()) {
			System.out.println("--------------------");
			System.out.println("BLOCK!! Inside A Star" + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
			Cell[][] mazeCopy = mazeCreator.getMazeCopy(discoveredMaze, true, true);
			mazeCopy[param.getxGoal()][param.getyGoal()].setEnd(true);
			mazeCopy[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setEnd(true);
			boolean isAstarReached = aStar.execute(mazeCopy, getParamCopy(toExpand, param));
			updateDiscoveredMaze(mazeCopy, discoveredMaze, param);
			
			Grid intermediateGrid = new Grid();
			intermediateGrid.setMaze(mazeCopy);
			intermediateGrid.setGoalReached(isAstarReached);
			allGrids.add(intermediateGrid);
			
		} else {
			System.out.println("--------------------");
			System.out.println("Expanding cell : " + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
			
			for(Cell child : toExpand.getChildren()) {
				
				if(discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].isVisited()) {
					continue;
				}
				
				
				if(initialMaze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
						discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
				}
				child.setParent(toExpand);
				System.out.println("Added cell to open list : " + child.getxCoordinate() + "," + child.getyCoordinate());
				openList.add(child);
			}
			discoveredMaze[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setOnFinalPath(true);
			discoveredMaze[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setVisited(true);
		}
		
		
		System.out.println("Removed cell to open list : " + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
		openList.remove(toExpand);
		
	}

	private GridParameters getParamCopy(Cell toExpand, GridParameters param) {
		GridParameters newStartParam= new GridParameters();
		newStartParam.setxStart(toExpand.getParent().getxCoordinate());
		newStartParam.setyStart(toExpand.getParent().getyCoordinate());
		newStartParam.setxGoal(param.getxGoal());
		newStartParam.setyGoal(param.getyGoal());
		newStartParam.setBreadth(param.getBreadth());
		newStartParam.setLength(param.getLength());
		return newStartParam;
	}

	private void updateDiscoveredMaze(Cell[][] aStarMaze, Cell[][] discoveredMaze,GridParameters param) {
		for (int i = 0; i < discoveredMaze.length; i++) {
			for (int j = 0; j < discoveredMaze[1].length; j++) {
				discoveredMaze[i][j].setNewHeuristic(aStarMaze[param.getxGoal()][param.getyGoal()].getSteps() - aStarMaze[i][j].getSteps());
				discoveredMaze[i][j].sethValue(discoveredMaze[i][j].getNewHeuristic());
				if(discoveredMaze[i][j].isObstacle()) {
					discoveredMaze[i][j].sethValue(Integer.MAX_VALUE);
				}
			}
		}
		
	}

	private Cell getBestCellToExpand(ArrayList<Cell> openList) {
		int lowestH = Integer.MAX_VALUE;
		Cell lowestHCell = new Cell();
		for(Cell cell : openList) {
			if(cell.gethValue()<lowestH) {
				lowestH = cell.gethValue();
				lowestHCell=cell;
			}
		}
		return lowestHCell;
	}
	
	
}
