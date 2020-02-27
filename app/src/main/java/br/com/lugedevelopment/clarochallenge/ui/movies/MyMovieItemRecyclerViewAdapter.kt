package br.com.lugedevelopment.clarochallenge.ui.movies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lugedevelopment.clarochallenge.R
import br.com.lugedevelopment.clarochallenge.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.dummy.DummyContent.MovieItem
import br.com.lugedevelopment.clarochallenge.ui.movies.MovieItemFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_movie_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MovieItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieItemRecyclerViewAdapter(
    private val mValues: List<MovieEntity>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyMovieItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var movies = emptyList<MovieEntity>()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as MovieItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.movieName
        holder.mContentView.text = item.movieCategory

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun setMovies(movies: List<MovieEntity>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.movieNameTextView
        val mContentView: TextView = mView.categoryMovieTextView

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
