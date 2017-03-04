package com.binaryHeap;

import java.util.Arrays;
import java.util.Comparator;

public class MinBinaryHeap<T> implements PriorityQueue<T> {

	private static final int DEFAULT_CAPACITY = 2;
	protected T[] binaryTree;
	protected int size;
	Comparator<T> comparator;

	
	@SuppressWarnings("unchecked")
	public MinBinaryHeap(Comparator<T> comparator)
	{
		this.comparator=comparator;
		binaryTree = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	

	public String toString() {
        return Arrays.toString(binaryTree);
    }
	
	@Override
	public void add(T object) {
		// First,It will increase the size of array if needed to add new element.
		if (size >= binaryTree.length - 1) {
			binaryTree = this.resize();
		}
		
		// It will place the element at the bottom.
		size++;
		int index = size;
		binaryTree[index] = object;
		
		// It will moveUp the newly inserted element to its proper position in a tree.
		moveUp();
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	@Override
	public T peek() {
		if (this.isEmpty()) {
			throw new MinBinaryHeapExceptions("Illegal access. Priority Queue is empty. You can't perform peek operation. ");
		}
		return binaryTree[1];
	}

	@Override
	public T poll() {
		// It will store the top element first before it actually removes  it. So we can return it at end.
		T result = peek();

		// Make the last element of array to first element and make last element null. 
		binaryTree[1] = binaryTree[size];
		binaryTree[size] = null;
		size--;

		//As first element needs to be at its proper position we will call moveDown operation.
		moveDown();

		return result;
	}
	
	@Override
	public T getNode(int index)
	{
		return binaryTree[index];
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void clear() {
		for(int i=1;i<=size;i++)
			binaryTree[i] = null;
		size = 0;
	}
	
	public boolean remove(T object)
	{
		int index = indexOf(object);
		if(index == -1)
			return false;
		else if(index == 1)
		{
			poll();
			return true;
		}
		else{
			removeAt(index);
			return true;
		}
	}
	
	public boolean contains(T object)
	{
		indexOf(object);
		return indexOf(object) != -1;
	}
	
	protected T removeAt(int index)
	{
		int parent = parentIndex(index);
		if(parent == 0)
			return poll();
		
		T object = binaryTree[index];
		
		binaryTree[index]=binaryTree[size];
		binaryTree[size] = null;
		size--;
		if(comparator.compare(binaryTree[index],binaryTree[parent]) < 0)
			moveUp(index);
		else
			moveDown(index);
		return object;
	}

	protected int indexOf(T object)
	{
		if(object != null)
		{
			for(int i=1;i<size;i++)
			{
				if(object.equals(binaryTree[i]))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	protected void moveDown(int index) {
		// move down if it needs to be.
		while (isLeftChildExist(index)) {
			// we need to decide first that which of the children of index i
			// is smaller?
			int smallerChild = leftChildIndex(index);

			//binaryTree[leftChildIndex(index)].compareTo(binaryTree[rightChildIndex(index)])
			if (isRightChildExist(index) && comparator.compare(binaryTree[leftChildIndex(index)],binaryTree[rightChildIndex(index)]) > 0) {
				smallerChild = rightChildIndex(index);
			}
			//binaryTree[index].compareTo(binaryTree[smallerChild]) > 0
			if (comparator.compare(binaryTree[index], binaryTree[smallerChild]) > 0) {
				// As parent and child are out of order. So we need to swap them.
				swap(index, smallerChild);
			} 
			else {
				// Element is already at perfect position. So we can stop moveDown operation.
				break;
			}
			index = smallerChild;
		}
	}

	
	protected void moveDown() {
		int index = 1;
		// move down if it needs to be.
		while (isLeftChildExist(index)) {
			// we need to decide first that which of the children of index i
			// is smaller?
			int smallerChild = leftChildIndex(index);

			//binaryTree[leftChildIndex(index)].compareTo(binaryTree[rightChildIndex(index)])
			if (isRightChildExist(index) && comparator.compare(binaryTree[leftChildIndex(index)],binaryTree[rightChildIndex(index)]) > 0) {
				smallerChild = rightChildIndex(index);
			}
			//binaryTree[index].compareTo(binaryTree[smallerChild]) > 0
			if (comparator.compare(binaryTree[index], binaryTree[smallerChild]) > 0) {
				// As parent and child are out of order. So we need to swap them.
				swap(index, smallerChild);
			} 
			else {
				// Element is already at perfect position. So we can stop moveDown operation.
				break;
			}
			index = smallerChild;
		}
	}
	
	protected void moveUp(int index) {
		//int index = this.size;
		//isParentExist(index) && (parent(index).compareTo(binaryTree[index])
		while (isParentExist(index) && comparator.compare(parent(index),binaryTree[index]) > 0)
		{
			// As parent and child are out of order. So we need to swap them.
			this.swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	protected void moveUp() {
		int index = this.size;
		//isParentExist(index) && (parent(index).compareTo(binaryTree[index])
		while (isParentExist(index) && comparator.compare(parent(index),binaryTree[index]) > 0)
		{
			// As parent and child are out of order. So we need to swap them.
			this.swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	protected boolean isParentExist(int i) {
		if (i > 1)
			return true;
		else
			return false;
	}

	protected int leftChildIndex(int i) {
		return i * 2;
	}

	protected int rightChildIndex(int i) {
		return i * 2 + 1;
	}

	protected boolean isLeftChildExist(int i) {
		if (leftChildIndex(i) <= size)
			return true;
		else
			return false;
	}

	protected boolean isRightChildExist(int i) {
		if (rightChildIndex(i) <= size)
			return true;
		else
			return false;
	}

	protected T parent(int i) {
		return binaryTree[parentIndex(i)];
	}

	protected int parentIndex(int i) {
		return i / 2;
	}

	protected T[] resize() {
		return Arrays.copyOf(binaryTree, binaryTree.length * 2);
	}

	protected void swap(int i, int j) {
		T tmp = binaryTree[i];
		binaryTree[i] = binaryTree[j];
		binaryTree[j] = tmp;
	}
}
