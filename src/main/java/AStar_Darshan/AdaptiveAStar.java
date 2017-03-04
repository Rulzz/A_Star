package AStar_Darshan;

import java.util.ArrayList;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

public class AdaptiveAStar {
	
	MazeCreator mazeCreator = new MazeCreator();
	AStar aStar = new AStar();
	
	private boolean isGoalReached = false;
	private boolean goalUnreachable = false;
	
	public ArrayList<Grid> solveAdaptiveAStar(Grid grid, GridParameters param) {
		
		ArrayList<Grid> allGrids = new ArrayList<>();
		allGrids.add(grid);
		Cell[][] initialMaze = grid.getMaze();
		Cell[][] discoveredMaze = mazeCreator.getMazeCopy(initialMaze, false, true);
		ArrayList<Cell> aStarPath = new ArrayList<>();
		ArrayList<Cell> finalPath =new ArrayList<Cell>();
		
		Cell start = discoveredMaze[param.getxStart()][param.getyStart()];
		start.setVisited(true);
		for(Cell child : start.getChildren()) {
			if(initialMaze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
					discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
			}
		}
		
		finalPath.add(start);
		Cell[][] mazeCopy = mazeCreator.getMazeCopy(discoveredMaze, true, true);
		mazeCopy[param.getxGoal()][param.getyGoal()].setEnd(true);
		mazeCopy[param.getxStart()][param.getyStart()].setStart(true);
		boolean isAstarReached = aStar.execute(mazeCopy, param);
		if(!isAstarReached) {
			goalUnreachable = true;
		}
		aStarPath.addAll(aStar.getPath());
		updateDiscoveredMaze(mazeCopy, discoveredMaze, param);
		
		while(!isGoalReached && !goalUnreachable) {
			getAdaptiveStep(initialMaze, finalPath, discoveredMaze, param, allGrids, aStarPath);
		}
		Grid intermediateGrid = new Grid();
		mazeCreator.traceFinalPath(discoveredMaze, finalPath);
		mazeCreator.setPathDirection(discoveredMaze, aStarPath);
		intermediateGrid.setMaze(discoveredMaze);
		intermediateGrid.setGoalReached(isGoalReached);
		allGrids.add(intermediateGrid);
		return allGrids;
	}

	private void getAdaptiveStep(Cell[][] initialMaze, ArrayList<Cell> finalPath, Cell[][] discoveredMaze, GridParameters param, ArrayList<Grid> allGrids, ArrayList<Cell> aStarPath) {
		/*System.out.println("--------------------");
		
		
		System.out.println("final path : " + printOpenList(finalPath));
		System.out.println("AStarPath path : " + printOpenList(aStarPath));
		*/
		Cell toExpand;
		toExpand = aStarPath.get(finalPath.size());
		//System.out.println("Expanding cell : " + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
		
		if (toExpand.getxCoordinate()==param.getxGoal() && toExpand.getyCoordinate()==param.getyGoal()) {
			//System.out.println("GOAL REACHED!");
			isGoalReached=true;
			return;
		}
		
		if(toExpand.isObstacle()) {
			//System.out.println("BLOCK!! Inside A Star" + toExpand.getxCoordinate() + "," + toExpand.getyCoordinate());
			Cell[][] mazeCopy = mazeCreator.getMazeCopy(discoveredMaze, true, true);
			Cell newStart = aStarPath.get(finalPath.size()-1);
			//System.out.println("New Start cell : " + newStart.getxCoordinate() + "," + newStart.getyCoordinate());
			mazeCopy[param.getxGoal()][param.getyGoal()].setEnd(true);
			mazeCopy[newStart.getxCoordinate()][newStart.getyCoordinate()].setStart(true);
			//mazeCreator.display(mazeCopy);
			boolean isAstarReached = aStar.execute(mazeCopy, getParamCopy(newStart, param));
			//System.out.println("AStarPath path : " + printOpenList( aStar.getPath()));
			if(isAstarReached) {
				aStarPath = updateAStarPath(aStarPath, finalPath, aStar.getPath());
				mazeCreator.traceFinalPath(mazeCopy, aStarPath);
				mazeCreator.setPathDirection(mazeCopy, aStarPath);
				//finalPath.add(aStarPath.get(finalPath.size()));
				updateDiscoveredMaze(mazeCopy, discoveredMaze, param);
				
				Grid intermediateGrid = new Grid();
				intermediateGrid.setMaze(mazeCopy);
				intermediateGrid.setGoalReached(isAstarReached);
				allGrids.add(intermediateGrid);
			} else {
				goalUnreachable = true;
				//finalPath=aStarPath;
			}
			
			
		} else {
			finalPath.add(toExpand);
			
			for(Cell child : toExpand.getChildren()) {
				
				if(initialMaze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
						discoveredMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
						child.setObstacle(true);
						//System.out.println("Discovered blocked cell : " + child.getxCoordinate() + "," + child.getyCoordinate());
				}
			}
			discoveredMaze[toExpand.getxCoordinate()][toExpand.getyCoordinate()].setVisited(true);
		}
		
		
		//System.out.println("New final path : " + printOpenList(finalPath)); 
		
	}

	private ArrayList<Cell> updateAStarPath(ArrayList<Cell> oldPath, ArrayList<Cell> finalPath, ArrayList<Cell> newPath) {
		oldPath.clear();
		oldPath.addAll(finalPath);
		oldPath.remove(finalPath.size()-1);
		oldPath.addAll(newPath);
		return oldPath;
	}

	private String printOpenList(ArrayList<Cell> openList) {
		String openListString = "";
		for(Cell cell : openList) {
			openListString = openListString + " " + cell.getxCoordinate() + "," + cell.getyCoordinate() + ";";
		}
		return openListString;
	}

	private GridParameters getParamCopy(Cell start, GridParameters param) {
		GridParameters newStartParam= new GridParameters();
		newStartParam.setxStart(start.getxCoordinate());
		newStartParam.setyStart(start.getyCoordinate());
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
