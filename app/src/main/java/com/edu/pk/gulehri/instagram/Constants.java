package com.edu.pk.gulehri.instagram;

public class Constants {

    public static final String DB_TABLE = "User";

    public static final String UID = "_id";
    public static final String EMAIL = "Email";
    public static final String FULL_NAME = "FullName";
    public static final String USER_NAME = "UserName";
    public static final String PASSWORD = "Password";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + DB_TABLE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT, "
                    + FULL_NAME + " TEXT, " + USER_NAME + " TEXT, " + PASSWORD + " TEXT );";

    public static final String [] All_COLUMNS = {USER_NAME,PASSWORD};

    public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + DB_TABLE;

}
