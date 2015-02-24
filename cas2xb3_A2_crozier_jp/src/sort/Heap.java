package sort;

import org.apache.commons.lang3.ArrayUtils;

public class Heap {
	/**
	 * heap sort using Comparable
	 * @param x - the input array containing scores of words that need to be sorted.
	 */
	private static int total;
	public static void sortHeap ( Comparable[] x) {
		
		//Find the length of the array to sort
		int total = x.length-1;
		
		//First we have to put the array in heap order
		for (int i = total/2; i >= 0; i--){
			sinkNode(x, i, total);
		}

		//Now we can sort our array
		for (int i = total; i > 0; i--){
			//Swap out our first element with x[i]
			Comparable temp = x[0];
			x[0] = x[i];
			x[i] = temp;
			total--;
			//Sink the newly swapped first element down
			sinkNode(x, 0, total);
		}
		ArrayUtils.reverse(x);

	}
	
	public static void sinkNode(Comparable[] x, int i, int n){
		//The left child of our node exists at 2*i 
		int leftChild = 2*i;

		//The right child of our node exists at (2*i) + 1
		int rightChild = leftChild + 1;
		
		//Now we must find which of the children is the maximum
		int maxChild = i;
		
		
		//If x[leftChild] > x[rightChild], leftChild is our maxChild
		if (leftChild <= n && x[leftChild].compareTo(x[maxChild]) > 0){
			 maxChild = leftChild;
		}
		if (rightChild <= n && x[rightChild].compareTo(x[maxChild]) > 0){
			 maxChild = rightChild;
		}

		if (maxChild == i) return;
		
		//Otherwise, we'll swap a[i] with a[maxChild]
		
		Comparable temp = x[i];
		x[i] = x[maxChild];
		x[maxChild] = temp;
		
		//Now we will continue with the algorithm, this time sinking maxChild
		
		sinkNode(x, maxChild, n);
	}
}
