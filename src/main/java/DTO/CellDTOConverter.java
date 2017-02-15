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
		cellDTO.setSteps(cell.getSteps());
		cellDTO.setStepsTillNow(cell.getStepsTillNow());
		return cellDTO;
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
