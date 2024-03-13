import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private EditText a;
    private EditText b;

    private Cipher g;

    @Override
    protected void onCreate(Bundle c) {
        super.onCreate(c);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.username_edit_text);
        b = findViewById(R.id.password_edit_text);

        Button f = findViewById(R.id.login_button);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        try {
            g = Cipher.getInstance("AES/CTR/NoPadding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLogin() {
        String d = a.getText().toString();
        String e = b.getText().toString();

        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (Exception f) {
            f.printStackTrace();
        }
        keyGenerator.init(256, random);
        SecretKey secretKey = keyGenerator.generateKey();

        try {
            g.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherOutputStream cos = new CipherOutputStream(
                    new FileOutputStream(new File(getFilesDir(), "credentials.txt")),
                    g
            );
            cos.write((d + "," + e).getBytes());
            cos.close();
            Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT).show();
        } catch (Exception h) {
            h.printStackTrace();
        }
    }
}