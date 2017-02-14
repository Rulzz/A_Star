package DTO;

public class CellDTO {
	
	private int xCoordinate;
	private int yCoordinate;
	private int heuristic;
	private String cellStatus;
	private int steps;
	private String direction;
	public int getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public int getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	public String getCellStatus() {
		return cellStatus;
	}
	public void setCellStatus(String cellStatus) {
		this.cellStatus = cellStatus;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public enum Status {
		Start, Goal, Block, Visited, FinalPath, Blank
	}
	public enum Direction {
		Right, Left, Up, Down
	}
}
