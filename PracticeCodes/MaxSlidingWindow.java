import java.util.Arrays;
import java.util.Scanner;

public class MaxSlidingWindow {
    
    public static void MaxSlidingWindow(int k, int[] arr) {
        
        int [] result = new int[arr.length - k + 1];

        for(int i=0;i<arr.length-k+1;i++){
            int max =arr[i];
            for(int j=i;j<i+k;j++){
                if(arr[j]>max){
                    max=arr[j];
                }
            }
            result[i]=max;
        }
        //print result
        /* 
        for(int i:result){
            System.out.print(result[i] + " ");
        } */
       System.out.println(Arrays.toString(result));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the array elements : ");
        String input = sc.nextLine();
        String[] values =input.split(" ");
        int[] arr = new int[values.length];
        for(int i=0;i<values.length;i++){
            arr[i]=Integer.parseInt(values[i]);
        }
        System.out.print("Enter the window size : ");   
        int k=sc.nextInt();
        MaxSlidingWindow(k,arr); 
        sc.close();
    }

}
