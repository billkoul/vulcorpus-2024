public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "sensitive_data.txt";
    private static final String SENSITIVE_DATA = "API_KEY: 1234567890abcdef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isExternalStorageWritable()) {
            File dir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(dir, FILE_NAME);

            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(SENSITIVE_DATA.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}