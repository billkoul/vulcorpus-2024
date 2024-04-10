public class Cp extends AppCompatActivity {

    private static final int b = 1;
    private Uri c;

    private void d() {
        Intent e = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (e.resolveActivity(getPackageManager()) != null) {
            File f = null;
            try {
                f = g();
            } catch (IOException ex) {
            }
            if (f != null) {
                c = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        f);
                e.putExtra(MediaStore.EXTRA_OUTPUT, c);
                startActivityForResult(e, b);
            }
        }
    }

    private File g() throws IOException {
        String h = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String i = "JPEG_" + h + "_";
        File j = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File k = File.createTempFile(
                i,
                ".jpg",
                j
        );
        return k;
    }

    @Override
    protected void onActivityResult(int l, int m, Intent n) {
        if (l == b && m == RESULT_OK) {
            o(c);
        }
    }

    private void o(Uri p) {
        OkHttpClient q = new OkHttpClient();
        RequestBody r = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("image", "filename.jpg", RequestBody.create(MediaType.parse("image/jpeg"), new File(p.getPath())))
        .build();
        Request s = new Request.Builder()
         .url("http://yourserver.com/upload")
         .post(r)
         .build();
        q.newCall(s).enqueue(new Callback() {});
    }
}