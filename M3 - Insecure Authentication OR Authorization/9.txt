public class a extends AppCompatActivity {
    private String a;
    private SharedPreferences b;

    @Override
    protected void onCreate(Bundle c) {
        super.onCreate(c);
        setContentView(R.layout.a);

        b = getSharedPreferences("a", MODE_PRIVATE);
        a = b.getString("a", "");

        if (a.isEmpty()) {
            d();
        } else {
            e();
        }
    }

    private void d() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                a = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";  // Simulated JWT

                SharedPreferences.Editor f = b.edit();
                f.putString("a", a);
                f.apply();

                e();
            }
        }, 2000);
    }

    private void e() {
        Intent g = new Intent(a.this, b.class);
        g.putExtra("a", a);
        startActivity(g);
    }
}