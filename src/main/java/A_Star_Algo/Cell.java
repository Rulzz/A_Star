package A_Star_Algo;
import java.util.ArrayList;


public class Cell {
	
	private int xCoordinate;
	private int yCoordinate;
	private boolean obstacle;
	private boolean visited;
	private boolean isStart;
	private boolean isEnd;
	private boolean onFinalPath;
	private int heuristic;
	private int steps;
	private int stepsTillNow;
	private Cell parent;
	private ArrayList<Cell> children;
	
	//Cell tree;
	
	public Cell() {
		// TODO Auto-generated constructor stub
	}
	
	public Cell (int x, int y){
		// Manually Generated
		xCoordinate = x;
		yCoordinate = y;
	}
	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}
	/**
	 * @param xCoordinate the xCoordinate to set
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}
	/**
	 * @param yCoordinate the yCoordinate to set
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	/**
	 * @return the obstacle
	 */
	public boolean isObstacle() {
		return obstacle;
	}
	/**
	 * @param obstacle the obstacle to set
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * @param visited the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	
	public ArrayList<Cell> getChildren() {
		return this.children;
	}

	

	public boolean isOnFinalPath() {
		return onFinalPath;
	}

	public void setOnFinalPath(boolean onFinalPath) {
		this.onFinalPath = onFinalPath;
	}

	private Cell addIfAdmissible(ArrayList<Cell> neighbors, Grid grid, int x, int y, GridParameters param) {
		if(x>=0 && x < param.getLength() && y>=0 && y < param.getBreadth() && !grid.getMaze()[x][y].isObstacle()) {
			neighbors.add(grid.getMaze()[x][y]);
		}
		return null;
	}

	public void setChildren(Grid grid, GridParameters param) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		addIfAdmissible(neighbors, grid, this.getxCoordinate()-1, this.getyCoordinate(), param);
		addIfAdmissible(neighbors, grid, this.getxCoordinate()+1, this.getyCoordinate(), param);
		addIfAdmissible(neighbors, grid, this.getxCoordinate(), this.getyCoordinate()-1, param);
		addIfAdmissible(neighbors, grid, this.getxCoordinate(), this.getyCoordinate()+1, param);
		this.children = (neighbors);
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}

	public Cell getParent() {
		return parent;
	}

	public void setParent(Cell parent) {
		this.parent = parent;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	
	public int getStepsTillNow() {
		return stepsTillNow;
	}

	public void setStepsTillNow(int stepsTillNow) {
		this.stepsTillNow = stepsTillNow;
	}

	public boolean equals(Cell cell) {
	    return (this.getxCoordinate()== cell.getxCoordinate() && this.getyCoordinate()== cell.getyCoordinate());
	}
	
	
}
