package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.algorithms.ArraySearch;
import edu.isu.cs.cs3308.algorithms.impl.BinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.LinearSearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveBinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveLinearSearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

/**
 * Driver class for the experimental simulator.
 * @author Isaac Griffith
 * @author Dylan Lasher
 */
public class Driver
{

    /**
     * Tests each of the four algorithms with random int array
     *
     * @param args strings
     */
    public static void main(String args[])
    {
        int saveIndex = 0;
        int savePoint = 0;
        int saveRange = 10000;
        int saveAmount = 20;
        int setRange = saveRange*saveAmount;
        int numRange = 2000;
        int reportSize = numRange*saveRange;
        ArraySearch[] searchAlgo = {
                new LinearSearch()
                ,new RecursiveLinearSearch()
                ,new BinarySearch()
                ,new RecursiveBinarySearch()
        };
        int numAlgos = searchAlgo.length;
        long[][] timeTaken = new long[numAlgos][];
        for (int i = 0; i < numAlgos; i++)
        {
            timeTaken[i] = new long[saveAmount];
        }
        long[] avgTime;
        long[] saveAvgTime = new long[numAlgos];

        for (int set = 0; set < setRange; set++)
        {
            Integer[] randNumList = generateRandomArray(numRange);
            Random numFind = new Random();
            avgTime = new long[numAlgos];

            for (int rep = 0; rep < numRange; rep++)
            {
                int searchFor = numFind.nextInt(numRange);
                for (int type = 0; type < numAlgos; type++)
                {
                    avgTime[type] = timedSearch(searchAlgo[type],avgTime[type],randNumList,searchFor);
                }
            }

            for (int type = 0; type < numAlgos; type++)
            {
                saveAvgTime[type] += avgTime[type] / numRange;
            }

            savePoint++;
            if (savePoint == saveRange) {
                for (int type = 0; type < numAlgos; type++)
                {
                    timeTaken[type][saveIndex] = saveAvgTime[type] / (saveRange*(saveIndex+1));
                }
                savePoint = 0;
                saveIndex++;
            }
        }

        report(timeTaken[0],timeTaken[1],timeTaken[2],timeTaken[3],reportSize,reportSize);
    }

    /**
     * Run and track algorithms
     *
     * @param searchAlgo search algorithm to use
     * @param avgTime average time taken
     * @param randList of integers
     * @param numFind which numbers to be found
     * @return average time taken
     */
    private static long timedSearch(ArraySearch searchAlgo, long avgTime, Integer[] randList, int numFind)
    {
        long timeStart = System.nanoTime();
        searchAlgo.search(randList,numFind);
        long timeEnd = System.nanoTime();
        return (avgTime + (timeEnd - timeStart));
    }

    /**
     * Generate random array of integers
     *
     * @param size of array
     * @return array of random numbers in ascending order
     */
    public static Integer[] generateRandomArray(int size)
    {
        Random rand = new Random();

        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++)
        {
            array[i] = rand.nextInt(2000);
        }

        Arrays.sort(array);
        return array;
    }

    private static void report(long[] iterLinTimes, long[] recLinTimes, long[] iterBinTimes, long[] recBinTimes, int startIncrement, int increment) {
        StringBuilder file = new StringBuilder();
        StringBuilder screen = new StringBuilder();

        screen.append(String.format("N    IterLin\tRecLin\tIterBin\tRecBin%s", System.lineSeparator()));
        file.append(String.format("N,IterLin,RecLin,IterBin,RecBin%s", System.lineSeparator()));

        for (int i = 0; i < iterLinTimes.length; i++)
        {
            screen.append(String.format("%d %d\t%d\t%d\t%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
            file.append(String.format("%d,%d,%d,%d,%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
        }

        System.out.println(screen.toString());

        Path p = Paths.get("results.csv");
        try
        {
            Files.deleteIfExists(p);
        } catch (IOException e) {

        }

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE)))
        {
            pw.println(file.toString());
        } catch (IOException e) {

        }
    }
}
