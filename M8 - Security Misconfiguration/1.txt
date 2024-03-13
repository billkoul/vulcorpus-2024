@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initializing the networking components
    final RequestQueue requestQueue = Volley.newRequestQueue(this);
    final String url = "https://awesome-social-app.com/login";
    
    // Creating a new JSON request to the server
    final JSONObject params = new JSONObject();
    params.put("email", "user@example.com");
    params.put("password", "pxa81#D!");
    
    final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
        Request.Method.POST, url, params, 
        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Handling the response from the server
            }
        }, 
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handling the error from the server
            }
        }
    );
    
    // Adding the request to the queue
    requestQueue.add(jsonObjectRequest);
}