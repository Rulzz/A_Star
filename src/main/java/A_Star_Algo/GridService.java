package A_Star_Algo;

public class GridService {
	
	MazeCreator mazeCreator = new MazeCreator();
	
	public Grid solveDefaultGrid() {
		GridParameters defaultParam = getDefaultGridParameters();
		Grid grid = mazeCreator.createMaze(defaultParam);
		
		ExecuteAStar.execute(grid, defaultParam);
		
		return grid;
	}

	public GridParameters getDefaultGridParameters() {
		GridParameters param = new GridParameters();
		param.setLength(20);
		param.setBreadth(20);
		param.setxGoal(9);
		param.setyGoal(8);
		param.setxStart(0);
		param.setyStart(0);
		
		return param;
	}
}
