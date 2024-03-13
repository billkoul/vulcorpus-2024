public class CustomEncryption {
    private static final String password = "akjbc981xkb123";

    public static String encrypt(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ password.charAt(i % password.length())));
        }

        return output.toString();
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sensitiveData = "SensitiveData";
        String encryptedData = CustomEncryption.encrypt(sensitiveData);
    }
}
