/**
 * 
 */
package AStar_Darshan;

/**
 * @author darsh
 *
 */

import java.util.Comparator;
import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

import A_Star_Algo.Cell;

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

	private void generateMaze(int x, int y) {

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
			if (decider < 0.3) {
				current.setObstacle(true);
			} else {
				current.setObstacle(false);
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

	public static Cell[][] getCopyWithoutObstacle(Cell[][] maze) {
		int row = maze.length;
		int column = maze[0].length;
		Cell[][] kMaze = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				kMaze[i][j] = new Cell(i, j);
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
