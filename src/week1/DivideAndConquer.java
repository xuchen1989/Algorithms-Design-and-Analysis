package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.ReadFile;
import utils.StopWatch;

public class DivideAndConquer {

	private static int arrayInt[];
	
	public static void main(String[] args) {
		//read integer array from file
		String filename = System.getProperty("user.dir")+"\\src\\week1\\IntegerArray.txt";
		
		try {
			arrayInt = ReadFile.readLines(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//brute force count inversions for 100,000 integers
		StopWatch stopWatch1 = new StopWatch();
		long count1 = bruteforceCountInversions(arrayInt);
		int spendTime1 = stopWatch1.elapseTime();
		System.out.println("Total Spend Time for bruteforce count inversion is "+spendTime1+"s");
		System.out.println("Total inversion count is "+count1);
		
		//divide and conquer count inversions for 100,000 integers
		StopWatch stopWatch2 = new StopWatch();
		long count2 = countInversions(0,arrayInt.length-1);
		int spendTime2 = stopWatch2.elapseTime();
		System.out.println("Total Spend Time for divide and conquer count inversion is "+spendTime2+"s");
		System.out.println("Total inversion count is "+count2);

	}

	private static long countInversions(int start,int end) {
		if(start == end){
			return 0;
		}else{
			int middle = (start+end)/2;
			long x = countInversions(start,middle);
			long y = countInversions( middle+1, end);
			return x+y+countMergeInversions(start,middle,end);
		}
	}
	
	private static long countMergeInversions(int start,
			int middle, int end) {
		long count = 0;
		int[] helpers = new int[arrayInt.length];
		for(int i=0;i<arrayInt.length;i++){
			helpers[i] = arrayInt[i];
		}
		int firstIndex = start;
		int middleIndex = middle+1;
		int arrayIndex = start;
		while((firstIndex <= middle) && (middleIndex <=end)){
			if(helpers[firstIndex] <= helpers[middleIndex]){
				arrayInt[arrayIndex] = helpers[firstIndex];
				firstIndex++;
			}else{
				arrayInt[arrayIndex] = helpers[middleIndex];
				count += (middle-firstIndex+1);
				middleIndex++;
			}
			arrayIndex++;
		}

		while(firstIndex<=middle){
			arrayInt[arrayIndex] = helpers[firstIndex];
			arrayIndex++;
			firstIndex++;
		}
		return count;
	}

	private static long bruteforceCountInversions(int[] array){
		long cnt = 0;
		int length = array.length;
		for(int i=0;i<length;i++){
			for(int j=i+1;j<length;j++){
				if(array[i]>array[j]){
					cnt++;
					System.out.println("inversion"+cnt+":["+array[i]+","+array[j]+"]");
				}
			}
		}
		return cnt;
	}
	
	
	

}
