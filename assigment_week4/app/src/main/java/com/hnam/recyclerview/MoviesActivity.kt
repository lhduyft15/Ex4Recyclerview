package com.hnam.recyclerview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MoviesActivity : AppCompatActivity() {

    var movies: ArrayList<Movie> = ArrayList()
    lateinit var movieAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addMovies()

        rvMovies.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        movieAdapter = MoviesAdapter(movies, this)

        rvMovies.adapter = movieAdapter

        movieAdapter.setListener(movieItemCLickListener)
    }

    private val movieItemCLickListener = object : MovieItemCLickListener {
        override fun onItemCLicked(position: Int) {

            val intent = Intent(this@MoviesActivity, DetailActivity::class.java)
            intent.putExtra(POSTER_PATH_KEY, movies[position].poster_path)
            intent.putExtra(VIDEO_KEY,  movies[position].video)
            intent.putExtra(TITLE_KEY, movies[position].title)
            intent.putExtra(RELEASE_DATE_KEY, movies[position].release_date)
            intent.putExtra(VOTE_AVERAGE_KEY, movies[position].vote_average)
            intent.putExtra(OVERVIEW_KEY, movies[position].overview)
            startActivity(intent)

        }

        override fun onItemLongCLicked(position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    private fun addMovies() {
        var infoMovieString = FakeService.getMovieRaw()
        val obj = JSONObject(infoMovieString)
        val jsonMovie = obj.get("results").toString()

        val collectionType = object : TypeToken<Collection<Movie>>(){}.type

        movies = Gson().fromJson(jsonMovie, collectionType)
    }
}
