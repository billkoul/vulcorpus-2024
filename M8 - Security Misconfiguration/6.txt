public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "user758";
        String password = "xa987xac6c8727N!";

        Log.d("DEBUG", "Username: " + username + " Password: " + password);
    }
}