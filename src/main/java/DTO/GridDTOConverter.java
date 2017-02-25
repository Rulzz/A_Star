package DTO;

import java.util.ArrayList;
import java.util.TreeSet;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;

public class GridDTOConverter {
	CellDTOConverter cellDTOConverter = new CellDTOConverter();
	public GridDTO convert (Grid grid) {
		
		Cell[][] maze = grid.getMaze();
		CellDTO[][] mazeDto = new CellDTO[maze.length][maze[1].length];
		for(int i=0; i<maze.length; i++) {
	        for(int j=0; j<maze[i].length; j++) {
	        	mazeDto[i][j] = cellDTOConverter.convert(maze[i][j], getDirection(maze[i][j], grid));
	        }
	    }
		
		GridDTO gridDTO = new GridDTO();
		gridDTO.setMaze(mazeDto);
		gridDTO.setGoalReached(grid.isGoalReached());
		return gridDTO;
	}
	
private String getDirection(Cell cell, Grid grid) {
	if(cell.getyCoordinate()-1>0 && grid.getMaze()[cell.getxCoordinate()][cell.getyCoordinate()-1].isOnFinalPath()) {
		 return CellDTO.Direction.Right.name();
	} else if(cell.getyCoordinate()+1<grid.getMaze()[1].length && grid.getMaze()[cell.getxCoordinate()][cell.getyCoordinate()+1].isOnFinalPath()) {
		 return CellDTO.Direction.Left.name();
	} else if(cell.getxCoordinate()-1>0 && grid.getMaze()[cell.getxCoordinate()-1][cell.getyCoordinate()].isOnFinalPath()) {
		 return CellDTO.Direction.Down.name();
	} else if(cell.getxCoordinate()+1<grid.getMaze().length && grid.getMaze()[cell.getxCoordinate()+1][cell.getyCoordinate()].isOnFinalPath()) {
		 return CellDTO.Direction.Up.name();
	}
		
	return null;
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

public ArrayList<GridDTO> convert(ArrayList<Grid> gridList) {
		ArrayList<GridDTO> gridListDto = new ArrayList<>();
		for(Grid grid : gridList) {
			gridListDto.add(convert(grid));
		}
		return gridListDto;
	}
}
