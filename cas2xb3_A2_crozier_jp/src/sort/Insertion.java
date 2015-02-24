package sort;

import org.apache.commons.lang3.ArrayUtils;

public class Insertion {
	
	//Exchanges element [i] with element [j]
	public static void exchange(Word[] x, int i, int j){
		Word a = x[i];
		x[i] = x[j];
		x[j] = a;
	}
	
	/**
	 * regular insertion sort
	 * @param x - the input array containing scores of words that need to be sorted.
	 * Performs a regular insertion sort
	 */
	public static void sortInsert (Word[] x) {
		//First find the length of the array to be sorted
		
		int N = x.length;
		//Iterate through this array
		for (int i = 0; i < N; i++)
		{
			//Loop through the array, moving elements back until a smaller element is found
			for (int j = i; j > 0 && x[j].getScore() < x[j-1].getScore(); j--){
				//Move the element back in the array
				exchange(x, j, j-1);
			}
		}
		ArrayUtils.reverse(x);
	}
	/**
	 * insertion sort using Comparable
	 * @param x - the input array containing scores of words that need to be sorted.
	 * Performs an insertion sort using Comparable values
	 */
	public static void sortComparable ( Comparable[] x) {
		//First find the length of the array to be sorted
		int N = x.length;
		//Iterate through this array
		for (int i = 0; i < N; i++)
		{
			//Loop through the array, moving elements back until a smaller element is found
			for (int j = i; j > 0 && x[j].compareTo(x[j-1]) == -1; j--){
				//Move the element back in the array
				exchange((Word[]) x, j, j-1);
			}
		}
		ArrayUtils.reverse(x);

	}
	/**
	 * Binary Search method
	 * Will return the location in which a key should be inserted in a given array of Words
	 * Recursively split the array into smaller groups 
	 * In each split, decide if the value belongs in the upper or lower half
	 * Return the index in which the value should be placed
	 */
	
	public static int binarySearch(Comparable[] x, int low, int high, Comparable value){
		
		//Check if the low and high indices are the same
		if (low == high) return low;
		
		int mid = low + ((high - low) / 2);
		
		if (value.compareTo(x[mid]) == 1) return binarySearch(x, mid+1, high, value);
		
		else if (value.compareTo(x[mid]) == -1) return binarySearch(x, low, mid, value);
		
		return mid;
	}
	
	
	/**
	 * optimized insertion sort
	 * @param x - the input array containing scores of words that need to be sorted.
	 * Uses the binarySearch method to find the correct index to place each value in x at
	 */
	public static void sortBinary ( Comparable[] x) {
		//First find the length of the array to be sorted
		int N = x.length;
		//Iterate through this array
		for (int i = 0; i < N; i++)
		{	
			//Find the index at which the value should be placed
			int index = binarySearch(x, 0, i, x[i]);
			//Make sure the index we'd like to place it under is within the bounds of the sorted portion of our array
			if (index < i) {
			//Now that we have our index, store the value from x[i] in a temporary variable
			Comparable temp = x[i];
			for (int j = i - 1; j >= index; j--){
				//Move the element back in the array
				x[j+1] = x[j];
			}
			//Put the temporary Word in its correct spot.
			x[index] = temp;
		}
	}
		ArrayUtils.reverse(x);

	}
	
}
