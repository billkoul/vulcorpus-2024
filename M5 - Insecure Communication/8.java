import android.os.AsyncTask;
import android.util.Log;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsecureTokenTransmission {

    private static final String TAG = "InsecureTokenTransmission";

    public void sendApiToken(String apiToken, String endpoint) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                String token = params[0];
                String apiEndpoint = params[1];

                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL("http://" + apiEndpoint + "/token");

                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    urlConnection.setDoOutput(true);

                    String body = "token=" + token;

                    OutputStream os = urlConnection.getOutputStream();
                    os.write(body.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode != HttpURLConnection.HTTP_OK) {
                        Log.e(TAG, "Failed to send API token. Response Code: " + responseCode);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Exception occurred while sending API token", e);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
                return null;
            }
        }.execute(apiToken, endpoint);
    }
}