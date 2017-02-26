package AStar_Darshan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import A_Star_Algo.Cell;

public class RFAStarWithSG {

	private static final int INFINITY = Integer.MAX_VALUE - 100000;
	private int row, column;
	private Cell[][] maze;
	private Cell start;
	private Cell goal;
	private PriorityQueue<Cell> openPQueue;
	private LinkedList<Cell> closedList;
	private ArrayList<Cell> path;
	private int counter = 0;
	private int numOfExpandedCells = 0;

	private Neighbours[] neighbours = { new Neighbours(0, -1), new Neighbours(1, 0), new Neighbours(0, 1),
			new Neighbours(-1, 0) };

	public RFAStarWithSG() {
	}

	private class Neighbours {
		private int dx;
		private int dy;

		public Neighbours(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}

	private Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			if ((a.getfValueSmallerG() - b.getfValueSmallerG()) == 0)
				return a.getgValue() - a.getgValue();
			else
				return a.getfValueSmallerG() - b.getfValueSmallerG();
		}
	};

	public RFAStarWithSG(Cell[][] maze, Cell start, Cell goal) {
		this.maze = maze;
		this.start = start;
		this.goal = goal;

		openPQueue = new PriorityQueue<Cell>(cellComparator);
		closedList = new LinkedList<Cell>();
		path = new ArrayList<Cell>();

		row = maze.length;
		column = maze[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				maze[i][j].computeHValue(goal);
			}
		}

	}

	public void executeAStarWithSmallerG() {
		System.out.println("Start:" + start.getXY() + " Goal:" + goal.getXY());
		while (!start.equalsTo(goal)) {
			counter++;
			start.setgValue(0);
			start.computeHValue(goal);
			start.setSearch(counter);

			goal.setgValue(INFINITY);
			goal.setSearch(counter);

			openPQueue.clear();
			closedList.clear();

			openPQueue.offer(start);

			if (!openPQueue.isEmpty()) {
				findPath();
			}

			if (openPQueue.isEmpty()) {
				System.out.println("Target is not reachable.");
				return;
			}

			ArrayList<Cell> route = TracePath(goal);
			for(int i=route.size()-1;i>=0;i--)
				path.add(route.get(i));
			numOfExpandedCells = numOfExpandedCells + closedList.size();
			return;
		}
	}

	private void findPath() {
		while (goal.getgValue() > openPQueue.peek().getgValue()) {
			Cell currentCell = openPQueue.poll();
			closedList.add(currentCell);

			for (Neighbours neighbour : neighbours) {
				int newX = currentCell.getxCoordinate() + neighbour.dx;
				int newY = currentCell.getyCoordinate() + neighbour.dy;

				if ((0 <= newX && newX < row) && (0 <= newY && newY < column) && (!maze[newX][newY].isObstacle())) {
					Cell newCell = maze[newX][newY];

					if (newCell.getSearch() < counter) {
						newCell.setgValue(INFINITY);
						newCell.setSearch(counter);
					}

					if (newCell.getgValue() > currentCell.getgValue() + 1) {
						newCell.setgValue(currentCell.getgValue() + 1);
						newCell.parent = currentCell;

						if (openPQueue.contains(newCell)) {
							openPQueue.remove(newCell);
						}
						openPQueue.offer(newCell);
					}
				}
			}
			if (openPQueue.isEmpty()) {
				return;
			}
		}
	}

	private ArrayList<Cell> TracePath(Cell goal)
	{
		ArrayList<Cell> route = new ArrayList<Cell>();
		Cell temp = goal;
		while(temp != start)
		{
			route.add(temp);
			temp = temp.parent;
		}
		if(start.equalsTo(temp))
		{
			route.add(start);
			return route;
		}
		else
			return null;
	}
	
	public Cell[][] getMaze() {
		return maze;
	}

	public ArrayList<Cell> getPath() {
		return path;
	}

	public int getNumOfExpandedCells() {
		return numOfExpandedCells;
	}
}
