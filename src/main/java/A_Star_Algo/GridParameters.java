package A_Star_Algo;

public class GridParameters {
	public int length;
	public int breadth;
	public int xStart;
	public int yStart;
	public int xGoal;
	public int yGoal;
	
	public GridParameters()
	{
		//Empty Constructor;
	}
	
	// Created by Darshan.
	public GridParameters(int length,int breadth,int xStart, int yStart, int xGoal, int yGoal)
	{
		this.length = length;
		this.breadth = breadth;
		this.xStart = xStart;  
		this.yStart = yStart;
		this.xGoal = xGoal;
		this.yGoal = yGoal;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getBreadth() {
		return breadth;
	}
	public void setBreadth(int breadth) {
		this.breadth = breadth;
	}
	public int getxStart() {
		return xStart;
	}
	public void setxStart(int xStart) {
		this.xStart = xStart;
	}
	public int getyStart() {
		return yStart;
	}
	public void setyStart(int yStart) {
		this.yStart = yStart;
	}
	public int getxGoal() {
		return xGoal;
	}
	public void setxGoal(int xGoal) {
		this.xGoal = xGoal;
	}
	public int getyGoal() {
		return yGoal;
	}
	public void setyGoal(int yGoal) {
		this.yGoal = yGoal;
	}
	
	
}
