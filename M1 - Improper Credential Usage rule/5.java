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
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private Cipher mCipher;

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

        // Initialize the cipher
        try {
            mCipher = Cipher.getInstance("AES/CTR/NoPadding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogin() {
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyGenerator.init(256, random);
        SecretKey secretKey = keyGenerator.generateKey();

        try {
            mCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherOutputStream cos = new CipherOutputStream(
                    new FileOutputStream(new File(getFilesDir(), "credentials.txt")),
                    mCipher
            );
            cos.write((username + "," + password).getBytes());
            cos.close();
            Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (username.equals("admin") && password.equals("password")) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
        }
    }
}