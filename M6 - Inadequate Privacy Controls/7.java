import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class InsecureContentProvider extends ContentProvider {

    private static final String[] COLUMNS = {"username", "password"};
    private static final MatrixCursor DATA;

    static {
        DATA = new MatrixCursor(COLUMNS);
        DATA.addRow(new Object[]{"user1", "password1"});
        DATA.addRow(new Object[]{"user2", "password2"});
        DATA.addRow(new Object[]{"user3", "password3"});
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return DATA;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}