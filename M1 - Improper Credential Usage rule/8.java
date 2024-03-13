import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
    }

    public void saveCredentials(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String encodedUsername = Base64.encodeToString(userName.getText().toString().getBytes(), Base64.DEFAULT);
        String encodedPassword = Base64.encodeToString(password.getText().toString().getBytes(), Base64.DEFAULT);

        editor.putString("USERNAME", encodedUsername);
        editor.putString("PASSWORD", encodedPassword);

        editor.apply();
    }
}