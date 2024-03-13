import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String data = "SensitiveData";
        String key = "xkhlcx18yc1"; 
        try {
            byte[] dataBytes = data.getBytes("UTF-8");
            Key secretKey = new SecretKeySpec(key.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(dataBytes);
            // Here, the data is encrypted using DES, which is insufficiently secure
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}