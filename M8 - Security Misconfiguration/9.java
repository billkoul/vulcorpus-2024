public class MainActivity extends AppCompatActivity {
  
    private static final String URL = "http://example.com/api/data";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(URL).openConnection();

                HttpURLConnection.setFollowRedirects(true);
                
                String auth = USERNAME + ":" + PASSWORD;
                String encodedAuth = Base64.encodeToString(auth.getBytes(), Base64.DEFAULT);

                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

                Log.d("DEBUG", "Authorization Header: " + "Basic " + encodedAuth);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write("data to be sent");

                writer.flush();
                writer.close();
                os.close();

                connection.connect();
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}