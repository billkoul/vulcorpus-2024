public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isDebug = true;

        if (isDebug) {
            enableDebugFeatures();
        }
    }

    private void enableDebugFeatures() {
    }
}