public class MainActivity extends AppCompatActivity {
    private EditText searchInput;
    private TextView resultsDisplay;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.searchInput);
        resultsDisplay = findViewById(R.id.resultsDisplay);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void searchBooks(View view) {
        String query = searchInput.getText().toString();

        String url = "http://openlibrary.org/search.json?title=" + query;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, 
            response -> {
                try {
                    JSONArray docs = response.getJSONArray("docs");
                    for (int i = 0; i < docs.length(); i++) {
                        JSONObject doc = docs.getJSONObject(i);
                        String title = doc.getString("title");
                        resultsDisplay.append(title + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            error -> error.printStackTrace()
        );

        requestQueue.add(request);
    }
}