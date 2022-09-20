package ru.gb.redditaww

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gb.redditaww.databinding.PostItemBinding
import ru.gb.redditaww.network.json.Post

class PostsAdapter :
    PagingDataAdapter<Post, PostsAdapter.RedditViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RedditViewHolder(PostItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RedditViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class RedditViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.score.text = post.score.toString()
            binding.comments.text = post.commentCount.toString()
            binding.title.text = post.title
            Glide.with(this.itemView.context)
                .load(post.imageURL)
                .placeholder(R.drawable.no_image)
                .into(binding.image)
        }
    }
}