package DTO;

import A_Star_Algo.Cell;

public class CellDTOConverter {
	public CellDTO convert (Cell cell, Cell[][] originalMaze) { 
		CellDTO cellDTO = new CellDTO();
		cellDTO.setHeuristic(cell.getHeuristic());
		cellDTO.setxCoordinate(cell.getxCoordinate());
		cellDTO.setyCoordinate(cell.getyCoordinate());
		cellDTO.setCellStatus(setCellStatus(cell));
		cellDTO.setDirection(cell.getDirection().isEmpty()?CellDTO.Direction.Up.name() : cell.getDirection());
		cellDTO.setSteps(cell.getSteps());
		cellDTO.setStepsTillNow(cell.getStepsTillNow());
		cellDTO.setStyle(setStyle(cell, originalMaze));
		cellDTO.setImage(setImage(cell, originalMaze));
		return cellDTO;
	}
	
	private String setImage(Cell cell, Cell[][] originalMaze) {
		if(cell.isStart()) {
			return "Start";
		} else if(cell.isEnd()) {
			return "Goal";
		} else if(cell.isOnFinalPath()) {
			if(originalMaze[cell.getxCoordinate()][cell.getyCoordinate()].isObstacle()) {
				return "BlockVisit";
			} else {
				return cell.getDirection().isEmpty()?CellDTO.Direction.Up.name() : cell.getDirection();
			}
		} else if(cell.isObstacle()) {
			return "Block";
		} else if (cell.isVisited()) {
			return "Visited";
		} else {
			return "Blank";
		}
		
	}

	private String setStyle(Cell cell, Cell[][] originalMaze) {
		if(cell.isStart()) {
			return "{'background-color' : 'blue','border' : '1px solid black','height' : '50px','width' : '50px'}";
		} else if(cell.isEnd()) {
			return "{'background-color' : 'red','border' : '1px solid black','height' : '50px','width' : '50px'}";
		} else if(cell.isOnFinalPath()) {
			if(originalMaze[cell.getxCoordinate()][cell.getyCoordinate()].isObstacle()) {
				return "{'background-color' : 'DarkGreen','border' : '1px solid black','height' : '50px','width' : '50px'}";
			} else {
				return "{'background-color' : 'LimeGreen','border' : '1px solid black','height' : '50px','width' : '50px'}";
			}
		} else if(cell.isObstacle()) {
			return "{'background-color' : 'black','border' : '1px solid white','height' : '50px','width' : '50px'}";
		} else if (cell.isVisited()) {
			return "{'background-color' : 'yellow','border' : '1px solid black','height' : '50px','width' : '50px'}";
		} else {
			return "{'border' : '1px solid black','height' : '50px','width' : '50px'}";
		}
	}
	

	public Cell convert (CellDTO cellDTO) { 
		Cell cell = new Cell();
		cell.setHeuristic(cellDTO.getHeuristic());
		cell.setxCoordinate(cellDTO.getxCoordinate());
		cell.setyCoordinate(cellDTO.getyCoordinate());
		cell.setEnd(cellDTO.getCellStatus().equals(CellDTO.Status.Goal.name()));
		cell.setObstacle(cellDTO.getCellStatus().equals(CellDTO.Status.Block.name()));
		cell.setStart(cellDTO.getCellStatus().equals(CellDTO.Status.Start.name()));
		return cell;
	}

	private String setCellStatus(Cell cell) {
		if(cell.isStart()) {
			return CellDTO.Status.Start.name();
		} else if(cell.isEnd()) {
			return CellDTO.Status.Goal.name();
		} else if(cell.isObstacle()) {
			return CellDTO.Status.Block.name();
		} else if(cell.isOnFinalPath()) {
			return CellDTO.Status.FinalPath.name();
		} else if (cell.isVisited()) {
			return CellDTO.Status.Visited.name();
		} else {
			return CellDTO.Status.Blank.name();
		}
	}
}
