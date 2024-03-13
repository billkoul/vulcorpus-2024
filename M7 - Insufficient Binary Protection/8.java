public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input = findViewById(R.id.input);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = input.getText().toString();
                if (userInput.equals(getString(R.string.xcode))) {
                    activateXMode();
                }
            }
        });
    }

    private void activateXMode() {
    }
}