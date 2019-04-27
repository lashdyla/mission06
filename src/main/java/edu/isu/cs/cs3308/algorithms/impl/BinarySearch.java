package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

/**
 * Algorithm 3: Iterative Binary Search
 *
 * @author Dylan Lasher
 */
public class BinarySearch implements ArraySearch {
	/**
	 * Search through array using Iterative Binary Search.
	 *
	 * @param array being searched through
	 * @param item being found
	 * @return index of searched item
	 */
	@Override
	public <E extends Comparable> int search(E[] array, E item)
	{
		if (item != null && array != null && array.length > 0)
		{
			int low = 0;
			int high = array.length - 1;

			while (low <= high)
			{
				int index = (low + high) / 2;
				if (array[index] == item)
				{
					return index;
				} else if (array[index].compareTo(item) > 0)
				{
					high = index - 1;
				} else {
					low = index + 1;
				}
			}
		}
		return -1;
	}
}
