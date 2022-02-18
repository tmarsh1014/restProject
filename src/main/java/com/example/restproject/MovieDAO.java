package com.example.restproject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {

    @PersistenceContext
    private EntityManager entMan;

    //Insert into database
    public Movie create(Movie movie){
        entMan.persist(movie);
        entMan.flush();
        return movie;
    }

    //Retrieve all Movies
    public List<Movie> getAll(){
        List<Movie> movies = entMan.createQuery("Select h from Movie h", Movie.class).getResultList();
        return movies;
    }

    //Retrieve a single Movie
    public Movie getByID(int id){
        Movie movie = entMan.find(Movie.class,id);
        if (movie == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID" + id + " was not found");
        }
        return movie;
    }

    //Update Movie
    public Movie updateMovie(Movie movie){
        entMan.merge(movie);
        entMan.flush();
        return movie;
    }

    //Delete Movie
    public void deleteByID(int id){
        Movie movie = getByID(id);
        if (movie == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID" + id + " was not found");
        }
        entMan.remove(movie);
    }
}
