/**
 * 
 */
package AStar_Darshan;

import java.util.ArrayList;

/**
 * @author darsh
 *
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

public class MazeCreator {
	private Cell[][] maze;
	Random random = new Random();
	private static Neighbours[] neighbours = { new Neighbours(-1, 0), new Neighbours(0, 1), new Neighbours(1, 0),
			new Neighbours(0, -1) };

	private static class Neighbours {
		private int dx;
		private int dy;

		public Neighbours(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	@Deprecated
	public MazeCreator(int row, int column) {
		maze = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				maze[i][j] = new Cell(i, j);
			}
		}
		generateChildren(this.maze);
		generateMaze(0, 0, new GridParameters());
	}

	public MazeCreator() {
		// TODO Auto-generated constructor stub
	}
	
	public MazeCreator(GridParameters gridParameters) {
		maze = new Cell[gridParameters.getLength()][gridParameters.getBreadth()];
		for (int i = 0; i < gridParameters.getLength(); i++) {
			for (int j = 0; j < gridParameters.getBreadth(); j++) {
				maze[i][j] = new Cell(i, j);
			}
		}
		generateChildren(this.maze);
		generateMaze(0, 0, gridParameters);
	}

	private static Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			return Integer.parseInt(a.getXY()) - Integer.parseInt(b.getXY());
		}
	};

	public static void generateChildren(Cell[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j].children = new TreeSet<Cell>(cellComparator);
				setChildren(maze[i][j], maze);
			}
		}
	}

	public static void setChildren(Cell cell, Cell[][] maze) {

		int rows = maze.length;
		int cols = maze[0].length;
		for (int i = 0; i < 4; i++) {
			int newX = cell.getxCoordinate() + neighbours[i].dx;
			int newY = cell.getyCoordinate() + neighbours[i].dy;
			if ((0 <= newX && newX < rows) && (0 <= newY && newY < cols)) {

				Cell child = maze[newX][newY];

				cell.children.add(child);

			}
		}

	}

	public Cell[][] getMaze() {
		return maze;
	}

	private void generateMaze(int x,int y, GridParameters gridParam) {

		int row = maze.length;
		int column = maze[0].length;

		Stack<Cell> stack = new Stack<Cell>();

		if ((0 <= x && x < row) && (0 <= y && y < column)) {
			stack.push(maze[x][y]);
		}
		while (!stack.isEmpty()) {
			Cell current = stack.pop();
			if (current.isVisited()) {
				continue;
			}
			current.setVisited(true);

			double decider = random.nextDouble();
			if (decider < 0.3 && !((gridParam.getxGoal()==current.getxCoordinate() && gridParam.getyGoal()==current.getyCoordinate()) 
										|| (gridParam.getxStart()==current.getxCoordinate() && gridParam.getyStart()==current.getyCoordinate()) )) {
				current.setObstacle(true);
			} 
			for (Cell child : current.children) {
				if (!child.isVisited())
					stack.push(child);
			}
		}
	}

	public Cell[][] getCopy() {
		int rows = maze.length;
		int cols = maze[0].length;

		Cell[][] newMaze = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				newMaze[i][j] = new Cell(i, j);
				newMaze[i][j].setObstacle(maze[i][j].isObstacle());
			}
		}
		return newMaze;
	}
	
	public static Cell[][] getCopy(Cell[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;

		Cell[][] newMaze = new Cell[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				newMaze[i][j] = new Cell(i, j);
				newMaze[i][j].setObstacle(maze[i][j].isObstacle());
			}
		}
		return newMaze;
	}

	public static Cell[][] getCopyWithoutObstacle(Cell[][] maze) {
		int row = maze.length;
		int column = maze[0].length;
		Cell[][] kMaze = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				kMaze[i][j] = new Cell(i, j);
			}
		}
		generateChildren(kMaze);
		return kMaze;
	}

	public void display() {
		int rows = maze.length;
		int cols = maze[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!maze[i][j].isObstacle()) {
					System.out.print("0");
				} else {
					System.out.print("1");
				}
				if ((j + 1) % cols == 0) {
					System.out.print("\n");
				}
			}
		}
	}

	public static void display(Cell[][] maze) {
		int rows = maze.length;
		int cols = maze[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!maze[i][j].isObstacle()) {
					System.out.print("0");
				} else {
					System.out.print("1");
				}
				if ((j + 1) % cols == 0) {
					System.out.print("\n");
				}
			}
		}
	}
	
	public static void setFinalPath(Cell[][] maze, ArrayList<Cell> path)
	{
		Iterator<Cell> pathIterator = path.iterator();
		while(pathIterator.hasNext())
		{
			Cell cell = pathIterator.next();
			maze[cell.getxCoordinate()][cell.getyCoordinate()].setOnFinalPath(true);
		}
	}
	
	public static void setStartGoal(Cell[][] maze, Cell start, Cell goal)
	{
		maze[start.getxCoordinate()][start.getyCoordinate()].setStart(true);
		maze[goal.getxCoordinate()][goal.getyCoordinate()].setEnd(true);
	}
	
	
	//Raj's code
	
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
	
}
