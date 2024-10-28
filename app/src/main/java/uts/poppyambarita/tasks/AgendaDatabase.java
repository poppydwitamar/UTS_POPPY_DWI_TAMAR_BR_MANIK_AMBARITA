package uts.poppyambarita.tasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AgendaDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_AGENDA = "agenda";

    private static AgendaDatabase instance;

    public static synchronized AgendaDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new AgendaDatabase(context.getApplicationContext());
        }
        return instance;
    }

    public AgendaDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_AGENDA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, status TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENDA);
        onCreate(db);
    }

    // Method untuk menambahkan agenda
    public void addAgenda(String name, String description, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("status", status);
        db.insert(TABLE_AGENDA, null, values);
        db.close();
    }

    // Method untuk mendapatkan semua agenda
    public ArrayList<Agenda> getAllAgendas() {
        ArrayList<Agenda> agendas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_AGENDA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String status = cursor.getString(3);
                agendas.add(new Agenda(id, name, description, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return agendas;
    }
}
