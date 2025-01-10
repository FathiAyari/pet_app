package com.example.myapppeet;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        // Création de la table "users" pour stocker les informations des utilisateurs
        String qry1 = "create table users(nom text, email text, motdepasse text)";
        sqliteDatabase.execSQL(qry1);

        // Création de la table "animals" pour stocker les informations des animaux
        String qry2 = "create table animals(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT, age TEXT)";
        sqliteDatabase.execSQL(qry2);

        String qry3 = "create table events(id INTEGER PRIMARY KEY AUTOINCREMENT, label TEXT)";
        sqliteDatabase.execSQL(qry3);

        String qry4 = "CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, subject TEXT, note TEXT)";
        sqliteDatabase.execSQL(qry4);

        String qry5 = "CREATE TABLE reminders(id INTEGER PRIMARY KEY AUTOINCREMENT, subject TEXT, date TEXT)";
        sqliteDatabase.execSQL(qry5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Suppression des tables existantes et création de nouvelles
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS animals");
        db.execSQL("DROP TABLE IF EXISTS events");
        db.execSQL("DROP TABLE IF EXISTS notes");
        db.execSQL("DROP TABLE IF EXISTS reminders");

        onCreate(db);
    }

    // Méthode pour enregistrer un nouvel utilisateur
    public void register(String nom, String email, String motdepasse) {
        ContentValues cv = new ContentValues();
        cv.put("nom", nom);
        cv.put("email", email);
        cv.put("motdepasse", motdepasse);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    // Méthode pour vérifier les informations de connexion
    public int login(String nom, String motdepasse) {
        int result = 0;
        String[] str = new String[2];
        str[0] = nom;
        str[1] = motdepasse;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where nom=? and motdepasse=?", str);
        if (c.moveToFirst()) {
            result = 1; // Si un utilisateur correspond, retourne 1
        }
        return result;
    }

    // Méthode pour insérer un animal dans la base de données
    public long insertAnimal(String name, String type, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("type", type);
        values.put("age", age);
        return db.insert("animals", null, values);
    }
    public long insertEvent(String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("label", label);
        return db.insert("events", null, values);
    }

    public long insertNote(String subject, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("subject", subject);
        values.put("note", note);

        long result = db.insert("notes", null, values);
        db.close();
        return result;
    }
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Note note = new Note(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("subject")),
                        cursor.getString(cursor.getColumnIndex("note"))
                );
                notes.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return notes;
    }



    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM animals", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Animal animal = new Animal(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("type")),
                        cursor.getInt(cursor.getColumnIndex("age"))
                );
                animals.add(animal);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return animals;
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM events", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Event event = new Event(
                        cursor.getInt(cursor.getColumnIndex("id")),    // Get event ID
                        cursor.getString(cursor.getColumnIndex("label"))// Get event label
                );
                events.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return events;
    }


    // Delete animal by ID
    public void deleteAnimal(int animalId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("animals", "id = ?", new String[]{String.valueOf(animalId)});
        db.close();
    }
    public void deleteEvent(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("events", "id = ?", new String[]{String.valueOf(eventId)});
        db.close();
    }

    public void updateAnimal(int id, String name, String type, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("type", type);
        values.put("age", age);

        db.update("animals", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateEvent(int id, String label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("label", label);

        db.update("events", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void updateNote(int id, String subject, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("subject", subject);
        values.put("note", note);

        db.update("notes", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteNote(int noteId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notes", "id = ?", new String[]{String.valueOf(noteId)});
        db.close();
    }


    // Insert Reminder
    public long insertReminder(String subject, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("subject", subject);
        values.put("date", date);
        return db.insert("reminders", null, values);
    }

    // Update Reminder
    public void updateReminder(int id, String subject, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("subject", subject);
        values.put("date", date);
        db.update("reminders", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Delete Reminder
    public void deleteReminder(int reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("reminders", "id = ?", new String[]{String.valueOf(reminderId)});
        db.close();
    }

    // Get All Reminders
    public List<Reminder> getAllReminders() {
        List<Reminder> reminderList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM reminders", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String subject = cursor.getString(cursor.getColumnIndex("subject"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));

                Reminder reminder = new Reminder(id, subject, date);
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reminderList;
    }

}