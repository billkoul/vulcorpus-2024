import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import okhttp3.*;
import java.io.File;
import java.io.IOException;

public class Fp extends AppCompatActivity {

    private static final int RRC = 42;
    private OkHttpClient hc = new OkHttpClient();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        pFS();
    }

    public void pFS() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*"); // Use the desired MIME type

        startActivityForResult(i, RRC);
    }

    @Override
    public void onActivityResult(int rc, int rsc, @Nullable Intent d) {
        super.onActivityResult(rc, rsc, d);

        if (rc == RRC && rsc == AppCompatActivity.RESULT_OK) {
            if (d != null) {
                Uri u = d.getData();
                uF(u);
            }
        }
    }

    private void uF(Uri fu) {
        ContentResolver contentResolver = getContentResolver();
        String fileName = getFileName(fu);

        // Use try-with-resources to automatically close the InputStream
        try (InputStream inputStream = contentResolver.openInputStream(fu)) {
            // Create a RequestBody from the InputStream
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", fileName,
                            RequestBody.create(MediaType.parse("application/octet-stream"), inputStreamToBytes(inputStream)))
                    .build();

            // Create the HTTP request
            Request request = new Request.Builder()
                    .url("https://yourserver.com/upload")
                    .post(requestBody)
                    .build();

            // Asynchronously send the HTTP request
            hc.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call c, IOException e) {
                    // Handle the error
                }

                @Override
                public void onResponse(Call c, Response r) throws IOException {
                    if (r.isSuccessful()) {
                        // Handle the response
                    }
                }
            });
        } catch (IOException e) {
            // Handle the IOException
        }
    }

    // Helper method to convert InputStream to byte array
    private byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    // Helper method to get the file name from a Uri
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}