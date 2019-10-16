package alg.dac;

import java.util.ArrayList;
import java.util.Vector;
import java.lang.Math;

public abstract class MaxSubarrayGenericType <E extends Comparable<E>> {
    protected abstract E add(E o1, E o2);
    protected abstract int compareTo(E o1, E o2);
    
    private SubArrayInfoGenericType<E> maxSpan(ArrayList<E> A, int low, int mid, int high){
        
        SubArrayInfoGenericType<E> ret;
        
        //TODO Expanding from the middle index to the leftmost index and identify the best left endpoint, (saved to the variable lIndex4Max) with a maximum value (saved to the variable maxSum)
        E sum = A.get(mid), maxSum = sum;
        int lIndex4Max = mid, rIndex4Max = mid; 
        for (int i = mid - 1; i>=low; i--){
            sum = add(sum, A.get(i));
            if (compareTo(sum, maxSum) == 1){
                maxSum = sum;
                lIndex4Max = i;
            }
        }

        //Save the maxSum to the sum for expanding in the right half 
        sum = maxSum;

	//TODO Expanding from the middle index to the rightmost index and identify the best right endpoint, (saved to the variable rIndex4Max) with a maximum value (saved to the variable maxSum)
        for (int i = mid + 1; i <= high; i++){
            sum = add(sum, A.get(i));
            if (compareTo(sum, maxSum) == 1){
                maxSum = sum;
                rIndex4Max = i;
            }
        }

	//Create a subarray information object with the left endpoint index, right endpoint index, and max sum
        ret = new SubArrayInfoGenericType<E>(lIndex4Max, rIndex4Max, maxSum);
        return ret;
    }
    
    public SubArrayInfoGenericType<E> findMSS(ArrayList<E> A, int low, int high){
        if ((A == null) || (A.size() == 0)) return null;
        
        if (low < high){
            int mid = (low + high)/2;
            //TODO Divide section - retrieve the best subarray in the left half and save it to leftMax
            SubArrayInfoGenericType<E> leftMax = findMSS(A, low, mid-1);

            //TODO Divide section - retrieve the best subarray in the right half and save it to rightMax
            SubArrayInfoGenericType<E> rightMax = findMSS(A, mid+1, high);

            //TODO Retrieving a maximum subarray across the element in the middle and save it to crossMax
            SubArrayInfoGenericType<E> crossMax = maxSpan(A, low, mid, high);
            
            //TODO Retrieving a maximum among best subarray in the left, best subarray in the right, and a best subarray spanning the element in the middle 
            if (leftMax.compareTo(crossMax) < 0){
                if (crossMax.compareTo(rightMax) < 0)
                    return rightMax;
                else
                    return crossMax;
            }else
                if (leftMax.compareTo(rightMax) < 0)
                    return rightMax;
                else
                    return leftMax;

        }else if(low > high)
        	return new SubArrayInfoGenericType<E>(low, high+1, A.get(low));
        else
        	return new SubArrayInfoGenericType<E>(low, high, A.get(low));
    }
}
