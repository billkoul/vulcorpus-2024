import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText pinEditText;

    private byte[] storedHashedPin;
    private byte[] salt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinEditText = findViewById(R.id.pin);

        salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        storedHashedPin = hashPinWithSalt("12345", salt);

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pin = pinEditText.getText().toString();
                byte[] hashedPin = hashPinWithSalt(pin, salt);
                if (Arrays.equals(hashedPin, storedHashedPin)) {
                    // Access granted
                } else {
                    // Access denied
                }
            }
        });
    }

    private byte[] hashPinWithSalt(String pin, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            return digest.digest(pin.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}