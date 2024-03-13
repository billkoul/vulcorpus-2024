public class a extends AppCompatActivity {
    private SharedPreferences a;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.a);

        a = getSharedPreferences("a", MODE_PRIVATE);

        Button b = findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c();
            }
        });
    }

    private void c() {
        String c = ((EditText) findViewById(R.id.c)).getText().toString();
        String d = ((EditText) findViewById(R.id.d)).getText().toString();

        if (c.equals(a.getString("c", ""))) {
            SharedPreferences.Editor e = a.edit();
            e.putBoolean("e", true);
            e.apply();

            Intent f = new Intent(a.this, b.class);
            startActivity(f);
        } else {
            Toast.makeText(a.this, "f", Toast.LENGTH_SHORT).show();
        }
    }
}