package com.example.inclass05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE = 111;
    public static final int REQ_CODE_BY = 222;
    public static final int REQ_CODE_EDIT = 333;
    Button add;
    Button edit;
    Button delete;
    Button year;
    Button rating;
    ArrayList<Movie> movies = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");

        add = findViewById(R.id.buttonadd);
        edit = findViewById(R.id.buttonEdit);
        delete = findViewById(R.id.buttonDelete);
        year = findViewById(R.id.buttonYear);
        rating = findViewById(R.id.buttonRating);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovie.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<String> movieList = getMovieName();
                if(movieList != null){
                    final String[] movieNameArray = movieList.toArray(new String[0]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");
                    builder.setItems(movieNameArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String title = movieNameArray[which];
                            Intent intent = new Intent(MainActivity.this,EditActivity.class);
                            intent.putExtra("movie",movies.get(which));
                            intent.putExtra("index",which);
                            startActivityForResult(intent,REQ_CODE_EDIT);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> movieList = getMovieName();
                if(movieList != null){
                    final String[] movieNameArray = movieList.toArray(new String[0]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pick a Movie");
                    builder.setItems(movieNameArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String title = movieNameArray[which];
                            movies.remove(which);
                            Toast.makeText(MainActivity.this, title + " has been deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieBy.class);
                ArrayList<Movie> tempList = movies;
                if(tempList.size() > 0) {
                    Collections.sort(tempList, new Comparator<Movie>() {
                        @Override
                        public int compare(Movie movie, Movie t1) {
                            return movie.getYear().compareTo(t1.getYear());
                        }
                    });
                    intent.putExtra("movies",tempList);
                    intent.putExtra("heading","Year");
                    startActivityForResult(intent,REQ_CODE_BY);
                } else  {
                    Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MovieBy.class);
                ArrayList<Movie> tempList = movies;
                if(tempList.size() > 0) {
                    Collections.sort(tempList, new Comparator<Movie>() {
                        @Override
                        public int compare(Movie movie, Movie t1) {
                            return movie.getRating().compareTo(t1.getRating());
                        }
                    });
                    intent.putExtra("movies",tempList);
                    intent.putExtra("heading","Rating");
                    startActivityForResult(intent,REQ_CODE_BY);
                } else  {
                    Toast.makeText(MainActivity.this, "No movies in list", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQ_CODE) {
            if(resultCode == RESULT_OK){
                Movie result = (Movie) data.getExtras().getSerializable("newMovie");
                movies.add(result);
            }
        }

        if(requestCode == REQ_CODE_EDIT) {
            if(resultCode == RESULT_OK){
                Movie result = (Movie) data.getExtras().getSerializable("updatedMovie");
                int index = (int) data.getExtras().getSerializable("index");
                movies.set(index,result);
            }
        }
    }

    private ArrayList<String> getMovieName(){
        ArrayList<String> movieList = new ArrayList<String>();
        if(!movies.isEmpty()){
            for(Movie m: movies){
                movieList.add(m.name);
            }
        } else {
            return null;
        }
        return movieList;
    }

}
