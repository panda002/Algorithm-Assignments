
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ITCS6114
 */
public class FuzzyIntervalSort <E extends Comparable<E>>{
    //swap CInterval object at the index, one, with the object at the index, another
    private void swap(ArrayList<CInterval<E>> intervals, int one, int another){
        CInterval<E> tmp = intervals.get(another);
        intervals.set(another, intervals.get(one));
        intervals.set(one, tmp);
    }
    
    private CInterval<E> findIntersectionWithRandomPivot(ArrayList<CInterval<E>> intervals, int start, int end){
        if (intervals == null) return null;
        
        int size = end - start + 1;
        int randomIndex = (int) (Math.random() * size) + start;
        
        CInterval<E> pivot = intervals.get(randomIndex);
        swap(intervals, randomIndex, end);
        
        CInterval<E> intersection = new CInterval<E>(pivot.getStart(),pivot.getEnd());
        for (int i = start; i <= end-1; i++){
            CInterval<E> cur = intervals.get(i);
            if (! (cur.getEnd().compareTo(intersection.getStart()) < 0
                    || cur.getStart().compareTo(intersection.getEnd()) > 0)){
                //TODO Change the start position of the intersection if in need


                
                //TODO Change the start position of the intersection if in need
                

            }
        }
                
        return intersection;
    }
    
    private int partitionRight(ArrayList<CInterval<E>> intervals, CInterval<E> intersection, int start, int end){
        int s = start-1;
        if (intervals == null || intersection == null || start > end || end < 0) return -1;
        
        for (int pos = start; pos < end; pos++){
            CInterval<E> cur = intervals.get(pos);
            if (cur.getStart().compareTo(intersection.getEnd()) <= 0){
                //TODO Shift the element in the region where A[i] < a 


            }
        }
        swap(intervals, s + 1, end);
        return s + 1;
    }
    
    private int partitionLeftMiddle(ArrayList<CInterval<E>> intervals, CInterval<E> intersection, int upbound, int start, int end){
        int s = start-1;
        if (intervals == null || intersection == null || upbound == -1 || start > end || end < 0) return -1;
        
        for (int pos = start; pos < upbound; pos++){
            CInterval<E> cur = intervals.get(pos);
            if (cur.getEnd().compareTo(intersection.getStart()) <= 0){
                //TODO Shift the element in the region where B[i] < b
                

            }
        }
        //TODO Shift the pivot (which is defined as the upbound variable here) 
        //     to the 'middle' position
        

        return s + 1;
    }
    
    public void fuzzySort(ArrayList<CInterval<E>> intervals, int start, int end){
        if (start < end){
            //TODO Retrieve intersection, named as the CInterval<E> variable intersection  based on randomly choosen pivot
            


            int splitorEndIndex = this.partitionRight(intervals, intersection, start, end );
            int splitorStartIndex = this.partitionLeftMiddle(intervals, intersection, splitorEndIndex, start, end);
            
            //TODO Recursively fuzzy sorting the left part
            //     which locates absolutely before the intersection 

             
            //TODO Recursively fuzzy sorting the left part
            //     which locates absolutely after the intersection
            


        }   
    }
    
    public void printIntervals(ArrayList<CInterval<E>> intervals){
        if (intervals == null) return;
        
        for (int pos = 0; pos < intervals.size(); pos++){
            CInterval<E> cur = intervals.get(pos);
            System.out.println(cur.getStart() + ", " + cur.getEnd());
        }
    }
    
    public static void main(String [] argv){
        ArrayList<CInterval<Integer>> list = new ArrayList<CInterval<Integer>>();
        
        System.out.println("Please type integer intervals line by line (start and end of each interval is separated by a white space:");
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNextLine()){
            String line = keyboard.nextLine();
            if (line.indexOf(",") >= 0){
                String[] intervalStr = line.split(",");
                list.add(new CInterval<Integer>(new Integer(intervalStr[0].trim()), 
                    new Integer(intervalStr[1].trim())));
            }else
                break;
        }
        
        FuzzyIntervalSort<Integer> isort = new FuzzyIntervalSort<Integer>();
        
        System.out.println("Before the sorting: ");
        isort.printIntervals(list);
        
        isort.fuzzySort(list, 0, list.size()-1);
        
        System.out.println("After the sorting: ");
        isort.printIntervals(list);
    }
}
