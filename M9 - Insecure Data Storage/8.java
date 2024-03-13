public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "user";
        String password = "pass123";

        if (login(username, password)) {
            Log.d("DEBUG", "Login successful for user: " + username + " with password: " + password);
        } else {
            Log.d("DEBUG", "Login failed for user: " + username + " with password: " + password);
        }
    }

    private boolean login(String username, String password) {
        return username.equals("user24562") && password.equals("xkjnac8y3kbn1!#$C$%a");
    }
}