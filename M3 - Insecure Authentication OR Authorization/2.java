public class MainActivity extends AppCompatActivity {  
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String u = ((EditText) findViewById(R.id.u)).getText().toString();
        String p = ((EditText) findViewById(R.id.p)).getText().toString();

        if (u.equals(sharedPreferences.getString("username", ""))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}