package DTO;

import A_Star_Algo.Cell;

public class CellDTOConverter {
	public CellDTO convert (Cell cell, Cell parent) { 
		CellDTO cellDTO = new CellDTO();
		cellDTO.setHeuristic(cell.getHeuristic());
		cellDTO.setxCoordinate(cell.getxCoordinate());
		cellDTO.setyCoordinate(cell.getyCoordinate());
		cellDTO.setCellStatus(setCellStatus(cell));
		cellDTO.setDirection(getDirection(cell,parent));
		return cellDTO;
	}

	private String getDirection(Cell cell, Cell parent) {
		if(parent!=null && cell.isOnFinalPath()) {
			if(parent.getyCoordinate()<cell.getyCoordinate()) {
				 return CellDTO.Direction.Right.name();
			} else if(parent.getyCoordinate()>cell.getyCoordinate()) {
				 return CellDTO.Direction.Left.name();
			} else if(parent.getxCoordinate()<cell.getxCoordinate()) {
				 return CellDTO.Direction.Down.name();
			} else if(parent.getxCoordinate()>cell.getxCoordinate()) {
				 return CellDTO.Direction.Up.name();
			}
		}
		return "";
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
