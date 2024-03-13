import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText cardNumberEditText, cardExpiryEditText, cardCVVEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumberEditText = findViewById(R.id.cardNumber);
        cardExpiryEditText = findViewById(R.id.cardExpiry);
        cardCVVEditText = findViewById(R.id.cardCVV);

        Button saveCardButton = findViewById(R.id.saveCard);
        saveCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardNumber = cardNumberEditText.getText().toString();
                String cardExpiry = cardExpiryEditText.getText().toString();
                String cardCVV = cardCVVEditText.getText().toString();
                saveCardInfo(cardNumber, cardExpiry, cardCVV);
            }
        });
    }

    private void saveCardInfo(String cardNumber, String cardExpiry, String cardCVV) {
        SQLiteOpenHelper helper = new SQLiteOpenHelper(this, "Cards.db", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE Cards(CardNumber TEXT, CardExpiry TEXT, CardCVV TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS Cards");
                onCreate(db);
            }
        };

        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO Cards VALUES (?, ?, ?)", new String[]{cardNumber, cardExpiry, cardCVV});
        db.close();
    }
}