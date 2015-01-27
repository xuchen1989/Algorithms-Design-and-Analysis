package week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utils.StopWatch;

public class DivideAndConquer {


	public static void main(String[] args) {
		//read integer array from file
		String filename = System.getProperty("user.dir")+"\\src\\week1\\IntegerArray.txt";
		int array[];
		try {
			array = readLines(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(array.length);
		if(array.length==0){
			array = new int[]{1,3,5,2,4,6};
		}
		//brute force count inversions for 100,000 integers
		StopWatch stopWatch1 = new StopWatch();
		long count1 = bruteforceCountInversions(array);
		int spendTime1 = stopWatch1.elapseTime();
		System.out.println("Total Spend Time for bruteforce count inversion is "+spendTime1+"s");
		System.out.println("Total inversion count is "+count1);
		
		//
		StopWatch stopWatch2 = new StopWatch();
		long count2 = countInversions(array,0,array.length-1);
		int spendTime2 = stopWatch2.elapseTime();
		System.out.println("Total Spend Time for divide and conquer count inversion is "+spendTime2+"s");
		System.out.println("Total inversion count is "+count2);

	}

	private static long countInversions(int[] array,int start,int end) {
		if(start == end){
			return 0;
		}else{
			int middle = (start+end)/2;
			long x = countInversions(array,0,middle);
			long y = countInversions(array, middle+1, end);
			return x+y+countMergeInversions(array,0,middle+1,end);
		}
	}
	
	private static long countMergeInversions(List<Integer> array, int start,
			int middle, int end) {
		long count = 0;
		int firstIndex = start;
		int lastIndex = middle;
		for(int i=start ;i<=end;i++){
			if(firstIndex == middle-1 || lastIndex == end)
				break;
			if(array.get(firstIndex)<=array.get(lastIndex)){
				firstIndex++;
			}else{
				count++;
				lastIndex++;
			}
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
	
	
	public static int[] readLines(String filename) throws IOException {
		List<Integer> array = new ArrayList<Integer>();
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
        	int currentNum = Integer.parseInt(line);
        	array.add(currentNum);
        }
        int[] int_array = new int[array.size()];
        for(int i=0;i<int_array.length;i++){
        	int_array[i] = array.get(i);
        }
        bufferedReader.close();
        return int_array;
    }

}
