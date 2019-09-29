package com.example.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieBy extends AppCompatActivity {

    TextView textViewHeading;
    TextView textViewTitle;
    EditText editTextDesc;
    TextView textViewGenre;
    TextView textViewRating;
    TextView textViewYear;
    TextView textViewimdb;
    Button buttonFinish;
    ImageView imageViewNext;
    ImageView imageViewPrev;
    ImageView imageViewlast;
    ImageView imageViewFirst;
    ArrayList<Movie> movies;
    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_by);

        textViewHeading = findViewById(R.id.textViewHeading);
        textViewTitle = findViewById(R.id.textViewName);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextDesc.setEnabled(false);
        textViewGenre = findViewById(R.id.textViewGenre);
        textViewRating = findViewById(R.id.textViewRating);
        textViewYear = findViewById(R.id.textViewYear);
        textViewimdb = findViewById(R.id.textViewimdb);
        imageViewFirst = findViewById(R.id.imageViewfirst);
        imageViewlast = findViewById(R.id.imageViewLast);
        imageViewPrev = findViewById(R.id.imageViewprevious);
        imageViewNext = findViewById(R.id.imageViewnext);

        buttonFinish = findViewById(R.id.buttonfinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageViewFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = 0;
                displayItem(currentIndex);
            }
        });

        imageViewlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = movies.size() - 1;
                displayItem(currentIndex);
            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex++;
                if(currentIndex > (movies.size() -1)) {
                    currentIndex = 0;
                    displayItem(currentIndex);
                } else {
                    displayItem(currentIndex);
                }
            }
        });

        imageViewPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex--;
                if(currentIndex<0){
                    currentIndex = movies.size() - 1;
                    displayItem(currentIndex);
                } else  {
                    displayItem(currentIndex);
                }
            }
        });


        Intent intent = getIntent();
        String heading = (String) getIntent().getSerializableExtra("heading");
        movies = (ArrayList<Movie>) getIntent().getSerializableExtra("movies");
        Log.d("demo",movies.get(currentIndex).toString());
        textViewHeading.setText("Movies by "+ heading);
        displayItem(currentIndex);
    }

    private void displayItem(int currentIndex){
        textViewTitle.setText(movies.get(currentIndex).getName());
        editTextDesc.setText(movies.get(currentIndex).getDesc());
        textViewGenre.setText(movies.get(currentIndex).getGenre());
        textViewRating.setText(movies.get(currentIndex).getRating()+"");
        textViewYear.setText(movies.get(currentIndex).getYear()+"");
        textViewimdb.setText(movies.get(currentIndex).getImdb()+"");
    }
}
