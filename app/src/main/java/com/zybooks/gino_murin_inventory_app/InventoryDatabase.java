package com.zybooks.gino_murin_inventory_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import java.util.List;

public class InventoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int VERSION = 2;

    private static InventoryDatabase inventoryDatabase;

    //Creating a singleton for db object
    public static InventoryDatabase getInstance(Context context){
        if(inventoryDatabase == null){
            inventoryDatabase = new InventoryDatabase(context);
        }
        return inventoryDatabase;
    }

    private InventoryDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class UserTable{
        private static final String TABLE = "users";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    private static final class InventoryTable{
        private static final String TABLE = "inventory";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_QUANTITY = "quantity";
        private static final String COL_DESC = "description";
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //Create User Table
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME+ " text, " +
                UserTable.COL_PASSWORD + " text)");

        //Create Inventory Table
        db.execSQL("create table " + InventoryTable.TABLE + " (" +
                InventoryTable.COL_ID + " integer primary key autoincrement, " +
                InventoryTable.COL_NAME+ " text, " +
                InventoryTable.COL_QUANTITY + " integer, " +
                InventoryTable.COL_DESC + " text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + UserTable.TABLE);
        db.execSQL("drop table if exists " + InventoryTable.TABLE);
        onCreate(db);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "select * from " + UserTable.TABLE + " order by "
                + UserTable.COL_ID;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTable.COL_USERNAME, user.getUsername());
        values.put(UserTable.COL_PASSWORD, user.getPassword());
        long userId = db.insert(UserTable.TABLE, null, values);
        user.setId(userId);
    }

    public void addInventory(Inventory item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryTable.COL_NAME, item.getName());
        values.put(InventoryTable.COL_QUANTITY, item.getQuantity());
        values.put(InventoryTable.COL_DESC, item.getDescription());
        long itemId = db.insert(InventoryTable.TABLE, null, values);
        item.setId(itemId);
    }

    public List<Inventory> getInventory(){
        List<Inventory> inventory = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + InventoryTable.TABLE + " order by "
                + InventoryTable.COL_ID;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Inventory item = new Inventory();
                item.setId(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setQuantity(cursor.getInt(2));
                item.setDescription(cursor.getString(3));
                inventory.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return inventory;
    }

    public void deleteInventory(long id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(InventoryTable.TABLE,
                InventoryTable.COL_ID +" = "+ id, null);
    }

    public void updateInventory(Inventory item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(InventoryTable.COL_ID, item.getId());
        values.put(InventoryTable.COL_NAME, item.getName());
        values.put(InventoryTable.COL_QUANTITY, item.getQuantity());
        values.put(InventoryTable.COL_DESC, item.getDescription());
        db.update(InventoryTable.TABLE, values,
                InventoryTable.COL_ID + " = "
                        + item.getId(), null);
    }
}
