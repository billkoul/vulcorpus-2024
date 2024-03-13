import android.util.Log;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class InsecureSSLExample {

    private static final String TAG = "InsecureSSLExample";

    public void trustAllCertificates() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(
                    java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            Log.e(TAG, "Exception occurred while setting up all-trusting trust manager", e);
        }
    }

    public void insecureHttpsConnection(String urlString) {
        trustAllCertificates(); // This line makes all SSL/TLS certificates trusted, big security risk!

        InputStream in = null;
        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier((hostname, session) -> true);

            in = urlConnection.getInputStream();
        } catch (Exception e) {
            Log.e(TAG, "Exception occurred while making insecure HTTPS connection", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    Log.e(TAG, "Exception occurred while closing InputStream", e);
                }
            }
        }
    }
}