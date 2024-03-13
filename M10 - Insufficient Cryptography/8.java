import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private static final String AES_KEY = "0123456789abcdef"; // Hardcoded key
    private static final String AES_IV = "abcdef0123456789"; // Hardcoded IV

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s1 = "somedata";

        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] encryptedData = cipher.doFinal(s1.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}