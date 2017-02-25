package A_Star_Algo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ExecuteAStar {

	public static ArrayList<Cell> execute(Grid grid, GridParameters param) {
		
		Cell start = grid.getMaze()[param.getxStart()][param.getyStart()];
		Cell goal = new Cell();
		goal.setxCoordinate(param.getxGoal());
		goal.setyCoordinate(param.getyGoal());
		ArrayList<Cell> finalPath = new ArrayList<>();
		Boolean isReached = false;
		Map<Integer, ArrayList<Cell>> estimateMap = new HashMap<>(); 
		ArrayList<Cell> rootList = new ArrayList<>();
		rootList.add(start);
		start.setVisited(true);
		estimateMap.put(start.getHeuristic(), rootList);
		isReached = tracePath(estimateMap, goal);
		
		if(isReached) {
			grid.setGoalReached(true);
			setFinalPath(grid.getMaze()[param.getxGoal()][param.getyGoal()]);
		}
		
		return finalPath;
	}

	private static void setFinalPath(Cell cell) {
		cell.setOnFinalPath(true);
		if(cell.getParent()!=null) {
			System.out.println("final Path of : " + cell.getxCoordinate() + "," + cell.getyCoordinate()); 
			setFinalPath(cell.getParent());
		}
		
	}

	private static boolean tracePath(Map<Integer, ArrayList<Cell>> estimateMap, Cell goal) {
		Boolean isReached = false;
		Integer stepsTillNow = 1;
		while(!(emptyValues(estimateMap.values()) || isReached)) {
			
			int leastEstimate = getLeastEstimate(estimateMap.keySet());
			Cell currentNode =getCellWithLessHeuristic(estimateMap.get(leastEstimate));
			
			if(currentNode.equals(goal)) {
				isReached=true;
				break;
			}
			
			System.out.println("Expanded : " + currentNode.getxCoordinate() + "," + currentNode.getyCoordinate()); 
			currentNode.setVisited(true);
			currentNode.setStepsTillNow(stepsTillNow);
			addToEstimates(estimateMap, currentNode);
			estimateMap.get(currentNode.getSteps() + currentNode.getHeuristic()).remove(currentNode);
			}
			stepsTillNow = stepsTillNow+1;
		
		return isReached;
	}

	private static Cell getCellWithLessHeuristic(ArrayList<Cell> equallyProbable) {
		Integer leastH = Integer.MAX_VALUE;
		Cell leastHeu = new Cell();
		for(Cell cell : equallyProbable) {
			if(cell.getHeuristic()<leastH) {
				leastH = cell.getHeuristic();
				leastHeu=cell;
			}
		}
		return leastHeu;
	}

	private static boolean emptyValues(Collection<ArrayList<Cell>> values) {
		for(ArrayList<Cell> list : values) {
			if(!list.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private static Integer getLeastEstimate(Collection<Integer> values) {
		Integer least = Integer.MAX_VALUE;
		for(Integer value : values) {
			if(value<least) {
				least=value;
			}
		}
		return least;
	}

	private static void addToEstimates(Map<Integer, ArrayList<Cell>> estimateMap, Cell parent) {
		int estimate;
		for(Cell child : parent.getChildren()) {
			if(!child.isVisited()) {
				child.setParent(parent);
				child.setSteps(parent.getSteps()+1);
				estimate = child.getSteps() + child.getHeuristic();
				if(estimateMap.get(estimate)==null) {
					ArrayList<Cell> cells = new ArrayList<>();
					cells.add(child);
					estimateMap.put(estimate, cells);
				} else {
					estimateMap.get(estimate).add(child);
				}
			}
		}
	}

	
	
	

}
