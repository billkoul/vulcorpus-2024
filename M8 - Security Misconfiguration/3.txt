public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setAllowFileAccess(true);

        webSettings.setAllowFileAccessFromFileURLs(true);

        webSettings.setAllowUniversalAccessFromFileURLs(true);

        myWebView.loadUrl("http://www.example.com");
    }
}