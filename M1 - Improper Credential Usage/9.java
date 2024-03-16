import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.library.NetworkLibrary;

public class MainActivity extends AppCompatActivity {
    private EditText user, pwrd;
    private NetworkLibrary networkLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.user);
        pwrd = (EditText) findViewById(R.id.pwrd);
        networkLibrary = new NetworkLibrary();
    }

    public void login(View view) {
        String credentials = user.getText().toString() + ":" + pwrd.getText().toString();
        String response = networkLibrary.sendCredentials(credentials);

        Log.d("Login Response", response);
    }
}