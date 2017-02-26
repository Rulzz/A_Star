package A_Star_Algo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Cell {
		
	private int xCoordinate;
	private int yCoordinate;
	private String XY;
	private boolean obstacle;
	private boolean visited;
	private int fValue;
	private int gValue;
	private int hValue;
	private int search;
	public Cell parent;
	public TreeSet<Cell> children;
	public ArrayList<Cell> childrenList;
	private boolean isStart;
	private boolean isEnd;
	private boolean onFinalPath;
	private int heuristic;
	private int steps;
	private int stepsTillNow;
	private int newHeuristic;
	private String direction="";
	
	private static final int C = 200000;
	
	public Cell() {
		// TODO Auto-generated constructor stub
	}
	
	public Cell (int x, int y){
		// Manually Generated
		setSearch(0);
		xCoordinate = x;
		yCoordinate = y;
		XY = Integer.toString(xCoordinate)+Integer.toString(yCoordinate);
	}

	public int getxCoordinate() {
		return xCoordinate;
	}
	
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	
	public String getXY() {
		return XY;
	}

	public void setXY(String xY) {
		XY = xY;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}
	
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}
	
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public int getfValueLargerG()
	{
		return C * getfValue() - gValue;
	}
	
	public int getfValueSmallerG()
	{
		return C * getfValue() + gValue;
	}
	
	public int getfValue() {
		computeFValue();
		return fValue;
	}
	
	private void computeFValue()
	{
		fValue = gValue + hValue;
	}
	
	public void setfValue(int fValue) {
		this.fValue = fValue;
	}

	public int getgValue() {
		return gValue;
	}
	
	public void setgValue(int gValue) {
		this.gValue = gValue;
	}

	public void setOnFinalPath(boolean onFinalPath) {
		this.onFinalPath = onFinalPath;
	}
	
	public boolean isOnFinalPath() {
		return onFinalPath;
	}

	public void computeHValue(Cell goal)
	{
		hValue = Math.abs(this.xCoordinate - goal.xCoordinate) + Math.abs(this.yCoordinate - goal.yCoordinate); // Manhattan distance
		this.sethValue(hValue);
	}
	
	public int gethValue() {
		return hValue;
	}
	
	public void sethValue(int hValue) {
		this.hValue = hValue;
	}
	
	public boolean equalsTo(Cell a)
	{
		return this.getxCoordinate() == a.getxCoordinate() && this.getyCoordinate() == a.getyCoordinate();
	}
	
	public int getSearch() {
		return search;
	}

	public void setSearch(int search) {
		this.search = search;
	}

	public TreeSet<Cell> getChildren() {
		return children;
	}
	
	public void setChildren(TreeSet<Cell> children) {
		this.children = children;
	}
	
	public void setChildrenList(Cell[][] maze, GridParameters param) {
		ArrayList<Cell> neighbors = new ArrayList<>();
		addIfAdmissible(neighbors, maze, this.getxCoordinate()-1, this.getyCoordinate(), param);
		addIfAdmissible(neighbors, maze, this.getxCoordinate()+1, this.getyCoordinate(), param);
		addIfAdmissible(neighbors, maze, this.getxCoordinate(), this.getyCoordinate()-1, param);
		addIfAdmissible(neighbors, maze, this.getxCoordinate(), this.getyCoordinate()+1, param);
		this.childrenList = neighbors;
	}
	
	private Cell addIfAdmissible(ArrayList<Cell> neighbors, Cell[][] maze, int x, int y, GridParameters param) {
		if(x>=0 && x < param.getLength() && y>=0 && y < param.getBreadth()) {
			neighbors.add(maze[x][y]);
		}
		return null;
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

	public int getNewHeuristic() {
		return newHeuristic;
	}

	public void setNewHeuristic(int newHeuristic) {
		this.newHeuristic = newHeuristic;
	}

	public ArrayList<Cell> getChildrenList() {
		return childrenList;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
