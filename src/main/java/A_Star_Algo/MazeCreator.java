package A_Star_Algo;

/**
 * @author darsh
 *
 */

public class MazeCreator 
{

	private Grid grid = new Grid();
	public Grid createMaze(GridParameters param) 
	{
		
		Cell[][] maze = new Cell[param.getLength()][param.getBreadth()];
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				Cell xy = new Cell();
				xy.setxCoordinate(x);
				xy.setyCoordinate(y);
				
				if(x==param.getxStart() && y==param.getyStart()) {
					xy.setStart(true);
				}
				if(x==param.getxGoal() && y==param.getyGoal()) {
					xy.setEnd(true);
				}
				
				xy.setHeuristic(Math.abs(param.getxGoal()-x)+Math.abs(param.getyGoal()-y));
				
				maze[x][y] = xy;
			}
		}
		
		grid.setMaze(maze);
		
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				grid.getMaze()[x][y].setChildren(grid, param);
			}
		}
		
		
		generateBlocks(0,0, grid.getMaze()[0][0], param);
		
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				grid.getMaze()[x][y].setChildren(grid, param);
				grid.getMaze()[x][y].setVisited(false);
			}
		}
		
		return grid;
	}
	private void generateBlocks(int row, int column, Cell node, GridParameters param) {
		
		if( Math.random()<0.1 && !((row==param.getxGoal() && column==param.getyGoal()) || (row==param.getxStart() && column==param.getyStart()))) {
			node.setObstacle(true);
		}
		node.setVisited(true);
		for(Cell child : node.getChildren()) {
			if(!child.isVisited()) {
				generateBlocks(child.getxCoordinate(), child.getyCoordinate(), child, param);
			}
		}
	}
	
}
