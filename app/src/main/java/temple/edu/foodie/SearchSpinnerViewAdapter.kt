package temple.edu.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchSpinnerViewAdapter(private val searchTypeSet: MutableList<String>) : RecyclerView.Adapter<SearchSpinnerViewAdapter.RecViewHolder>() {

    inner class RecViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchType: TextView = view.findViewById(R.id.searchType)
        lateinit var choiceTypeString: String
        var isPlaying = false
        init {
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener {

            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        // Create a new view, which defines the UI of the list item
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_layout, parent, false)

        return RecViewHolder(layout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.searchType.text = searchTypeSet[position]
        viewHolder.choiceTypeString = searchTypeSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = searchTypeSet.size
}