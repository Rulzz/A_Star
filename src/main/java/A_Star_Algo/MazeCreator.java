package A_Star_Algo;

/**
 * @author darsh
 *
 */

public class MazeCreator 
{

	public Grid createMaze(GridParameters param) 
	{
		
		Grid grid = createBasicMaze(param);
		
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				grid.getMaze()[x][y].setChildren(grid, param);
			}
		}
		
		
		generateBlocks(0,0, grid.getMaze()[0][0], param);
		
		populateChildren(grid, param);
		
		
		return grid;
	}
	public void populateChildren(Grid grid, GridParameters param) {
		for(int x=0 ; x<param.getLength(); x++) {
			for(int y=0 ; y<param.getBreadth(); y++) {
				grid.getMaze()[x][y].setChildren(grid, param);
				grid.getMaze()[x][y].setVisited(false);
			}
		}
		
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
	public Grid createBasicMaze(GridParameters gridParam) {
		Cell[][] maze = new Cell[gridParam.getLength()][gridParam.getBreadth()];
		for(int x=0 ; x<gridParam.getLength(); x++) {
			for(int y=0 ; y<gridParam.getBreadth(); y++) {
				Cell xy = new Cell();
				xy.setxCoordinate(x);
				xy.setyCoordinate(y);
				
				if(x==gridParam.getxStart() && y==gridParam.getyStart()) {
					xy.setStart(true);
				}
				if(x==gridParam.getxGoal() && y==gridParam.getyGoal()) {
					xy.setEnd(true);
				}
				
				xy.setHeuristic(Math.abs(gridParam.getxGoal()-x)+Math.abs(gridParam.getyGoal()-y));
				
				maze[x][y] = xy;
			}
		}
		Grid grid = new Grid();
		grid.setMaze(maze);
		return grid;
	}
	
}
