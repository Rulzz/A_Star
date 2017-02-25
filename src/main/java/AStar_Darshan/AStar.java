/**
 * 
 */
package AStar_Darshan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
/**
 * @author darsh
 *
 */
public class AStar {

	/**
	 * 
	 */
	private static final int INFINITY = Integer.MAX_VALUE - 100000;
	private int row, column;
	private Cell[][] maze;
	private Cell[][] kMaze;
	private Cell start, kStart;
	private Cell goal, kGoal;
	private PriorityQueue<Cell> openPQueue;
	private LinkedList<Cell> closedList;
	private ArrayList<Cell> /*path,*/ finalPath;
	private int counter = 0;
	private int numOfExpandedCells = 0;
	private ArrayList<Grid> grids;
	/*private boolean lastStep = false;
	private int countGoalReached =0;*/

	private Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			if (a.getfValue() - b.getfValue() == 0)
				return a.getgValue() - a.getgValue();
			else
				return a.getfValue() - b.getfValue();
		}
	};

	public AStar() {
		// TODO Auto-generated constructor stub
	}

	public AStar(Cell[][] maze, Cell start, Cell goal) {
		this.maze = maze;
		this.start = start;
		this.goal = goal;
		
		kMaze = MazeCreator.getCopyWithoutObstacle(maze);
		MazeCreator.generateChildren(kMaze);

		kStart = kMaze[start.getxCoordinate()][start.getyCoordinate()];
		kGoal = kMaze[goal.getxCoordinate()][goal.getyCoordinate()];
		
		grids = new ArrayList<Grid>();
		MazeCreator.setStartGoal(maze, start, goal);
		Grid grid = new Grid();
		grid.setMaze(maze);
		grids.add(grid);
		
		openPQueue = new PriorityQueue<Cell>(cellComparator);
		closedList = new LinkedList<Cell>();
		finalPath = new ArrayList<Cell>();
		//path = new ArrayList<Cell>();

		row = maze.length;
		column = maze[0].length;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				kMaze[i][j].computeHValue(goal);
			}
		}
	}

	public void executeAStar() {
		if(start.isObstacle() == true)
		{
			System.out.println("Final Path is empty means somehow your start point is having obstacle istself. Please set to false everytime.");
			return;
		}
		// while (!lastStep) {
		System.out.println("--------------------------------------------");
		MazeCreator.display(maze);
		System.out.println("--------------------------------------------");
		while (!kStart.equalsTo(kGoal)) 
		{
			System.out.println("Start:" + kStart.getXY() + " Goal:" + kGoal.getXY());
			counter++;

			kStart.setgValue(0);
			kStart.computeHValue(goal);
			kStart.setSearch(counter);

			kGoal.setgValue(INFINITY);
			kGoal.setSearch(counter);

			openPQueue.clear();
			closedList.clear();

			openPQueue.offer(kStart);

			for (Cell child : kStart.children) {
				if (this.maze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
					kMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
				}
			}

			if (!openPQueue.isEmpty()) {
				findPath();
			}

			if (openPQueue.isEmpty()) {
				Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
				MazeCreator.setStartGoal(kMazeCopy, start, goal);
				MazeCreator.setFinalPath(kMazeCopy, getPath());
				;
				Grid grid = new Grid();
				grid.setGoalReached(true);
				grid.setMaze(kMazeCopy);
				grids.add(grid);
				finalPath.clear();
				System.out.println("Target is not reachable.");
				return;
			}

			ArrayList<Cell> forwardPath = move();

			//MazeCreator.display(kMaze);
			Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
			MazeCreator.setStartGoal(kMazeCopy, start, goal);
			MazeCreator.setFinalPath(kMazeCopy, forwardPath);
			Grid grid = new Grid();
			grid.setMaze(kMazeCopy);
			grids.add(grid);

			System.out.println("I found Shortest Presumed Unblocked path.");
			Iterator<Cell> FP1 = forwardPath.iterator();
			while (FP1.hasNext()) {
				Cell temp = FP1.next();
				System.out.print(temp.getXY() + "," + temp.getgValue() + "->");
			}
			System.out.println();

			Iterator<Cell> FP = forwardPath.iterator();
			while (FP.hasNext()) {
				Cell temp = FP.next();
				if (maze[temp.getxCoordinate()][temp.getyCoordinate()].isObstacle()) {
					finalPath.remove(temp);
					break;
				}
				for (Cell child : temp.children) {
					if (this.maze[child.getxCoordinate()][child.getyCoordinate()].isObstacle() && !temp.equals(kGoal)) {
						kMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
					}
				}
				if (!finalPath.contains(temp)) {
					finalPath.add(temp);
				}
			}
			if(finalPath.isEmpty())
			{
				System.out.println("Final Path is empty means somehow your start point is having obstacle istself. Please set to false everytime.");
				return;
			}
			kStart = finalPath.get(finalPath.size() - 1);
			/*
			 * System.out.println(kStart.getXY());
			 * System.out.println(kStart.equalsTo(kGoal));
			 * if(kStart.equalsTo(kGoal) == true) { countGoalReached++;
			 * System.out.println("countGoalReached"+countGoalReached); }
			 */
			numOfExpandedCells += closedList.size();
		}
		
		Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
		MazeCreator.setStartGoal(kMazeCopy, start, goal);
		MazeCreator.setFinalPath(kMazeCopy, getPath());
		;
		Grid grid = new Grid();
		grid.setGoalReached(true);
		grid.setMaze(kMazeCopy);
		grids.add(grid);
		System.out.println("I reached the target.");
		/*
		 * if(countGoalReached < 2) {
		 * System.out.println("Running A* for last time."); kStart =
		 * kMaze[start.getxCoordinate()][start.getyCoordinate()];
		 * System.out.println(kStart.getXY()+" "+kGoal.getXY());
		 * finalPath.clear(); } if(countGoalReached >= 2) lastStep = true;;
		 */
		// }
	}

	private void findPath() {
		while (kGoal.getgValue() > openPQueue.peek().getgValue()) {
			Cell currentCell = openPQueue.poll();
			closedList.add(currentCell);

			for (Cell child : currentCell.children) {
				if (!child.isObstacle()) {
					Cell newCell = child;

					if (newCell.getSearch() < counter) {
						newCell.setgValue(INFINITY);
						newCell.setSearch(counter);
					}

					if (newCell.getgValue() > (currentCell.getgValue() + 1)) {
						newCell.setgValue(currentCell.getgValue() + 1);
						newCell.parent = currentCell;
						if (openPQueue.contains(newCell)) {
							openPQueue.remove(newCell);
						}
						openPQueue.add(newCell);
					}
				}
			}
			if (openPQueue.isEmpty()) {
				return;
			}
		}
		/*System.out.println("OpenQueue:");
		Iterator<Cell> IQ = openPQueue.iterator();
		while (IQ.hasNext()) {
			Cell temp = IQ.next();
			System.out.print(temp.getXY() + "," + temp.getgValue() + "->");
		}
		System.out.println();*/
	}

	private ArrayList<Cell> move() {
		ArrayList<Cell> reversePath = new ArrayList<Cell>();
		ArrayList<Cell> forwardPath = new ArrayList<Cell>();
		Cell pos = kGoal;
		while (pos != kStart) {
			reversePath.add(pos);
			pos = pos.parent;
		}
		if (pos == kStart) {
			reversePath.add(pos);
		}

		for (int i = reversePath.size() - 1; i >= 0; i--) {
			forwardPath.add(reversePath.get(i));
		}
		return forwardPath;
	}

	public Cell[][] getMaze() {
		return maze;
	}

	public ArrayList<Cell> getPath() {
		return finalPath;
	}

	public int getNumOfExpandedCells() {
		return numOfExpandedCells;
	}

	public ArrayList<Grid> getGrids() {
		return grids;
	}

	public void setGrids(ArrayList<Grid> grids) {
		this.grids = grids;
	}
}
