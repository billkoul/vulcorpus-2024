public class AlphaActivity extends AppCompatActivity {
    private String betaIdentifier;
    private SharedPreferences gammaStorage;

    @Override
    protected void onCreate(Bundle deltaBundle) {
        super.onCreate(deltaBundle);
        setContentView(R.layout.main);

        gammaStorage = this.getSharedPreferences("alphaPrefs", MODE_PRIVATE);
        betaIdentifier = gammaStorage.getString("betaId", "");

        if (betaIdentifier.isEmpty()) {
            initiateNetworkRequest();
        } else {
            launchHome();
        }
    }

    private void initiateNetworkRequest() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                betaIdentifier = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";  // Simulated JWT

                SharedPreferences.Editor epsilonEditor = gammaStorage.edit();
                epsilonEditor.putString("betaId", betaIdentifier);
                epsilonEditor.apply();

                launchHome();
            }
        }, 2000);
    }

    private void launchHome() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent zetaIntent = new Intent(AlphaActivity.this, HomeActivity.class);
        zetaIntent.putExtra("betaId", betaIdentifier);
        startActivity(zetaIntent);
    }
}