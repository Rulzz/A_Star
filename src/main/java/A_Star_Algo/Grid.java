package A_Star_Algo;

public class Grid {
		
	private Cell[][] maze;
	private boolean goalReached;
	
	public Cell[][] getMaze() {
		return maze;
	}
	public void setMaze(Cell[][] maze) {
		this.maze = maze;
	}
	public boolean isGoalReached() {
		return goalReached;
	}
	public void setGoalReached(boolean goalReached) {
		this.goalReached = goalReached;
	}
	
	
}
