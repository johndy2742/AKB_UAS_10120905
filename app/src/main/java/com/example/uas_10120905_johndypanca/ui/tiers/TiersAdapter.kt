package com.example.uas_10120905_johndypanca.ui.tiers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uas_10120905_johndypanca.R

class TiersAdapter(private var tiers: List<Tier>) : RecyclerView.Adapter<TiersAdapter.TierViewHolder>() {

    inner class TierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tierNameTextView: TextView = itemView.findViewById(R.id.tierstext)
        private val tierIconImageView: ImageView = itemView.findViewById(R.id.tiersic)

        fun bind(tier: Tier) {
            tierNameTextView.text = tier.tierName

            // Load tier icon using Glide or any other image loading library
            Glide.with(tierIconImageView)
                .load(tier.largeIcon)
                .into(tierIconImageView)
        }
    }

    // Add a helper function to filter out tier 1 and tier 2
    private fun filterTiersExcludingUnused(tiers: List<Tier>): List<Tier> {
        return tiers.filter { tier ->
            // Include the tier only if its tier value is greater than 2
            tier.tier == 0 || tier.tier > 2
        }
    }

    fun updateTiersList(newTiers: List<Tier>) {
        // Filter the new tiers list to exclude tier 1 and tier 2
        val filteredTiers = filterTiersExcludingUnused(newTiers)
        tiers = filteredTiers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TierViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tiers, parent, false)
        return TierViewHolder(view)
    }

    override fun onBindViewHolder(holder: TierViewHolder, position: Int) {
        holder.bind(tiers[position])
    }

    override fun getItemCount(): Int {
        return tiers.size
    }
}
