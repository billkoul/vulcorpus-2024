import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        database = openOrCreateDatabase("AppDatabase", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Users(Username VARCHAR, Password VARCHAR);");
    }

    public void registerUser(View view) {
        String encryptedUsername = customEncrypt(userName.getText().toString());
        String encryptedPassword = customEncrypt(password.getText().toString());

        ContentValues values = new ContentValues();
        values.put("Username", encryptedUsername);
        values.put("Password", encryptedPassword);
        database.insert("Users", null, values);
    }

    private String customEncrypt(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}