public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String sensitiveData = "Username: user24, Password: afs98fnc1!R%";
        File cacheDir = getCacheDir();
        File cacheFile = new File(cacheDir, "sensitiveData.txt");

        try {
            FileWriter writer = new FileWriter(cacheFile);
            writer.write(sensitiveData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}