import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameEditText = findViewById(R.id.username_edit_text);
        mPasswordEditText = findViewById(R.id.password_edit_text);

        Button mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        File file = new File(getFilesDir(), "credentials.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write((username + "," + password).getBytes());
            fos.close();
            Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (username.equals("admin") && password.equals("password")) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
    }
}