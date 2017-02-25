/**
 * 
 */
package AStar_Darshan;

/**
 * @author darsh
 *
 */

//import java.util.Arrays;
//import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

public class MazeCreator {

	/**
	 * 
	 */

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

	public MazeCreator(int row, int column) {
		maze = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				maze[i][j] = new Cell(i, j);
			}
		}
		generateChildren(this.maze);
		generateMaze(0, 0);
	}

	private static Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
				return Integer.parseInt(a.getXY()) - Integer.parseInt(b.getXY());
		}
	};
	
	public static void generateChildren(Cell[][] maze) {
		System.out.println("Inside Genereate Children");
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j].children = new TreeSet<Cell>(cellComparator);
				setChildren(maze[i][j], maze);
			}
		}
	}

	public static void setChildren(Cell cell,Cell[][] maze) {
	//	System.out.println("Inside Set Children for Cell:" + cell.getXY());
		int rows = maze.length;
		int cols = maze[0].length;
		for (int i = 0; i < 4; i++) {
			int newX = cell.getxCoordinate() + neighbours[i].dx;
			int newY = cell.getyCoordinate() + neighbours[i].dy;
			if ((0<= newX && newX < rows) && (0<= newY && newY < cols)) 
			{
	//			System.out.println("NewCell :"+maze[newX][newY].getXY());
				Cell child = maze[newX][newY];
	//			System.out.println("Is obstacle for Child before adding:"+child.isObstacle());
				cell.children.add(child);
	//			System.out.println("Children:" + cell.children.size());
			}
		}
	//	System.out.println("Total children for current cell:"+cell.getXY()+" size:"+cell.children.size());
	}

	public Cell[][] getMaze() {
		return maze;
	}

	private void generateMaze(int x, int y) {
		// System.out.println("Inside generateMaze function");
		int row = maze.length;
		int column = maze[0].length;
		// System.out.println("Row :"+row+" Column :"+column);
		Stack<Cell> stack = new Stack<Cell>();

		if ((0 <= x && x < row) && (0 <= y && y < column)) {
			stack.push(maze[x][y]);
		}
		// int counter = 0;
		while (!stack.isEmpty()) {
			// counter++;
			Cell current = stack.pop();
			//int currentX = current.getxCoordinate();
			//int currentY = current.getyCoordinate();
			// System.out.println("Current X :"+currentX+"Current Y
			// :"+currentY);
			if (current.isVisited()) {
				// System.out.println("Current is Visited already. So Skipping
				// now.");
				continue;
			}
			current.setVisited(true);

			double decider = random.nextDouble();
			// System.out.println("No :"+ counter +" decider value " + decider);
			if (decider < 0.3) {
				current.setObstacle(true);
				// System.out.println("Inside IF :"+current.isObstacle());
			} else {
				current.setObstacle(false);
				// System.out.println("Inside ELSE :"+current.isObstacle());
			}
			/*Collections.shuffle(Arrays.asList(neighbours));
			for (Neighbours d : neighbours) {
				int newX = currentX + d.dx;
				int newY = currentY + d.dy;
				if ((newX < 0 || newX >= row) || (newY < 0 || newY >= column))
					continue;
				// System.out.println("New X :"+newX+"New Y :"+newY);
				if (!maze[newX][newY].isVisited())
					stack.push(maze[newX][newY]);
			}*/
			for (Cell child : current.children) {
				/*
				 * int newX = currentX + d.dx; int newY = currentY + d.dy;
				 * if((newX < 0 || newX >= row) || (newY < 0 || newY >= column))
				 * continue;
				 */
				// System.out.println("New X :"+newX+"New Y :"+newY);
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
	
	public static Cell[][] getCopyWithoutObstacle(Cell[][] maze) {
		int row = maze.length;
		int column = maze[0].length;
		Cell[][] kMaze = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				kMaze[i][j] = new Cell(i,j);
			}
		}
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
}
