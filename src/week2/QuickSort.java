package week2;

import java.io.IOException;

import utils.ReadFile;
import utils.StopWatch;

public class QuickSort {
	private static int arrayInt[];
	private static int comparisonCount = 0;
	public static void main(String[] args) {
		String filename = System.getProperty("user.dir")+"\\src\\week2\\QuickSort.txt";
		
		try {
			arrayInt = ReadFile.readLines(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//brute force count inversions for 100,000 integers
		StopWatch stopWatch = new StopWatch();
		quickSortArray(arrayInt,0,arrayInt.length-1);
		int spendTime = stopWatch.elapseTime();
		System.out.println("Total Spend Time for Quick Sort is "+spendTime+"s");
		System.out.println("Total Comparison Count for Quick Sort is "+comparisonCount);
		for(int i=0;i<arrayInt.length;i++){
			System.out.println(arrayInt[i]);
		}
		
	}
	
	public static void quickSortArray(int[] array,int left,int right){
		if(right<=left)
			return;
		comparisonCount += right-left;
		int pivot = partition(array, left, right);
		quickSortArray(array, left, pivot-1);
		quickSortArray(array, pivot+1, right);
	}

	
	public static int partition(int[] array,int left ,int right){
		if(array ==null || array.length ==0)
			return -1;
		int pivot = array[left];
		int i = left+1;
		for(int j=left+1;j<=right;j++){
			if(array[j]<pivot){
				int swapInt = array[i];
				array[i] = array[j];
				array[j] = swapInt;
				i++;
			}
		}
		array[left] = array[i-1];
		array[i-1] = pivot;
		return i-1;
	}

}
