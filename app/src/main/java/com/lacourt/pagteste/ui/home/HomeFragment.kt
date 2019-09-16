package com.lacourt.pagteste.ui.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lacourt.pagteste.AppConstants
import com.lacourt.pagteste.R
import com.lacourt.pagteste.model.Movie
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tool_bar_layout.view.*
import okhttp3.internal.notify
import androidx.recyclerview.widget.LinearLayoutManager
import com.lacourt.pagteste.R.layout
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.ByteArrayOutputStream
import java.lang.Exception


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("callstest", "onCreateView called.\n")
        val root = inflater.inflate(layout.fragment_home, container, false)

        /*An 'activity' is passed in to '.of()' method below, because when passing
        'this', the ViewModel's init block is called twice.*/
        homeViewModel =
            ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
        Log.d("callstest", "homeViewModel initialized.\n")

        val progressBar: ProgressBar = root.findViewById(com.lacourt.pagteste.R.id.progress_circular)
        recyclerView = root.findViewById(com.lacourt.pagteste.R.id.movie_list)
        val adapter = MovieAdapter(context!!)

        recyclerView.computeVerticalScrollOffset()

        var gridLayoutManager = GridLayoutManager(context, 3)
        recyclerView.layoutManager = gridLayoutManager

        recyclerView.adapter = adapter
        progressBar.visibility = View.VISIBLE

        (recyclerView.adapter as MovieAdapter).registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                recyclerView.scrollToPosition(0)
            }
        })

        homeViewModel.movies.observe(this, Observer { pagedList ->
            adapter.submitList(pagedList)
            progressBar.visibility = View.INVISIBLE

            //TODO pagedList fetchs the entiry database and not the 50 it should fetch
            Log.d("testorder", ".observe: list = ${pagedList?.size}, dbCount = ${homeViewModel.movieDao.getCount()}")
        })

        return root
    }

    fun orderDateAsc() {
        homeViewModel.rearrengeMovies(AppConstants.DATE_ASC)
        Log.d("testorder", "orderDateAsc()")
    }

    fun orderDateDesc() {
        homeViewModel.rearrengeMovies(AppConstants.DATE_DESC)
        Log.d("testorder", "orderDateDesc()")
    }
}

