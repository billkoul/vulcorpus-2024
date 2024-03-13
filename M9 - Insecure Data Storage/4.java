public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "user_data.txt";
    private static final String USER_DATA = "Username: user, Password: pass123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isExternalStorageWritable()) {
            File file = new File(getExternalFilesDir(null), FILE_NAME);
            FileOutputStream outputStream;

            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(USER_DATA.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}