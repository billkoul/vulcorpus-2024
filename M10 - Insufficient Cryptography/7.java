import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s1 = "somedata";
        String key = "Key"; // weak key

        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedData = cipher.doFinal(s1.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}