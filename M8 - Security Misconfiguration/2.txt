public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", "asdjkhasd978123§!DDy");
        editor.putString("password", "cnq0823cNkhj123dcx231!@#$5f");
        editor.apply();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update("password".getBytes());
            byte[] digest = md5.digest();
            String myHash = new BigInteger(1, digest).toString(16);
            Log.d("MD5 hash of password: ", myHash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if ((getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            Log.d("Security Misconfiguration", "Application is debuggable!");
        }
    }
}s