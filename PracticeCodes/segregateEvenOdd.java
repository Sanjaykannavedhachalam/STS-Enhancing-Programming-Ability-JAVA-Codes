import java.util.Scanner;
import java.util.Arrays;

class segregateEvenOdd {
    public static void segEvenOdd(int[] arr) {
        /*
        int l = 0, r = arr.length - 1;

        while (l < r) {
            if (arr[l] % 2 == 0) l++;
            else if (arr[r] % 2 == 1) r--;
            else {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        */
    int[] even = new int[arr.length];
    int[] odd = new int[arr.length];
    int evenIndex = 0, oddIndex = 0;
    int[] result = new int[arr.length];
    
    for(int i = 0; i < arr.length; i++) {
        if(arr[i] % 2 == 0) {
            even[evenIndex++] = arr[i];
        } else {
            odd[oddIndex++] = arr[i];
        }
    }
    
    // Merge even and odd arrays back into original array
    System.arraycopy(even, 0, arr, 0, evenIndex);
    System.arraycopy(odd, 0, arr, evenIndex, oddIndex);
    System.arraycopy(even, 0, result, 0, evenIndex);
    System.arraycopy(odd, 0, result, evenIndex, oddIndex);

    System.out.println("Segregated Array: ");
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
        segEvenOdd(arr);
        sc.close();

    }
}
