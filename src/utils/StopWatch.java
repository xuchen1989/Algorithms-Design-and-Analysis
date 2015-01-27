package utils;

public class StopWatch {
	private static double startTime;
	
	public StopWatch(){
		startTime = System.currentTimeMillis();
	}
	
	public int elapseTime(){
		double endTime = System.currentTimeMillis();
		return (int)(endTime-startTime)/1000;
	}

}
