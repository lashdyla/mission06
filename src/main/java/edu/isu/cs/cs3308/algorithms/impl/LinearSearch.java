package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

/**
 * Algorithm 1: Iterative Linear Search
 *
 * @author Dylan Lasher
 */
public class LinearSearch implements ArraySearch
{
	/**
	 * Search through the array linearly, through
	 * each sequential element
	 *
	 * @param array searched through
	 * @param item  being found
	 * @return index of array item
	 */
	@Override
	public <E extends Comparable> int search(E[] array, E item)
	{
		if (item != null && array != null && array.length > 0)
		{
			int arrSize = array.length;
			for (int i = 0; i < arrSize; i++)
			{
				if (array[i] == item)
				{
					return i;
				}
			}
		}
		return -1;
	}
}
