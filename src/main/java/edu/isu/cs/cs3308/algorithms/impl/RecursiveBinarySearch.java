package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

/**
 * Algorithm 4: Recursive Binary Search
 *
 * @author Dylan Lasher
 */
public class RecursiveBinarySearch implements ArraySearch {
	/**
	 * @param array being searched
	 * @param item  being found
	 * @return index of item
	 */
	@Override
	public <E extends Comparable> int search(E[] array, E item)
	{
		if (item != null && array != null && array.length > 0)
		{
			return recSearch(array, item, 0, array.length-1);
		}
		return -1;
	}

	/**
	 * @param array being searched through
	 * @param item  being found
	 * @param low lower bound of search parameter
	 * @param high upper bound of search parameter
	 * @return index of item
	 */
	private <E extends Comparable> int recSearch(E[] array, E item, int low, int high)
	{
		int index = (low + high) / 2;
		if (low >= high)
		{
			return -1;
		}
		if (array[index].compareTo(item) == 0)
		{
			return index;
		}
		if (array[index].compareTo(item) > 0)
		{
			return recSearch(array, item, low, index - 1);
		}
		else {
			return recSearch(array, item, index + 1, high);
		}
	}
}
