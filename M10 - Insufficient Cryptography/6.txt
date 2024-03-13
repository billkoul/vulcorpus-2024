public class MainActivity extends AppCompatActivity {

    private static final int SHIFT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s1 = "somedata";
        String encryptedData = caesarEncrypt(s1, SHIFT);
    }

    private String caesarEncrypt(String plaintext, int shift) {
        StringBuilder cipherText = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                cipherText.append((char) ((ch - base + shift) % 26 + base));
            } else {
                cipherText.append(ch);
            }
        }

        return cipherText.toString();
    }
}