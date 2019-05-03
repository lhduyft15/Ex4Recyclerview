package com.hnam.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getAndPutData()
    }

    private fun getAndPutData() {
        val data = intent.extras

        if (data != null) {
            val title = data.getString(TITLE_KEY)
            val overview = data.getString(OVERVIEW_KEY)
            val poster_path = data.getString(POSTER_PATH_KEY)
            val video = data.getBoolean(VIDEO_KEY)
            val release_date = data.getString(RELEASE_DATE_KEY)
            val vote_average = data.getFloat(VOTE_AVERAGE_KEY)

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500/" + poster_path)
                .into(ivBackground)

            if (video)
                ivPlay.visibility = View.VISIBLE
            else
                ivPlay.visibility = View.INVISIBLE

            tvName.text = title

            tvReleaseDate.text = "Release date: " + release_date

            rb.rating = vote_average/2

            tvOverviewDetail.text = overview

        }
    }
}
