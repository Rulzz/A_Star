package AStar_Darshan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;


public class RFAStarWithLG {

	/**
	 * 
	 */
	private int row, column;
	private Cell[][] maze;
	private Cell[][] kMaze;
	private Cell start, kStart;
	private Cell goal, kGoal;
	/*private PriorityQueue<Cell> openPQueue;
	private LinkedList<Cell> closedList;*/
	private ArrayList<Cell> presumedPath,finalPath;
	//private int counter = 0;
	private int numOfExpandedCells = 0;
	private ArrayList<Grid> grids;
	private GridParameters gridParam;
	/*private boolean lastStep = false;
	private int countGoalReached =0;*/

	private Comparator<Cell> cellComparator = new Comparator<Cell>() 
	{
		@Override
		public int compare(Cell a, Cell b) 
		{
			/*if((a.getfValueLargerG() - b.getfValueLargerG()) == 0)
				return b.getgValue() - a.getgValue();
			else*/
				return a.getfValueLargerG() - b.getfValueLargerG();
		}
	};

	public RFAStarWithLG() {
		// TODO Auto-generated constructor stub
	}

	public RFAStarWithLG(Cell[][] maze, Cell start, Cell goal) {
		this.maze = maze;
		this.start = start;
		this.goal = goal;
		
		row = maze.length;
		column = maze[0].length;
		
		kMaze = MazeCreator.getCopyWithoutObstacle(maze);
		MazeCreator.generateChildren(kMaze);

		kStart = kMaze[start.getxCoordinate()][start.getyCoordinate()];
		kGoal = kMaze[goal.getxCoordinate()][goal.getyCoordinate()];
		
		gridParam = new GridParameters(row, column, start.getxCoordinate(), start.getyCoordinate(), goal.getxCoordinate(), goal.getyCoordinate());
		
		grids = new ArrayList<Grid>();
		MazeCreator.setStartGoal(maze, start, goal);
		Grid grid = new Grid();
		grid.setMaze(maze);
		grids.add(grid);
		
		finalPath = new ArrayList<Cell>();
		presumedPath = new ArrayList<Cell>();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				kMaze[i][j].computeHValue(goal);
			}
		}
	}

	public void executeAStar() {
		boolean isTargetReachable=false;
		if(start.isObstacle() == true)
		{
			System.out.println("Final Path is empty means somehow your start point is having obstacle istself. Please set to false everytime.");
			return;
		}
		while (!kStart.equalsTo(kGoal)) 
		{
			//System.out.println("Start:" + kStart.getXY() + " Goal:" + kGoal.getXY());
			
			gridParam.setxStart(kStart.getxCoordinate());
			gridParam.setyStart(kStart.getyCoordinate());
			gridParam.setxGoal(kGoal.getxCoordinate());
			gridParam.setyGoal(kGoal.getyCoordinate());
			
			
			kStart.computeHValue(goal);
			for(int i=0;i<kMaze.length;i++)
			{
				for(int j=0;j<kMaze.length;j++)
				{
					kMaze[i][j].setgValue(0);
				}
			}
			
			for (Cell child : kStart.children) {
				if (this.maze[child.getxCoordinate()][child.getyCoordinate()].isObstacle()) {
					kMaze[child.getxCoordinate()][child.getyCoordinate()].setObstacle(true);
				}
			}
			
			AStar aStar = new AStar(cellComparator);
			isTargetReachable = aStar.execute(kMaze, gridParam);
			
			if (isTargetReachable == false) 
			{
				Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
				MazeCreator.setStartGoal(kMazeCopy, start, goal);
				MazeCreator.setFinalPath(kMazeCopy, getPath());
				
				Grid grid = new Grid();
				grid.setGoalReached(true);
				grid.setMaze(kMazeCopy);
				grids.add(grid);
				finalPath.clear();
				System.out.println("Target is not reachable.");
				return;
			}

			presumedPath = aStar.getPath();

		//	MazeCreator.display(kMaze);
			
			Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
			MazeCreator.setStartGoal(kMazeCopy, start, goal);
			MazeCreator.setFinalPath(kMazeCopy, presumedPath);
			Grid grid = new Grid();
			grid.setMaze(kMazeCopy);
			grids.add(grid);

			/*System.out.println("I found Shortest Presumed Unblocked path.");
			Iterator<Cell> FP1 = presumedPath.iterator();
			while (FP1.hasNext()) {
				Cell temp = FP1.next();
				System.out.print(temp.getXY() + "," + temp.getgValue() + "->");
			}
			System.out.println();*/

			Iterator<Cell> presumedPathIterator = presumedPath.iterator();
			while (presumedPathIterator.hasNext()) {
				Cell temp = presumedPathIterator.next();
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
			numOfExpandedCells += aStar.getNumOfExpandedCells();
		}
		
		Cell[][] kMazeCopy = MazeCreator.getCopy(kMaze);
		MazeCreator.setStartGoal(kMazeCopy, start, goal);
		MazeCreator.setFinalPath(kMazeCopy, getPath());
		Grid grid = new Grid();
		grid.setGoalReached(true);
		grid.setMaze(kMazeCopy);
		grids.add(grid);
		
		System.out.println("I reached the target.");
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
