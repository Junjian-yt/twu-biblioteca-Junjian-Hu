package com.twu.biblioteca;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Movies {

    private HashMap<String, String[]> MoviesList = new HashMap<String, String[]>();

    public Movies () {

    }

    public HashMap<String, String[]> InitMovieList (HashMap<String, String[]> MovieBooked) {
        if (MoviesList.keySet().size()==0&&MovieBooked.keySet().size()==0){
            String[] MovieDetailsOfTitanic = {"1997-12-17", "James Cameron", "☆☆☆☆☆"};
            String[] MovieDetailsOfTransformers = {"2007-5-11", "Michael Bay", "☆☆☆☆"};
            MoviesList.put("Titanic", Arrays.copyOf(MovieDetailsOfTitanic, MovieDetailsOfTitanic.length));
            MoviesList.put("Transformers", Arrays.copyOf(MovieDetailsOfTransformers, MovieDetailsOfTransformers.length));
        }
        return MoviesList;
    }

    public void DisplayMoviesList(HashMap<String, String[]> MovieList){
        Set<String> MoviesTitle = MovieList.keySet();
        if(MoviesTitle.size()!=0) {
            int NumOfOrder = 1;
            for (String title : MoviesTitle) {
                System.out.println(NumOfOrder + ". Title:" + title + " | Public Year: "
                        + MovieList.get(title)[0] + " | Author: " + MovieList.get(title)[1] + " | Rating: "
                        + MovieList.get(title)[2]);
                NumOfOrder++;
            }
        }else{
            System.out.println("Sorry, there are no any movies available right now.\n");
        }
    }




}
