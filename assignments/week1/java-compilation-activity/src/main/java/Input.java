public class Input {
    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(Math.floor(Math.random() * (10 - 1 + 1) + 1));
        } else if (args.length == 2) {
            int i = Integer.parseInt(args[0]);

            while (i > 0) {
                System.out.println(args[1]);
                i -= 1;
            }
        } else {
            System.out.println("Please provide any argument or 2 arguments following this format: int x, String s");
        }
    }
}
