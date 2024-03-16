public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    public void login(String username, String password) {
        if (username.equals("u150") && password.equals("pCs59121!FG$E")) {
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}