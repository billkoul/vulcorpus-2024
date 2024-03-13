import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.library.NetworkLibrary;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private NetworkLibrary networkLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        networkLibrary = new NetworkLibrary();
    }

    public void login(View view) {
        String credentials = userName.getText().toString() + ":" + password.getText().toString();
        String response = networkLibrary.sendCredentials(credentials);

        Log.d("Login Response", response);
    }
}