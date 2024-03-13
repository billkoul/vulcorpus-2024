public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.FLAVOR.equals("special")) {
            enableHiddenFeature();
        }
    }

    private void enableHiddenFeature() {
    }
}