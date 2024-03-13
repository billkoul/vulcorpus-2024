public class a extends AppCompatActivity {
    private EditText b, c;

    @Override
    protected void onCreate(Bundle d) {
        super.onCreate(d);
        setContentView(R.layout.a);

        b = findViewById(R.id.b);
        c = findViewById(R.id.c);

        Button e = findViewById(R.id.e);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f(b.getText().toString(), c.getText().toString());
            }
        });
    }

    public void f(String g, String h) {
        if (g.equals("i") && h.equals("j")) {
            Intent k = new Intent(a.this, l.class);
            startActivity(k);
        } else {
            Intent m = new Intent(a.this, n.class);
            startActivity(m);
        }
    }
}