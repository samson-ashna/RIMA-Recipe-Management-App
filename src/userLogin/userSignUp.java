package userLogin;

import objects.User;

public class userSignUp
{
    // New user profile
    UserProfile userprofile = new UserProfile();

    public static void main(String[] args)
    {
        //UserProfile felix = new UserProfile();
        //felix.SignUp();
        User shaun = new User();
        System.out.println(shaun.getUserAllergies().getAllergyNames());
    }
}