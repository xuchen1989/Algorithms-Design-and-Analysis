package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
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
