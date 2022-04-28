package temple.edu.foodie

data class Review(var rating : String, var textBody : String, var datePosted : String)

class ReviewList{
    var reviews = ArrayList<Review>()
    val size : Int
        get() = reviews.size

    fun updateReviews (list: ReviewList){
        reviews = list.reviews
    }

    fun getReview(i: Int): Review{
        return reviews[i]
    }
}