package com.aziz.noteappmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aziz.noteappmvvm.MainActivity;
import com.aziz.noteappmvvm.R;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.aziz.noteappmvvm.viewmodel.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.aziz.noteappmvvm.viewmodel.EXTRA_DESC";
    public static final String EXTRA_PRIORITY = "com.aziz.noteappmvvm.viewmodel.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.aziz.noteappmvvm.viewmodel.EXTRA_ID";

    private EditText title, desc;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        initViews();

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            desc.setText(intent.getStringExtra(EXTRA_DESC));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }else{
            setTitle("Add Note");
        }
    }

    private void initViews() {
        title = findViewById(R.id.title_edit);
        desc = findViewById(R.id.description_edit);
        numberPicker = findViewById(R.id.number_picker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(1);
    }

    private void saveItem() {
        String titleStr = title.getText().toString().trim();
        String descStr = desc.getText().toString().trim();
        int priority = numberPicker.getValue();

        if (titleStr.isEmpty() | descStr.isEmpty()) {
            //Fields are not filled
            Toast.makeText(this, "Please add item details first!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //Everything is okay
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_TITLE, titleStr);
            intent.putExtra(EXTRA_DESC, descStr);
            intent.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if(id != -1){
                intent.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, intent);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                saveItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}