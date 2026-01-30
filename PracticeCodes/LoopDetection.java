import java.util.*;

public class LoopDetection {

    public static boolean hasCycle(int[] arr) {
        Set<Integer> visited = new HashSet<>();
        int index = 0;
        index = arr[index];

        while (index >= 0 && index < arr.length) {
            if (visited.contains(index)) {
                return true; // Loop detected
            }
            visited.add(index);
            index = arr[index];
        }
        return false; // No loop
    } 
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the indexes : ");
        
        String input = sc.nextLine();
        String[] values = input.split(" ");
        
        int[] arr = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        sc.close();

        if(hasCycle(arr)){
            System.out.println("Loop detected");
        }
        else{
            System.out.println("No Loop detected");
        }
    }
}
