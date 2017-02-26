package TestClasses;

import java.util.ArrayList;

import AStar_Darshan.AStar;
import AStar_Darshan.MazeCreator;
import AStar_Darshan.MazeDisplay;
import A_Star_Algo.Cell;
import A_Star_Algo.Grid;
import A_Star_Algo.GridParameters;

public class TestStandaloneAStar {
	
	private static final int SIZE = 50;

	public TestStandaloneAStar() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GridParameters gridParam = new GridParameters();
		gridParam.breadth=SIZE;
		gridParam.length=SIZE;
		gridParam.yStart = 0;
		gridParam.yStart = 0;
		gridParam.xGoal = 49;
 		gridParam.yGoal = 49;
 		
		MazeCreator mazeCreator = new MazeCreator(gridParam);
		Cell[][] maze = mazeCreator.getMaze();
		
		System.out.println("------------------------------------------------");		
		mazeCreator.display();
		System.out.println("------------------------------------------------");
		
		AStar aStar = new AStar();
		aStar.execute(maze, gridParam);
		ArrayList<Cell> path = aStar.getPath();
		
		if(!path.isEmpty())
		{
			System.out.println("------------------------------------------------");
			MazeDisplay.display(maze, path);
			System.out.println("------------------------------------------------");
		}

	}

}
