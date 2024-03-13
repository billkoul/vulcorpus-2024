public class MainActivity extends AppCompatActivity {

    private EditText postTitleInput, postBodyInput;
    private PostService postService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postTitleInput = findViewById(R.id.postTitleInput);
        postBodyInput = findViewById(R.id.postBodyInput);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        postService = retrofit.create(PostService.class);

        fetchPosts();
    }

    public void createPost(View view) {
        Post post = new Post(postTitleInput.getText().toString(), postBodyInput.getText().toString());
        Call<Post> call = postService.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Post creation failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                fetchPosts();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Post creation failed", Toast.LENGTH_SHORT).show();
            }
        });
        postTitleInput.setText("");
        postBodyInput.setText("");
    }

    private void fetchPosts() {
        Call<List<Post>> call = postService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Failed to fetch posts", Toast.LENGTH_SHORT).show();
                    return;
                }
                displayPosts(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch posts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayPosts(List<Post> posts) {
        for (Post post : posts) {
            TextView postView = new TextView(this);
            postView.setText(Html.fromHtml("<b>" + post.getTitle() + ":</b><br>" + post.getBody()));
            ((LinearLayout) findViewById(R.id.postsContainer)).addView(postView);
        }
    }
}