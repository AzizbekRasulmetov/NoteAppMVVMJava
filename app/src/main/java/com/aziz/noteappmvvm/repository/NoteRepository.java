package com.aziz.noteappmvvm.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.aziz.noteappmvvm.database.Note;
import com.aziz.noteappmvvm.database.NoteDao;
import com.aziz.noteappmvvm.database.NoteDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }


    public void insert(Note note) {
        noteDao.insert(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Long aLong) {
                        Log.d("ROOMRXJAVA", "onSuccess: called for insert row: " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void update(Note note) {
        noteDao.update(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("ROOMRXJAVA", "onComplete: called for update");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ROOMRXJAVA", "onError: called for update");
                    }
                });
    }
    public void delete(Note note) {
        noteDao.delete(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("ROOMRXJAVA", "onComplete: called for delete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void deleteAllNotes() {
        noteDao.deleteAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        Log.d("ROOMRXJAVA", "onSuccess: called for deleteAll " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }




}
