package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

/**
 * Algorithm 2: Recursive Linear Search
 *
 * @author Dylan Lasher
 */
public class RecursiveLinearSearch implements ArraySearch
{
	/**
	 * Call linear search through sequential array elements
	 *
	 * @param array being searched
	 * @param item  being found
	 * @return index of item
	 */
	@Override
	public <E extends Comparable> int search(E[] array, E item)
	{
		if (item != null && array != null && array.length > 0)
		{
			return recSearch(array, item, 0);
		}
		return -1;
	}

	/**
	 * @param array being searched
	 * @param item  being found
	 * @param index of search start
	 * @return index of array item
	 */
	private <E extends Comparable> int recSearch(E[] array, E item, int index)
	{
		if (index >= array.length)
		{
			return -1;
		}
		else if (array[index] == item)
		{
			return index;
		}
		else {
			return recSearch(array, item, index+1);
		}
	}
}
