package ru.zulvit.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.zulvit.databinding.ItemCharacterBinding
import ru.zulvit.data.models.GOTCharacter

class CharacterAdapter(private val onCharacterClick: (GOTCharacter) -> Unit) :
    ListAdapter<GOTCharacter, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: GOTCharacter) {
            binding.character = character
            binding.root.setOnClickListener {
                onCharacterClick(character)
            }
            binding.executePendingBindings()
        }
    }

    class CharacterDiffCallback : DiffUtil.ItemCallback<GOTCharacter>() {
        override fun areItemsTheSame(oldItem: GOTCharacter, newItem: GOTCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GOTCharacter, newItem: GOTCharacter): Boolean {
            return oldItem == newItem
        }
    }
}
