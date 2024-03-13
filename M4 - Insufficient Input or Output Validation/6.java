public class ForumActivity extends AppCompatActivity {

    private EditText c1;
    private DatabaseReference cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        c1 = findViewById(R.id.c1);

        cd = FirebaseDatabase.getInstance().getReference("comments");

        cd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    fc c = snapshot.getValue(fc.class);
                    addCommentToUI(c);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void postComment(View view) {
        String ct = c1.getText().toString();
        fc c = new fc(ct, "user");
        cd.push().setValue(c);
        c1.setText("");
    }

    private void addCommentToUI(fc c) {
        TextView cv = new TextView(this);
        cv.setText(Html.fromHtml("<b>" + c.getUsername() + ":</b> " + c.getComment()));
        ((LinearLayout) findViewById(R.id.commentsContainer)).addView(cv);
    }
}