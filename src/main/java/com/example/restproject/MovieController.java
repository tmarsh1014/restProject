package com.example.restproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieDAO movieDAO = new MovieDAO();

    //GET all Movies
    @RequestMapping(value = "/movies",method = RequestMethod.GET)
    public List<Movie> getMovies(){
        return movieDAO.getAll();
    }

    //GET single Movie
    @RequestMapping(value = "movies/{id}",method = RequestMethod.GET)
    public Movie movieByID(@PathVariable int id){
        return movieDAO.getByID(id);
    }

    //Create a Movie and send to database
    @RequestMapping(value="movies",method= RequestMethod.POST)
    public Movie createMovie(@RequestBody Movie movie){
       movieDAO.create(movie);
        return movie;
    }

    //Update Movie from database
    @RequestMapping(value = "movies",method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie){
        return movieDAO.updateMovie(movie);
    }

    //DELETE movie from database
    @RequestMapping(value = "movies/{id}",method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable int id){
        movieDAO.deleteByID(id);
        return "ID " + id + " deleted.";
    }
}
