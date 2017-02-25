/**
 * 
 */
package AStar_Darshan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import A_Star_Algo.Cell;
import A_Star_Algo.Grid;

/**
 * @author darsh
 *
 */
public class Test {

	private static final int SIZE = 5;

	public static void main(String[] args) {

			MazeCreator mazeCreator = new MazeCreator(SIZE, SIZE);
			Cell[][] maze = mazeCreator.getMaze();
			
			Random random = new Random();
			
			int startX, startY, goalX, goalY;
			startX = 0; startY = 0; goalX = 4; goalY = 4;

			/*startX = random.nextInt(SIZE);
			startY = random.nextInt(SIZE);
			goalX = random.nextInt(SIZE);
			goalY = random.nextInt(SIZE);*/
			
			Cell start, goal;
			start = maze[startX][startY];
			goal = maze[goalX][goalY];
			start.setObstacle(false);
			goal.setObstacle(false);
			
			

			System.out.println("Display Generated Maze:");
			mazeCreator.display();
			
			System.out.println();
			System.out.println("Start X : " + start.getxCoordinate() + " Y:" + start.getyCoordinate());
			System.out.println("Goal X : " + goal.getxCoordinate() + " Y:" + goal.getyCoordinate());

			AStar A = new AStar(maze, start, goal);

			A.executeAStar();
			ArrayList<Cell> path = A.getPath();
			System.out.println("Maze After AStar Search:");
		 	MazeDisplay.display(maze, path);

		 	System.out.println();
		 	System.out.println("Total Grids"+A.getGrids().size());
		 	Iterator<Grid> grids = A.getGrids().iterator();
		 	int i =1;
		 	while(grids.hasNext())
		 	{
		 		System.out.println("Grid No:"+i);
		 		Grid grid = grids.next();
		 		MazeCreator.display(grid.getMaze());
		 		i++;
		 	}
		 	
			System.out.println();
			System.out.println("No of Expanded Cells:" + A.getNumOfExpandedCells());
			System.out.println("No of Nodes : " + path.size());
			System.out.println("Path from Start to Goal : ");

			Iterator<Cell> IP = path.iterator();
			while (IP.hasNext()) {
				Cell temp = IP.next();
				System.out.print(temp.getXY()+","+temp.getgValue() + "->");
			}
			System.out.println();
			System.out.print("\n\n\n");
			
/*			Cell[][] mazeBack = mazeCreator.getCopy();
			
			start = mazeBack[startX][startY];
			goal = mazeBack[goalX][goalY];
			start.setObstacle(false);
			goal.setObstacle(false);
			
			System.out.println();
			System.out.println("Start X : " + goal.getxCoordinate() + " Y:" + goal.getyCoordinate());
			System.out.println("Goal X : " + start.getxCoordinate() + " Y:" + start.getyCoordinate());

			AStar ABack = new AStar(mazeBack, goal, start);

			ABack.executeAStar();
			ArrayList<Cell> pathBack = ABack.getPath();
			System.out.println("Maze After Backward AStar Search:");
		// 	MazeDisplay.display(maze, path);

			System.out.println();
			System.out.println("No of Expanded Cells:" + ABack.getNumOfExpandedCells());
			System.out.println("No of Nodes : " + pathBack.size());
			System.out.println("Path from Start to Goal : ");

			Iterator<Cell> IPBack = pathBack.iterator();
			while (IPBack.hasNext()) {
				Cell temp = IPBack.next();
				System.out.print(temp.getXY() + "->");
			}
			System.out.println();
			
			System.out.print("\n\n\n");
			System.out.println("AStarWithLargerG");

			Cell[][] mazeLG = mazeCreator.getCopy();

			start = mazeLG[startX][startY];
			goal = mazeLG[goalX][goalY];
			start.setObstacle(false);
			goal.setObstacle(false);

			AStarWithLargerG AWithLargerG = new AStarWithLargerG(mazeLG, start, goal);
			AWithLargerG.executeAStarWithLargerG();
		//	ArrayList<Cell> pathLG = AWithLargerG.getPath();

			System.out.println("Maze after A* with Larger G value.");
		// 	MazeDisplay.display(mazeLG, pathLG);

			System.out.println();
			System.out.println("No of Expanded Cells:" + AWithLargerG.getNumOfExpandedCells());
			System.out.println("No of Nodes in Path:" + AWithLargerG.getPath().size());
			System.out.println("Path:");
			Iterator<Cell> IPLG = AWithLargerG.getPath().iterator();
			while (IPLG.hasNext()) {
				System.out.print(IPLG.next().getXY() + "->");
			}
			System.out.println();

			System.out.print("\n\n\n");
			System.out.println("AStarWithSmallerG");

			Cell[][] mazeSG = mazeCreator.getCopy();

			start = mazeSG[startX][startY];
			goal = mazeSG[goalX][goalY];
			start.setObstacle(false);
			goal.setObstacle(false);

			AStarWithSmallerG AWithSmallerG = new AStarWithSmallerG(mazeSG, start, goal);
			AWithSmallerG.executeAStarWithSmallerG();
		//	ArrayList<Cell> pathSG = AWithSmallerG.getPath();

			System.out.println("Maze after A* with Smaller G value.");
		// 	MazeDisplay.display(mazeSG, pathSG);

			System.out.println();
			System.out.println("No of Expanded Cells:" + AWithSmallerG.getNumOfExpandedCells());
			System.out.println("No of Nodes in Path:" + AWithSmallerG.getPath().size());
			System.out.println("Path:");
			Iterator<Cell> IPSG = AWithSmallerG.getPath().iterator();
			while (IPSG.hasNext()) {
				System.out.print(IPSG.next().getXY() + "->");
			}
			System.out.println();*/
	}

}
