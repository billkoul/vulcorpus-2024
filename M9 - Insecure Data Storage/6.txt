public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "userdata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String userData = "Username: user, Password: pass123";
        FileOutputStream fos;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(userData.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}