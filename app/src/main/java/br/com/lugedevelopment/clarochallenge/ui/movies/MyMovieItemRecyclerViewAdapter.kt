package br.com.lugedevelopment.clarochallenge.ui.movies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lugedevelopment.clarochallenge.R
import br.com.lugedevelopment.clarochallenge.data.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.data.models.Movie
import br.com.lugedevelopment.clarochallenge.dummy.DummyContent.MovieItem
import br.com.lugedevelopment.clarochallenge.ui.movies.MovieItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [MovieItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMovieItemRecyclerViewAdapter(
    private val mValues: List<Movie>,
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
        holder.mIdView.text = item.title
        holder.mContentView.text = item.voteAverage.toString()
        item.posterPath?.let { holder.bind(it) }


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
        val mContentView: TextView = mView.ratingMovieTextView
        val mImageView: ImageView = mView.posterImageView

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }

        fun bind(posterPath: String){
            val picasso = Picasso.get()
            val url = "https://image.tmdb.org/t/p/w500" + posterPath
            picasso.load(url)
                .resize(150,200)
                .into(mImageView)
        }

    }
}
