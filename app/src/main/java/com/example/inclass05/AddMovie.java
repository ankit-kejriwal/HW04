package com.example.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class AddMovie extends AppCompatActivity {

    EditText editTextname;
    EditText editTextdesc;
    Spinner spinnerGenre;
    SeekBar seekBarrating;
    EditText editTextyear;
    EditText editTextimdb;
    TextView tvRatingValue;
    Button btnaddMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        spinnerGenre = findViewById(R.id.spinner);
        editTextname = findViewById(R.id.editTextName);
        editTextdesc = findViewById(R.id.editTextDesc);
        seekBarrating = findViewById(R.id.seekBar);
        editTextyear = findViewById(R.id.editTextYear);
        editTextimdb = findViewById(R.id.editTextimdb);
        tvRatingValue = findViewById(R.id.textViewRat);
        btnaddMovie = findViewById(R.id.buttonSaveMovie);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
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

        btnaddMovie.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AddMovie.this, "Please select Genre", Toast.LENGTH_SHORT).show();
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
                    returnIntent.putExtra("newMovie",movie);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }

            }
        });


    }

}
