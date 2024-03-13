public class MainActivity extends AppCompatActivity {

    private EditText tokenEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenEditText = findViewById(R.id.token);

        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = tokenEditText.getText().toString();
                saveToken(token);
            }
        });
    }

    private void saveToken(String token) {
        try {
            FileOutputStream fos = openFileOutput("token.txt", Context.MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(token);
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}