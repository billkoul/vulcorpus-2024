public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase mydatabase = openOrCreateDatabase("CreditCardInfo",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS CreditCardDetails(CardNumber VARCHAR, ExpiryDate VARCHAR, CVV VARCHAR, CardHolderName VARCHAR);");
        mydatabase.execSQL("INSERT INTO CreditCardDetails VALUES('1234-5678-9012-3456','12/24','123','John Doe');");
    }
}