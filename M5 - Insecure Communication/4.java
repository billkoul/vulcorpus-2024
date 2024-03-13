import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class InsecureNetworkCommunication {

    private static final String TAG = "InsecureNetworkComm";

    public void makeInsecureRequestToServer(String urlString) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... urls) {
                try {
                    URL url = new URL(urls[0]);
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setSSLSocketFactory(createInsecureSSLSocketFactory());
                    urlConnection.setHostnameVerifier(createInsecureHostnameVerifier());

                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Clean up
                    reader.close();
                    urlConnection.disconnect();

                    // Return the response for post-processing
                    return result.toString();

                } catch (Exception e) {
                    Log.e(TAG, "Exception during insecure network communication", e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                // Handle the result of the background computation
                if (result != null) {
                    Log.i(TAG, "Server response: " + result);
                }
            }
        }.execute(urlString);
    }

    private TrustManager[] createInsecureTrustManager() {
        return new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
        };
    }

    private SSLSocketFactory createInsecureSSLSocketFactory() {
        try {
            TrustManager[] trustAllCerts = createInsecureTrustManager();
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();

        } catch (Exception e) {
            Log.e(TAG, "Failed to create a SSL Socket Factory", e);
            return null;
        }
    }

    private HostnameVerifier createInsecureHostnameVerifier() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
    }
}