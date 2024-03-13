public void onClick(View v) {
    EditText userAge = (EditText) findViewById(R.id.ageEditText);
    String age = userAge.getText().toString();
    int userAge = Integer.parseInt(age);
    if (userAge < 13) {
        Toast.makeText(this, "Sorry, you are too young to use this app!", Toast.LENGTH_LONG).show();
    }
    else {
        // Process with the registration or access
    }
}