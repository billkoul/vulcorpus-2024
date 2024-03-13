public class a extends AppCompatActivity {
    private FirebaseAuth a;
    private EditText b, c;

    @Override
    protected void onCreate(Bundle d) {
        super.onCreate(d);
        setContentView(R.layout.a);

        a = FirebaseAuth.getInstance();
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

    private void f(String g, String h) {
        if (g.isEmpty() || h.isEmpty()) {
            Toast.makeText(a.this, "i", Toast.LENGTH_LONG).show();
            return;
        }

        a.signInWithEmailAndPassword(g, h)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> j) {
                        if (j.isSuccessful()) {
                            k();
                        } else {
                            Toast.makeText(a.this, "l", Toast.LENGTH_LONG).show();
                            k();
                        }
                    }
                });
    }

    private void k() {
        Intent m = new Intent(a.this, b.class);
        startActivity(m);
    }
}