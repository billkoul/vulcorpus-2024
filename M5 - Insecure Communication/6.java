import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsecureNetworkCommunication {

    public void sendSensitiveData(String sensitiveData) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://example.com/api/sendData");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String postData = "sensitiveData=" + sensitiveData;
            connection.getOutputStream().write(postData.getBytes());
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Handle the response appropriately
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Process the response line by line
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}