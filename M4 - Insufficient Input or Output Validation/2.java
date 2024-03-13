public void onClick(View v) {
    EditText editText = (EditText) findViewById(R.id.editText);
    String input = editText.getText().toString();
    try {
        URL url = new URL(input);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        String response = stringBuilder.toString();
    } catch (Exception e) {
        e.printStackTrace();
    }
}