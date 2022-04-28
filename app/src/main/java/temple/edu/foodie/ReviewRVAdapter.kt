package temple.edu.foodie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewRVAdapter (private var reviewList: ReviewList, foodieViewModel: FoodieViewModel): RecyclerView.Adapter<ReviewRVAdapter.ViewHolder>()  {

    private val fVM = foodieViewModel

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val reviewRating : TextView = itemView.findViewById(R.id.reviewRating)
        val reviewDate : TextView = itemView.findViewById(R.id.reviewDate)
        val reviewBody : TextView = itemView.findViewById(R.id.reviewBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_recyc_view, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reviewRating.text = "${reviewList.reviews[position].rating} stars"
        holder.reviewDate.text = "posted: ${reviewList.reviews[position].datePosted}"
        holder.reviewBody.text = reviewList.reviews[position].textBody
        holder.itemView.setOnClickListener {
            // fVM.setSelectedRestaurant(reviewList.reviews[position])
        }
    }

    override fun getItemCount() = reviewList.reviews.size
}