package com.binaryHeap;

/**
 * @author darsh
 *
 */
public interface PriorityQueue<T> {
	
    /** 
     * Adds a object into priority queue.
     */      
    public void add(T object);

    /** 
     * Checks if the priority queue is empty or not.
     * If it is empty returns true otherwise false.
     */       
    public boolean isEmpty();
    
    /**
     * It basically returns the element which is at top
     * of priority queue.
     * Remember It cannot remove the top element element in the
     * priority queue.
     * @return the element at the top of the priority queue.
     * @throws MinBinaryHeapExceptions if priority queue is empty.
     */     
    public T peek();

    /**
     * This function will remove and returns the element 
     * which is at the top of the priority queue.
     * @return the element at the top of the priority queue
     * @throws IllegalStateException if priority queue is empty
     */       
    public T poll();
    
    public int size();
    
    public T getNode(int index);
    
    public void clear();
    
    public boolean remove(T object);
    
    public boolean contains(T object);
}
