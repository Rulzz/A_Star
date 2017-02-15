package DTO;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;

public class GridDTOConverter {
	CellDTOConverter cellDTOConverter = new CellDTOConverter();
	public GridDTO convert (Grid grid) {
		
		Cell[][] maze = grid.getMaze();
		CellDTO[][] mazeDto = new CellDTO[maze.length][maze[1].length];
		for(int i=0; i<maze.length; i++) {
	        for(int j=0; j<maze[i].length; j++) {
	        	mazeDto[i][j] = cellDTOConverter.convert(maze[i][j], maze[i][j].getParent());
	        }
	    }
		
		GridDTO gridDTO = new GridDTO();
		gridDTO.setMaze(mazeDto);
		gridDTO.setGoalReached(grid.isGoalReached());
		return gridDTO;
	}
	
public Grid convert (GridDTO gridDTO) {
		
		CellDTO[][] mazeDTO = gridDTO.getMaze();
		Cell[][] maze = new Cell[mazeDTO.length][mazeDTO[1].length];
		for(int i=0; i<mazeDTO.length; i++) {
	        for(int j=0; j<mazeDTO[i].length; j++) {
	        	maze[i][j] = cellDTOConverter.convert(mazeDTO[i][j]);
	        }
	    }
		
		Grid grid = new Grid();
		grid.setMaze(maze);
		return grid;
	}
}
