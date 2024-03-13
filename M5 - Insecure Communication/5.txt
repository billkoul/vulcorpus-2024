import android.os.AsyncTask;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class InsecureWebSocketExample extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "InsecureWebSocket";
    private static final String WS_URL = "ws://example.com/websocket"; // Insecure: Use "wss://" for secure WebSocket connections

    private WebSocketClient webSocketClient;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Constructs the WebSocketClient instance
            webSocketClient = new WebSocketClient(new URI(WS_URL)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.i(TAG, "WebSocket Opened");
                    send("Hello, World!");
                }

                @Override
                public void onMessage(String message) {
                    Log.i(TAG, "Received message: " + message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.i(TAG, "WebSocket Closed. Code: " + code + ", Reason: " + reason);
                }

                @Override
                public void onError(Exception ex) {
                    Log.e(TAG, "WebSocket Error", ex);
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }}, null);

            webSocketClient.setSocket(sslContext.getSocketFactory().createSocket());
            webSocketClient.connect();

        } catch (Exception e) {
            Log.e(TAG, "WebSocket connection failed", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // Handle WebSocket connection post-execution, e.g., clean-up resources
    }
}