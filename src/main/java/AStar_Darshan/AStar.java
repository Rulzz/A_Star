package AStar_Darshan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import com.binaryHeap.MinBinaryHeap;
import com.binaryHeap.PriorityQueue;

import A_Star_Algo.Cell;
import A_Star_Algo.GridParameters;

/**
 * @author darsh
 *
 */

public class AStar {

	private static final int INFINITY = Integer.MAX_VALUE - 100000;
	private Cell start, goal;
	private PriorityQueue<Cell> openPQueue;
	private LinkedList<Cell> closedList;
	private ArrayList<Cell> path;
	private int counter = 0;
	private int numOfExpandedCells = 0;
	
	
	
	
	public AStar() {
		// TODO Auto-generated constructor stub
//		openPQueue = new PriorityQueue<Cell>(cellComparator);
		openPQueue = new MinBinaryHeap<Cell>(cellComparator);
		closedList = new LinkedList<Cell>();
		path = new ArrayList<Cell>();
	}
	
	public AStar(Comparator<Cell> outerCellcomparator) {
		// TODO Auto-generated constructor stub
//		openPQueue = new PriorityQueue<Cell>(cellComparator);
		openPQueue = new MinBinaryHeap<Cell>(outerCellcomparator);
		closedList = new LinkedList<Cell>();
		path = new ArrayList<Cell>();
	}
	
	private Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			if ((a.getfValue() - b.getfValue()) == 0)
				return b.getgValue() - a.getgValue();
			else
				return a.getfValue() - b.getfValue();
		}
	};
	
	public boolean execute(Cell[][] maze,GridParameters gridParam)
	{
		boolean isGoalReached = false;
		counter++;
		
/*		System.out.println("AStar---------------------------------------");
		MazeCreator.display(maze);
		System.out.println("--------------------------------------------");
*/		
		start = maze[gridParam.xStart][gridParam.yStart];
		goal = maze[gridParam.xGoal][gridParam.yGoal];
		MazeCreator creator = new MazeCreator();
		creator.printMaze(maze);
		System.out.println("A STAR Start cell : " + start.getxCoordinate() + "," + start.getyCoordinate());
		System.out.println("A GOAL Start cell : " + goal.getxCoordinate() + "," + goal.getyCoordinate());
		for(int i=0;i<maze.length;i++)
		{
			for(int j=0;j<maze[0].length;j++)
			{
				maze[i][j].setSearch(0);
			}
		}
		
		start.setgValue(0);
		start.setSearch(counter);

		goal.setgValue(INFINITY);
		goal.setSearch(counter);
		
		openPQueue.clear();
		closedList.clear();
		path.clear();
		
		openPQueue.add(start);
		
		if (!openPQueue.isEmpty()) {
			findPath();
		}
		
		if (openPQueue.isEmpty()) {
			path.clear();
			System.out.println("Astar says Target is not reachable.");
			return isGoalReached;
		}
		
		ArrayList<Cell> reverseRoute = TracePath(goal);
		
		for(int i=reverseRoute.size()-1;i>=0;i--)
			path.add(reverseRoute.get(i));
		
		Iterator<Cell> pathIterator = path.iterator();
		while(pathIterator.hasNext())
		{
			Cell temp = pathIterator.next();
			maze[temp.getxCoordinate()][temp.getyCoordinate()].setOnFinalPath(true);
		}
		numOfExpandedCells += closedList.size();
		isGoalReached = true;
		return isGoalReached;
	}
	
	private void findPath() {
		while (goal.getgValue() > openPQueue.peek().getgValue()) {
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

	public ArrayList<Cell> getPath() {
		return path;
	}

	public int getNumOfExpandedCells() {
		return numOfExpandedCells;
	}
	
}
