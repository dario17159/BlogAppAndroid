package com.san.juan.app.blogapp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Dario Carrizo on 8/2/2021
 **/
abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind( item: T)
}