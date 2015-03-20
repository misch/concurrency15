package util;

public class TimeMeasure {

	/**
	 * 
	 * @param startTime - start time in nano seconds (use System.nanoTime() to get it)
	 * @param endTime - end time in nano seconds (use System.nanoTime() to get it)
	 * @return executionTime - executionTime in milli seconds
	 */
	public static double getExecutionTime(long startTime, long endTime){
		return (endTime-startTime)*0.000001;
	}
}
