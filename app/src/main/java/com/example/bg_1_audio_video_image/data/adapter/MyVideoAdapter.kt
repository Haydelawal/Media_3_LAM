package com.example.bg_1_audio_video_image.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bg_1_audio_video_image.R
import com.example.bg_1_audio_video_image.data.model.MyVideoData
import com.example.bg_1_audio_video_image.databinding.AudioVideoLayoutBinding


class MyVideoAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<MyVideoAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: AudioVideoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<MyVideoData>() {
        override fun areItemsTheSame(oldItem: MyVideoData, newItem: MyVideoData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyVideoData, newItem: MyVideoData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {

            titleTextView.text = currentItem.title
            publisherOrArtisteNameTextView.text = currentItem.publisher

            thumbnailImageView
                .load(currentItem.thumbnail)
                {
                    crossfade(true)
                    crossfade(1000)
                    error(R.drawable.ic_videocam)
                }

//            videoView.player = currentItem.URL

        }

        holder.itemView.setOnClickListener {
            listener.onMyItemClick(currentItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            AudioVideoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    interface ClickListener {
        // item on click listener
        fun onMyItemClick(myVideoData: MyVideoData)
    }

}