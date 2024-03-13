public class MainActivity extends AppCompatActivity {

    private boolean premiumFeaturesUnlocked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button purchaseButton = findViewById(R.id.purchase_button);
        purchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePurchase();
            }
        });

        Button useFeatureButton = findViewById(R.id.use_feature_button);
        useFeatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (premiumFeaturesUnlocked) {
                    usePremiumFeature();
                } else {
                    Toast.makeText(MainActivity.this, "Please purchase to unlock this feature", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validatePurchase() {
        premiumFeaturesUnlocked = true;
        Toast.makeText(this, "Purchase successful - premium features unlocked", Toast.LENGTH_SHORT).show();
    }

    private void usePremiumFeature() {
        Toast.makeText(this, "Using premium feature", Toast.LENGTH_SHORT).show();
    }
}