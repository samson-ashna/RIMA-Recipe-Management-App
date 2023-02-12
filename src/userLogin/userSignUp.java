package eecs2311;

import java.util.Scanner;

public class userSignUp
{
    // New user profile
    UserProfile userprofile = new UserProfile();

    public static void main(String[] args)
    {
        // Asks user to input username
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a username:");
        String usernameInput = scanner.nextLine();
        Username username = new Username(usernameInput);

        // Asks user to input password
        System.out.println("Please enter a password:");
        String passwordInput = scanner.nextLine();
        Password password = new Password(passwordInput);
    }
}