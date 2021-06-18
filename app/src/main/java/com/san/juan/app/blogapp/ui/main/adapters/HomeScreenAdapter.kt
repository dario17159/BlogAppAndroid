package com.san.juan.app.blogapp.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.san.juan.app.blogapp.core.BaseViewHolder
import com.san.juan.app.blogapp.data.model.Post
import com.san.juan.app.blogapp.databinding.PostItemViewBinding

/**
 * @author Dario Carrizo on 8/2/2021
 **/
class HomeScreenAdapter(private val postList: List<Post>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            PostItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeScreenViewHolder(itemBinding, parent.context)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreenViewHolder -> holder.bind(postList[position])
        }
    }

    override fun getItemCount(): Int = postList.size

    private inner class HomeScreenViewHolder(
        val binding: PostItemViewBinding,
        val context: Context
    ) : BaseViewHolder<Post>(binding.root) {
        override fun bind(item: Post) {
            Glide.with(context)
                .load(item.post_image)
                .centerCrop()
                .into(binding.postImage)

            Glide.with(context)
                .load(item.profile_picture)
                .centerCrop()
                .into(binding.profileImage)

            binding.profileName.text = item.profile_name
            binding.postTimestamp.text = "Hace 2 horas"
        }
    }
}