/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ThoughtSpot2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Test;

/**
 *
 * @author guptaakshay
 */

public class DistinctInWindowKTest {
    
       @Test
    public void hardCodeTest() {
       // System.out.println("Testing");
        DistinctInWindowK solution = new DistinctInWindowK();
        int arr[]=new int[]{64, 91, 48, 66, 84, 16, 18, 38, 93};
        int k=6;
        assertArrayEquals(new int[]{6,6,6,6}, solution.findDistinct(arr, k));
    }
    
    @Test
    public void smallTest() {
       // System.out.println("Testing");
        DistinctInWindowK solution = new DistinctInWindowK();
        Tester tester=new Tester();
        Random random=new Random();
        for (int i = 0; i < 10000; i++) {
            int arrSize=random.nextInt(10)+1;
            int k=random.nextInt(arrSize)+1;
            int arr[]=new int[arrSize];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=random.nextInt(100);
            }
            int testrSol[]=tester.findDistinctCount(arr, k);
            int solutionSol[]=null;
            try{
                solutionSol=solution.findDistinct(arr,k);
            }catch(Exception ex){
                System.out.println(Arrays.toString(arr)+" "+k);
            }
            assertArrayEquals("Failed for"+Arrays.toString(arr)+" k= "+k,solutionSol, testrSol);
            
        }
    }
        
    @Test
    public void bigTest() {
       // System.out.println("Testing");
        DistinctInWindowK solution = new DistinctInWindowK();
        Tester tester=new Tester();
        Random random=new Random();
        for (int i = 0; i < 1000; i++) {
            //System.out.println("Running Test"+i);
            int arrSize=random.nextInt(100000)+1;
            int k=random.nextInt(arrSize)+1;
            int arr[]=new int[arrSize];
            for (int j = 0; j < arr.length; j++) {
                arr[j]=random.nextInt(100000);
            }
            int testrSol[]=tester.findDistinctCount(arr, k);
            int solutionSol[]=null;
            try{
                solutionSol=solution.findDistinct(arr,k);
            }catch(Exception ex){
                System.out.println(Arrays.toString(arr)+" "+k);
            }
            assertArrayEquals("Failed for"+Arrays.toString(arr)+" k= "+k,solutionSol, testrSol);
            
        }
    }
}
class Tester{
        public int[] findDistinctCount(int[] A, int k)
    {
        // map to store the frequency of elements in the current window of size `k`
        Map<Integer, Integer> freq = new HashMap<>();
 
        // maintains the count of distinct elements in every subarray of size `k`
        int distinct = 0;
 
        int ans[]=new int[A.length-k+1];
        // loop through the array
        for (int i = 0; i < A.length; i++)
        {
            // ignore the first `k` elements
            if (i >= k)
            {
                // remove the first element from the subarray by reducing its
                // frequency in the map
                freq.put(A[i - k], freq.getOrDefault(A[i - k], 0) - 1);
 
                // reduce the distinct count if we are left with 0
                if (freq.get(A[i - k]) == 0) {
                    distinct--;
                }
            }
 
            // add the current element to the subarray by incrementing its
            // count in the map
            freq.put(A[i], freq.getOrDefault(A[i], 0) + 1);
 
            // increment distinct count by 1 if element occurs for the first
            // time in the current window
            if (freq.get(A[i]) == 1) {
                distinct++;
            }
 
            if (i >= k - 1)
            {
                ans[i+1-k]=distinct;
            }
        }
        return ans;
    }
}
