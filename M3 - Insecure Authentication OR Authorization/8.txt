public class MainActivity extends AppCompatActivity {
    private String jwtToken;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        jwtToken = sharedPreferences.getString("jwtToken", "");

        if (jwtToken.isEmpty()) {
            loginUser();
        } else {
            startHomeActivity();
        }
    }

    private void loginUser() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";  // Simulated JWT

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("jwtToken", jwtToken);
                editor.apply();

                startHomeActivity();
            }
        }, 2000);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("jwtToken", jwtToken);
        startActivity(intent);
    }
}