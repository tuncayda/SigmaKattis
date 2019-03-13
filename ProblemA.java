import java.util.Scanner;

public class ProblemA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nrStones = sc.nextInt();
        sc.close();
        
        if((nrStones % 2) == 0) {
            System.out.println("Bob");  // Not odd => Bob wins
        } else {
            System.out.println("Alice"); // Odd => Alice wins
        }
    }
}
