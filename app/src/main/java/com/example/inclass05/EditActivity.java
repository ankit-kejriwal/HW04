package com.example.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class EditActivity extends AppCompatActivity {

    EditText editTextname;
    EditText editTextdesc;
    Spinner spinnerGenre;
    SeekBar seekBarrating;
    EditText editTextyear;
    EditText editTextimdb;
    TextView tvRatingValue;
    Button btnSaveMovie;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        spinnerGenre = findViewById(R.id.spinner);
        editTextname = findViewById(R.id.editTextName);
        editTextdesc = findViewById(R.id.editTextDesc);
        seekBarrating = findViewById(R.id.seekBar);
        editTextyear = findViewById(R.id.editTextYear);
        editTextimdb = findViewById(R.id.editTextimdb);
        tvRatingValue = findViewById(R.id.textViewRat);
        btnSaveMovie = findViewById(R.id.buttonSaveMovie);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        index = (int) getIntent().getSerializableExtra("index");
        Log.d("demo",movie.toString());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
        // setting dropdown value
        int selectionPosition= adapter.getPosition(movie.getGenre());
        Log.d("demo",selectionPosition+"");
        spinnerGenre.setSelection(selectionPosition);
        seekBarrating.setMax(5);

        seekBarrating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvRatingValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        setDisplay(movie);
        btnSaveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextname.getText().toString();
                if(name.equals("")) {
                    editTextname.setError("Please input movie name");
                }
                String desc = editTextdesc.getText().toString();
                if(desc.equals("")) {
                    editTextdesc.setError("Please input description");
                }
                String genre = (String) spinnerGenre.getSelectedItem();
                if(genre.equals("Select genre")) {
                    Toast.makeText(EditActivity.this, "Please select Genre", Toast.LENGTH_SHORT).show();
                }
                int rating = seekBarrating.getProgress() ;
                int year = 0;
                if(editTextyear.getText().toString().equals("")){
                    editTextyear.setError("Please provide year");
                } else {
                    year = Integer.parseInt(editTextyear.getText().toString());
                }
                URL imdb = null;
                if(editTextimdb.getText().toString().length() == 0 || !Patterns.WEB_URL.matcher(editTextimdb.getText()).matches()){
                    editTextimdb.setError("Please enter IMDB rating");
                }else{
                    try {
                        imdb = new URL(editTextimdb.getText().toString());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

                if(!name.equals(null) && !desc.equals(null) && !genre.equals("Select genre")
                        && imdb != null && !editTextyear.getText().toString().equals(null)){
                    Movie movie = new Movie(name, desc,genre, rating,year,imdb);
                    Intent returnIntent = getIntent();
                    returnIntent.putExtra("updatedMovie",movie);
                    returnIntent.putExtra("index",index);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }

            }
        });

    }

    private void setDisplay(Movie movie) {
        editTextname.setText(movie.getName());
        editTextdesc.setText(movie.getDesc());
        tvRatingValue.setText(movie.getRating()+"");
        editTextyear.setText(movie.getYear()+"");
        editTextimdb.setText(movie.getImdb().toString());
        seekBarrating.setProgress(movie.getRating());
    }
}
