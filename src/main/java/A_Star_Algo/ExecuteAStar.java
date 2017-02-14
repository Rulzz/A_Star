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
		estimateMap.put(start.getHeuristic(), rootList);
		isReached = tracePath(estimateMap, goal);
		
		if(isReached) {
			grid.setGoalReached(true);
			//getFinalPath(grid.getMaze()[param.getxGoal()][param.getyGoal()], finalPath);
			setFinalPath(grid.getMaze()[param.getxGoal()][param.getyGoal()]);
		}
		
		return finalPath;
	}

	private static void setFinalPath(Cell cell) {
		cell.setOnFinalPath(true);
		if(cell.getParent()!=null) {
			setFinalPath(cell.getParent());
		}
		
	}

	private static boolean tracePath(Map<Integer, ArrayList<Cell>> estimateMap, Cell goal) {
		Boolean isReached = false;
		while(!(emptyValues(estimateMap.values()) || isReached)) {
			
			int leastEstimate = getLeastEstimate(estimateMap.keySet());
			Cell currentNode = estimateMap.get(leastEstimate).get(0);
			currentNode.setVisited(true);
			Cell bestChild = getBestChild(currentNode);
			if(bestChild==null) {
				estimateMap.get(currentNode.getHeuristic()+currentNode.getSteps()).remove(currentNode);
				if(estimateMap.get(currentNode.getHeuristic()+currentNode.getSteps()).isEmpty()) {
					estimateMap.remove(currentNode.getHeuristic()+currentNode.getSteps());
				}
			}
			else {
				bestChild.setParent(currentNode);
				
				if(bestChild.equals(goal)) {
					isReached=true;
					break;
				}
				
				bestChild.setSteps(bestChild.getParent().getSteps());
				
				System.out.println("Expanded : " + bestChild.getxCoordinate() + "," + bestChild.getyCoordinate()); 
				bestChild.setVisited(true);
				addRemoveEstimates(estimateMap, bestChild);
			}
			
		}
		return isReached;
	}

	private static boolean emptyValues(Collection<ArrayList<Cell>> values) {
		for(ArrayList<Cell> list : values) {
			if(!list.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	private static Cell getBestChild(Cell parent) {
		Integer leastStep = Integer.MAX_VALUE;
		Cell nextNode = null;
		
		for(Cell child : parent.getChildren()) {
			if(!child.isVisited()) {
				if((child.getHeuristic()+parent.getSteps())<leastStep) {
					leastStep = child.getHeuristic()+parent.getSteps();
					nextNode=child;
				}
			}
			
		}
		return nextNode;
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

	private static void addRemoveEstimates(Map<Integer, ArrayList<Cell>> estimateMap, Cell node) {
		
		int estimate = node.getSteps() + node.getHeuristic();
		
		if(estimateMap.get(estimate) == null) {
			estimateMap.put(estimate, new ArrayList<Cell>());
			estimateMap.get(estimate).add(node);
		} else {
			estimateMap.get(estimate).add(node);
		}
		
		boolean allChildrenVisited = true;
		for(Cell child : node.getParent().getChildren()) {
			if(!child.isVisited()) {
				allChildrenVisited=false;
				break;
			}
		}
		if(allChildrenVisited) {
			if(estimateMap.get(estimate) != null) {
				estimateMap.get(estimate).remove(node.getParent());
				if(estimateMap.get(estimate).isEmpty()) {
					estimateMap.remove(estimate);
				}
			}
		}
	}

	
	
	

}
