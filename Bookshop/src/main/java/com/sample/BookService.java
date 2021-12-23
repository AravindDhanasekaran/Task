package com.sample;
import com.sample.model.BookType;

import java.util.ArrayList;
import java.util.List;
class BookService{
    public List getAvailablegeners(BookType type){

        List genres = new ArrayList();

        if(type.equals(BookType.LOVESTORY)){
            genres.add("Two state");
            genres.add(("Half girlfriend"));

        }else if(type.equals(BookType.HORROR)){
            genres.add("Evil dead");


        }else if(type.equals(BookType.THRILLER)){
            genres.add("Girl at Room no 105");
        }else if(type.equals(BookType.CRIMETHRILLER)){
            genres.add("Sherlockhomes");

        }else {
            genres.add("No Brand Available");
        }
        return genres;
    }
    
}