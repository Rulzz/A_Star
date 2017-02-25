package AStar_Darshan;

import java.util.ArrayList;

public class MazeDisplay {

	public static void display(Cell[][] maze, ArrayList<Cell> path ) 
	{
		int rows = maze.length;
		int cols = maze[0].length;
		char[][] display = new char[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell c = maze[i][j];
				if(!c.isObstacle())
				{
					display[i][j]='0';
				}
				else
				{
					display[i][j]='1';
				}
			}
		}
		
		for(Cell c : path)
		{
			display[c.getxCoordinate()][c.getyCoordinate()]='*';
		}
		
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				System.out.print(display[i][j]);
				if((j+1) % cols == 0)
				{
					System.out.print("\n");
				}
			}
		}
	}

}
