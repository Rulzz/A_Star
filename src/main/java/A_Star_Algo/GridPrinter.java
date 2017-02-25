package A_Star_Algo;

import AStar_Darshan.MazeCreator;

public class GridPrinter {
	
	public static void main(String[] args) {
		MazeCreator mazeCreator = new MazeCreator();
		GridService service = new GridService();
		GridParameters param = service.getDefaultGridParameters();
		Grid grid = mazeCreator.createMaze(param);
		
		ExecuteAStar.execute(grid, service.getDefaultGridParameters());
		
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				String status = " ";
				if(grid.getMaze()[x][y].isStart()) {
					status = "S";
				} else if(grid.getMaze()[x][y].isEnd()) {
					status = "G";
				} else if(grid.getMaze()[x][y].isObstacle()) {
					status = "X";
				} else if(grid.getMaze()[x][y].isOnFinalPath()) {
					status = "*";
				} else if (grid.getMaze()[x][y].isVisited()) {
					status = "!";
				}
				System.out.print(status);
				if(y==param.getLength()-1) {
					System.out.println();
				}
					
			}
		}
		if(!grid.isGoalReached()) {
			System.out.println("PATH NOT FOUND");
		}

	}

}
