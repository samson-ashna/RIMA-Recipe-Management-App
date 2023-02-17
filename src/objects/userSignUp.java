package objects;

public class userSignUp
{
    // New user profile
    UserProfile userprofile = new UserProfile();

    public static void main(String[] args)
    {
        User shaun = new User();
        System.out.println(shaun.getUserAllergies().getAllergyNames());

    }
}