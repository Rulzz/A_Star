package DTO;

public class CellDTO {
	
	private int xCoordinate;
	private int yCoordinate;
	private int heuristic;
	private String cellStatus;
	private int steps;
	private int stepsTillNow;
	private String direction;
	private String style;
	private String image;
	
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
	

	public int getStepsTillNow() {
		return stepsTillNow;
	}
	public void setStepsTillNow(int stepsTillNow) {
		this.stepsTillNow = stepsTillNow;
	}
	

	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	public enum Status {
		Start, Goal, Block, Visited, FinalPath, Blank
	}
	public enum Direction {
		Right, Left, Up, Down
	}
}
