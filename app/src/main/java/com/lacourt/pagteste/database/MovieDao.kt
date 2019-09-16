package com.lacourt.pagteste.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.lacourt.pagteste.model.Movie

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Query("SELECT * FROM movies ORDER BY release_date DESC")
    fun dateDesc(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies ORDER BY release_date")
    fun dateAsc(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovieById(id: Int): Movie?

    @Query("SELECT COUNT(id) FROM movies")
    fun getCount(): Int

    @Query ("DELETE FROM movies")
    fun deleteAll()
//
//    @Query("SELECT * FROM movies WHERE id=:id" )
//    fun getMovieById(id: Int?): Movie
//
//    @Query("DELETE FROM movies WHERE id=:id")
//    fun deleteMovieById(id:Int)
//
//    @Delete
//    fun deleteMovie(card: Movie)
//

}