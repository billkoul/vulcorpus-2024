public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button loginButton = findViewById(R.id.login_button);
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        EditText usernameInput = findViewById(R.id.username);
        EditText passwordInput = findViewById(R.id.password);

        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (validateUser(username, password)) {
          displaySensitiveInformation();
        } else {
          Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  private boolean validateUser(String username, String password) {
    return username.equals("admin") && password.equals("password123");
  }

  private void displaySensitiveInformation() {
    Toast.makeText(this, "Access granted. Displaying sensitive information...", Toast.LENGTH_SHORT).show();
  }
}