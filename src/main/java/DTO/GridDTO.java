package DTO;

import java.util.ArrayList;


public class GridDTO {
	
	private CellDTO[][] maze;
	private boolean goalReached;
	public CellDTO[][] getMaze() {
		return maze;
	}
	public void setMaze(CellDTO[][] maze) {
		this.maze = maze;
	}
	public boolean isGoalReached() {
		return goalReached;
	}
	public void setGoalReached(boolean goalReached) {
		this.goalReached = goalReached;
	}
}
