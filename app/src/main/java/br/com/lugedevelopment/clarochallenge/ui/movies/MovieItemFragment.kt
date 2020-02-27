package br.com.lugedevelopment.clarochallenge.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lugedevelopment.clarochallenge.R
import br.com.lugedevelopment.clarochallenge.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.dummy.DummyContent.MovieItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [MovieItemFragment.OnListFragmentInteractionListener] interface.
 */
class MovieItemFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private lateinit var moviesViewModel: MoviesViewModel

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_item_list, container, false)

        if (view is RecyclerView) {
        var movies: MutableList<MovieEntity> = arrayListOf()

            moviesViewModel =
                ViewModelProviders.of(this).get(MoviesViewModel::class.java)
            moviesViewModel.allMovies.observe(viewLifecycleOwner, Observer {
               movies.addAll(it)
            })

            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =
                    MyMovieItemRecyclerViewAdapter(
                        movies,
                        listener
                    )
            }


        }
        return view
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: MovieItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
