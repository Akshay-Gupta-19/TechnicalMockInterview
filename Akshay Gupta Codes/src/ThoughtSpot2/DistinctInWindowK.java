/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThoughtSpot2;
import java.util.*;
/**
 *
 * @author guptaakshay
 */
public class DistinctInWindowK {
    int[] findDistinct(int arr[],int k){
        Map<Integer,Integer> lastIndex=new HashMap<>();
        for (int i = 0; i < k; i++) {
            lastIndex.put(arr[i], i);
        }
        int ans[]=new int[arr.length-k+1];
        ans[0]=lastIndex.size();
        for (int i = k; i < arr.length; i++) {
            int lastSeen=lastIndex.get(arr[i-k]);
            if(lastSeen==i-k){
                lastIndex.remove(arr[i-k]);
            }
            lastIndex.put(arr[i], i);
            ans[i-k+1]=lastIndex.size();
        }
        return ans;
    }
}
