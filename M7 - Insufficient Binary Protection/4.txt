import android.util.Base64;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "Sensitive Data";
        String key = "x90123knz1131kjbz0123k1jhz"; // Hard-coded encryption key

        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            String encryptedValue = Base64.encodeToString(encryptedData, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SecretKeySpec generateKey(String key) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = key.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] keyBytes = digest.digest();
        return new SecretKeySpec(keyBytes, "AES");
    }
}