import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText pEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pEt = findViewById(R.id.p);

        Button lB = findViewById(R.id.l);
        lB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = pEt.getText().toString();
                if (p.equals("x0asdv")) { 
                } else {
                }
            }
        });
    }
}