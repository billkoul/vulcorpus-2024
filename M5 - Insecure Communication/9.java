import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class InsecureFileDownloader {

    private static final String TAG = "InsecureFileDownloader";

    public void downloadFile(String fileUrl, String filePath) {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                String downloadUrl = params[0];
                String outputFile = params[1];

                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                HttpsURLConnection urlConnection = null;

                try {
                    URL url = new URL(downloadUrl);
                    trustAllHosts(); 
                    urlConnection = (HttpsURLConnection) url.openConnection();
                    urlConnection.setHostnameVerifier((hostname, session) -> true);  // Ignore host name verification

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    fileOutputStream = new FileOutputStream(outputFile);

                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.flush();
                    return true;
                } catch (Exception e) {
                    Log.e(TAG, "Error downloading file", e);
                    return false;
                } finally {
                    try {
                        if (inputStream != null) inputStream.close();
                        if (fileOutputStream != null) fileOutputStream.close();
                        if (urlConnection != null) urlConnection.disconnect();
                    } catch (Exception e) {
                        Log.e(TAG, "Error closing streams", e);
                    }
                }
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    Log.i(TAG, "File downloaded successfully");
                } else {
                    Log.e(TAG, "File download failed");
                }
            }
        }.execute(fileUrl, filePath);
    }

    private void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
            }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            Log.e(TAG, "Failed to install all-trusting trust manager", e);
        }
    }
}