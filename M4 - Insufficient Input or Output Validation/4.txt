public List<String> getUserDetails(String username) {
    List<String> userDetails = new ArrayList<>();

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username = " + username, null);

    if(cursor.moveToFirst()){
        do{
            userDetails.add(cursor.getString(0)); // ID
            userDetails.add(cursor.getString(1)); // Username
            userDetails.add(cursor.getString(2)); // Email
        } while(cursor.moveToNext());
    }
    cursor.close();
    db.close();
    return userDetails;
}