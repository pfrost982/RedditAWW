package ru.gb.redditaww

import androidx.recyclerview.widget.DiffUtil
import ru.gb.redditaww.network.json.Post

class DiffUtilCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.key == newItem.key
                && oldItem.score == newItem.score
                && oldItem.commentCount == newItem.commentCount
                && oldItem.imageURL == newItem.imageURL
    }
}