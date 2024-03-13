public class ChatActivity extends AppCompatActivity {

    private EditText messageInput;
    private DatabaseReference messagesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = findViewById(R.id.messageInput);

        messagesDatabase = FirebaseDatabase.getInstance().getReference("messages");

        messagesDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatMessage message = snapshot.getValue(ChatMessage.class);
                    // display the message directly without any sanitization
                    addMessageToUI(message);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void sendMessage(View view) {
        String messageText = messageInput.getText().toString();
        ChatMessage message = new ChatMessage(messageText, "user");
        messagesDatabase.push().setValue(message);
        messageInput.setText("");
    }

    private void addMessageToUI(ChatMessage message) {
        TextView messageView = new TextView(this);
        messageView.setText(Html.fromHtml("<b>" + message.getUsername() + ":</b> " + message.getMessage()));
        ((LinearLayout) findViewById(R.id.messagesContainer)).addView(messageView);
    }
}