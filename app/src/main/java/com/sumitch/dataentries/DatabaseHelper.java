package com.sumitch.dataentries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String db_name="details.db";
    public static final String tbl_name="lotdetails";
    public static final String col1="Sr_No";
    public static final String col2="Date";
    public static final String col3="Lot_No";
    public static final String col4="Books";
    public static final String col5="Amount";
    public static final String col6="Commission";
    public static final String col7="TDS";
    public static final String col8="C_TDS";

    public DatabaseHelper(Context context) {
        super(context, db_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tbl_name+" ("+col1+" integer primary key autoincrement, "+col2+" date, "+col3+" integer, "+col4+" integer, "+col5+" integer, "+col6+" integer, "+col7+" integer, "+col8+" integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tbl_name);
        onCreate(db);
    }

    public boolean insertData(String date, String lot_no, String books, String amount, String comm, String tds, String ctds){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, date);
        contentValues.put(col3, lot_no);
        contentValues.put(col4, books);
        contentValues.put(col5, amount);
        contentValues.put(col6, comm);
        contentValues.put(col7, tds);
        contentValues.put(col8, ctds);
        long result = db.insert(tbl_name, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


}
