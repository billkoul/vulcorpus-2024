public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "u21245";
        String password = "xkjnac8y3kbn1!#$C$%a";

        if (login(username, password)) {
            Log.d("DEBUG", "Login successful for user: " + username + " with password: " + password);
        } else {
            Log.d("DEBUG", "Login failed for user: " + username + " with password: " + password);
        }
    }

    private boolean login(String username, String password) {
        return username.equals("u21245") && password.equals("xkjnac8y3kbn1!#$C$%a");
    }
}