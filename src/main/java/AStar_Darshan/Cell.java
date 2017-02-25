package AStar_Darshan;

import java.util.TreeSet;

/**
 * @author darsh
 *
 */
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
	Cell parent;
	TreeSet<Cell> children;
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
}
