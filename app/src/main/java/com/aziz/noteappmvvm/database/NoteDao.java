package com.aziz.noteappmvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface NoteDao {

    @Insert
    Single<Long> insert(Note note);

    @Update
    Completable update(Note note);

    @Delete
    Completable delete(Note note);

    @Query("DELETE FROM note_table")
    Single<Integer> deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

}
