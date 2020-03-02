package br.com.lugedevelopment.clarochallenge.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.lugedevelopment.clarochallenge.R
import br.com.lugedevelopment.clarochallenge.data.models.Movie
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies, container, false)

        val movies: MutableList<Movie> = arrayListOf()
        val searchProgressBar = root.searchMovieProgressBar

        val recyclerView = root.listMoviesRecyclerView

        val adapter = MyMovieItemRecyclerViewAdapter(movies, listener)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        root.searchMovieEditText.setOnTouchListener(View.OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= root.searchMovieEditText.getRight() - root.searchMovieEditText.getCompoundDrawables().get(
                        DRAWABLE_RIGHT
                    ).getBounds().width()
                ) {
                    moviesViewModel.searchMovieResult(searchMovieEditText.text.toString())
                    return@OnTouchListener true
                }
            }
            false
        })

        moviesViewModel.moviesResult.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            adapter.setMovies(movies)
        })

        moviesViewModel.errorResult.observe(viewLifecycleOwner, Observer {
            super.getView()?.let { it1 ->
                Snackbar.make(
                    it1,
                    "Error searching for your movie, try again later",
                    Snackbar.LENGTH_LONG
                ).setAction("OK", null).show()
            }
        })

        moviesViewModel.showProgressDialog.observe(viewLifecycleOwner, Observer {
            if (it) {
                searchProgressBar.visibility = ProgressBar.VISIBLE
            } else {
                searchProgressBar.visibility = ProgressBar.GONE
            }
        })
        return root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Movie)
    }
}


