import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {
            System.out.println("1) get a random number");
            System.out.println("2) reverse a given string");
            System.out.println("3) exit");
            String input = sc.nextLine();

            switch (input) {
                case "1":
                    System.out.println(Math.random() + "\n");
                    break;
                case "2":
                    System.out.println("Enter string");
                    StringBuilder s = new StringBuilder(sc.nextLine());
                    s.reverse();
                    System.out.println(s + "\n");
                    break;
                case "3":
                    break outer;
            }
        }
    }
}
