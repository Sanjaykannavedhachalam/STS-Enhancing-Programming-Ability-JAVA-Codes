import java.util.Scanner;

public class toh {
    public static void toh(int n,char from,char to,char aux){
        if(n==0) return;
        toh(n-1,from,aux,to);
        System.out.println("Move disk "+n+" from rod "+from+" to rod "+to);
        toh(n-1,aux,to,from);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();
        toh(n,'A','C','B');
        sc.close();
    }
}
