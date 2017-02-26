package TestClasses;

import java.util.Comparator;

import com.binaryHeap.*;

import A_Star_Algo.Cell;


public class TestMinBinaryHeap {

	//public static PriorityQueue<Cell> priorityQueue;
	
	public TestMinBinaryHeap() {
		// TODO Auto-generated constructor stub
	}

	private static Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell a, Cell b) {
			return a.getfValue() - b.getfValue();
		}
	};
	
	public static void main(String args[])
	{
		PriorityQueue<Cell> priorityQueue = new MinBinaryHeap<Cell>(cellComparator);
		
		Cell cell1 = new Cell(1, 1);
		Cell cell2 = new Cell(2, 2);
		Cell cell3 = new Cell(3, 3);
		Cell cell4 = new Cell(4, 4);
		Cell cell5 = new Cell(5, 5);
		Cell cell6 = new Cell(6, 6);
		Cell cell7 = new Cell(7, 7);
		Cell cell8 = new Cell(8, 8);
		Cell cell9 = new Cell(9, 9);
		Cell cell10 = new Cell(10,10);
		
		cell1.sethValue(15);
		cell2.sethValue(35);
		cell3.sethValue(32);
		cell4.sethValue(40);
		cell5.sethValue(54);
		cell6.sethValue(64);
		cell7.sethValue(15);
		cell8.sethValue(52);
		cell9.sethValue(32);
		cell10.sethValue(132);
		
		priorityQueue.add(cell4);
		System.out.println("Current Top:"+priorityQueue.peek().getXY());
		display(priorityQueue);
		priorityQueue.add(cell3);
		display(priorityQueue);
		priorityQueue.add(cell2);
		display(priorityQueue);
		priorityQueue.add(cell1);
		display(priorityQueue);
		System.out.println("Current Top:"+priorityQueue.peek().getXY());
		priorityQueue.poll();
		System.out.println("Current Top:"+priorityQueue.peek().getXY());
		display(priorityQueue);
		System.out.println(priorityQueue.contains(cell4));
		priorityQueue.add(cell10);
		display(priorityQueue);
		priorityQueue.remove(cell4);
		display(priorityQueue);
		priorityQueue.add(cell6);
		priorityQueue.add(cell5);
		display(priorityQueue);
		priorityQueue.add(cell6);
		display(priorityQueue);
		priorityQueue.add(cell9);
		display(priorityQueue);
		priorityQueue.poll();
		display(priorityQueue);
		priorityQueue.add(cell1);
		display(priorityQueue);
		priorityQueue.remove(cell6);
		display(priorityQueue);
		System.out.println(priorityQueue.peek().getXY());
		priorityQueue.clear();
		display(priorityQueue);
		System.out.println(priorityQueue.isEmpty());
		
		
	}
	
	public static void display(PriorityQueue<Cell> priorityQueue )
	{
		int i = 1;
		System.out.println("Size:"+priorityQueue.size());
		System.out.println("CurrentList:");
		while(i<=priorityQueue.size())
		{
			System.out.print("Level "+i+"\t\t");
			System.out.print(priorityQueue.getNode(i).getXY()+","+priorityQueue.getNode(i).getfValue()+"\t");
			System.out.println();
			i++;
		}
		System.out.println();
	}
}
