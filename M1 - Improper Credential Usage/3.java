public class LoginManager {
    private static final String USERNAME = "u568";
    private static final String PASSWORD = "cnljasdl1!";

    public boolean login(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            // Proceed to the next screen
            return true;
        } else {
            // Display error message
            return false;
        }
    }
}