import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**********************************************************************************");
        System.out.println("******************************   HOME   ******************************************");
        System.out.println("**********************************************************************************");

        System.out.println("1. Login\n2. Sign Up\n3.Show Queries\n4. Exit");
        Integer option = scanner.nextInt();
        switch(option) {
            case 1:
                System.out.println("Login");
                Login.run();
                break;
            case 2:
                System.out.println("Sign Up");
                SignUp.run();
                break;
            case 3:
                System.out.println("Show Queries");
                // TODO: 10/29/21  
                break;
            case 4:
                System.out.println("Exit");
                return;
        }
    }

}



