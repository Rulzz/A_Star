/**
 * 
 */
package TestClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import AStar_Darshan.MazeCreator;
import AStar_Darshan.MazeDisplay;
import AStar_Darshan.RFAStar;
import AStar_Darshan.RFAStarWithLG;
import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

/**
 * @author darsh
 *
 */
public class Test {

	private static final int SIZE = 50;

	public static void main(String[] args) {
		
			System.out.println("----------------------------------------------------------------------------------------");
			int startX, startY, goalX, goalY;
			startX = 0; startY = 0; goalX = 49; goalY = 49;
			
			//Random random = new Random();
			/*startX = random.nextInt(SIZE);
			startY = random.nextInt(SIZE);
			goalX = random.nextInt(SIZE);
			goalY = random.nextInt(SIZE);*/

			GridParameters gridParam = new GridParameters(SIZE, SIZE, startX, startY, goalX, goalY);
			MazeCreator mazeCreator = new MazeCreator(gridParam);
			
			Cell[][] maze = mazeCreator.getMaze();
			
			Cell start, goal;
			start = maze[startX][startY];
			goal = maze[goalX][goalY];

			System.out.println("Display Generated Maze:");
			mazeCreator.display();
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Start X : " + start.getxCoordinate() + " Y:" + start.getyCoordinate());
			System.out.println("Goal X : " + goal.getxCoordinate() + " Y:" + goal.getyCoordinate());
			System.out.println();
			RFAStar A = new RFAStar(maze, start, goal);

			A.executeAStar();
			
			ArrayList<Cell> path = A.getPath();
		//	System.out.println("Maze After AStar Search:");
		// 	MazeDisplay.display(maze, path);

		 	System.out.println();
		 	System.out.println("Total Grids"+A.getGrids().size());
		 	
			System.out.println();
			System.out.println("No of Expanded Cells:" + A.getNumOfExpandedCells());
			System.out.println("No of Nodes : " + path.size());
			System.out.println("Path from Start to Goal : ");

			Iterator<Cell> IP = path.iterator();
			while (IP.hasNext()) {
				Cell temp = IP.next();
				System.out.print(temp.getXY()/*+","+temp.getgValue() */+"->");
			}
			System.out.println();
			System.out.print("\n\n\n");
/*----------------------------------------------------------------------------------------------------------------------*/
			
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
/*----------------------------------------------------------------------------------------------------------------------*/
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.println("AStarWithLargerG");

			Cell[][] mazeLG = mazeCreator.getCopy();

			start = mazeLG[startX][startY];
			goal = mazeLG[goalX][goalY];
			start.setObstacle(false);
			goal.setObstacle(false);

			RFAStarWithLG rfaStarWithLG = new RFAStarWithLG(mazeLG, start, goal);
			rfaStarWithLG.executeAStar();
			ArrayList<Cell> pathLG = rfaStarWithLG.getPath();

	//		System.out.println("Maze after A* with Larger G value.");
	//	 	MazeDisplay.display(mazeLG, pathLG);

			System.out.println();
			System.out.println("No of Expanded Cells:" + rfaStarWithLG.getNumOfExpandedCells());
			System.out.println("No of Nodes in Path:" + rfaStarWithLG.getPath().size());
			System.out.println("Path:");
			
			Iterator<Cell> IPLG = rfaStarWithLG.getPath().iterator();
			while (IPLG.hasNext()) {
				System.out.print(IPLG.next().getXY() + "->");
			}
			System.out.println();

			System.out.print("\n\n\n");
			System.out.println("----------------------------------------------------------------------------------------");
/*----------------------------------------------------------------------------------------------------------------------*/			
/*			System.out.println("AStarWithSmallerG");

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
