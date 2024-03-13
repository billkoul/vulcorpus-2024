public class A extends AppCompatActivity {
    private EditText a;
    private TextView b;
    private RequestQueue c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = findViewById(R.id.searchInput);
        b = findViewById(R.id.resultsDisplay);

        c = Volley.newRequestQueue(this);
    }

    public void sB(View view) {
        String q = a.getText().toString();

        String u = "http://openlibrary.org/search.json?title=" + q;

        JsonObjectRequest r = new JsonObjectRequest(Request.Method.GET, u, null, 
            res -> {
                try {
                    JSONArray d = res.getJSONArray("docs");
                    for (int i = 0; i < d.length(); i++) {
                        JSONObject doc = d.getJSONObject(i);
                        String t = doc.getString("title");
                        b.append(t + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            Throwable::printStackTrace
        );

        c.add(r);
    }
}