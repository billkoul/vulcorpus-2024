public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileName = "user_data";
        String fileContents = "Username: user, Password: pass123";
        
        Context context = getApplicationContext();
        FileOutputStream fos;
        
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileContents.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", "user");
        editor.putString("password", "pass123");
        editor.apply();

        SQLiteDatabase db = context.openOrCreateDatabase("MyDatabase", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Users(Username VARCHAR,Password VARCHAR);");
        db.execSQL("INSERT INTO Users VALUES('user','pass123');");
    }
}