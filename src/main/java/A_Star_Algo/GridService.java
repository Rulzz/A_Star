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
		param.setLength(5);
		param.setBreadth(5);
		param.setxGoal(4);
		param.setyGoal(4);
		param.setxStart(0);
		param.setyStart(0);
		
		return param;
	}

	public Grid solveCustomizedGrid(GridParameters param) {
		
		Grid grid = mazeCreator.createMaze(param);
		
		ExecuteAStar.execute(grid, param);
		return grid;
	}

	public Grid getBlankGrid(GridParameters blankGridParam) {
		Grid grid = mazeCreator.createBasicMaze(blankGridParam);
		return grid;
	}

	public Grid solveCreatedMaze(Grid grid, GridParameters gridParam) {
		mazeCreator.populateChildren(grid, gridParam);
		ExecuteAStar.execute(grid, gridParam);
		return grid;
	}
}
