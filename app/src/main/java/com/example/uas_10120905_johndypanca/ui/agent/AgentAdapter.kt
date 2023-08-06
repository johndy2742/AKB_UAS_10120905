import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.databinding.ItemAgentBinding
import com.example.uas_10120905_johndypanca.ui.agent.AgentResponse

class AgentAdapter(private val onItemClick: (AgentResponse.Data) -> Unit) : RecyclerView.Adapter<AgentAdapter.AgentViewHolder>() {

    private val agentAbilitiesList: MutableList<Pair<AgentResponse.Data, List<AgentResponse.Data.Ability>>> = mutableListOf()

    fun setAgentAbilitiesList(agentAbilitiesList: List<Pair<AgentResponse.Data, List<AgentResponse.Data.Ability>>>) {
        this.agentAbilitiesList.clear()
        this.agentAbilitiesList.addAll(agentAbilitiesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        val binding = ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val agentData = agentAbilitiesList[position]
        holder.bind(agentData)
        holder.itemView.setOnClickListener { onItemClick(agentData.first) }
    }

    override fun getItemCount(): Int = agentAbilitiesList.size

    inner class AgentViewHolder(private val binding: ItemAgentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Pair<AgentResponse.Data, List<AgentResponse.Data.Ability>>) {
            val agent = item.first
            val abilities = item.second

            binding.run {
                // Set agent details
                agentname.text = agent.displayName
                Glide.with(agentimage.context)
                    .load(agent.fullPortrait)
                    .placeholder(R.drawable.fullportrait)
                    .into(agentimage)

                // Set abilities
                if (abilities.size >= 1) {
                    Glide.with(skill1)
                        .load(abilities[0].displayIcon)
                        .placeholder(R.drawable.skill)
                        .into(skill1)
                } else {
                    skill1.setImageDrawable(null)
                }

                if (abilities.size >= 2) {
                    Glide.with(skill2)
                        .load(abilities[1].displayIcon)
                        .placeholder(R.drawable.skill)
                        .into(skill2)
                } else {
                    skill2.setImageDrawable(null)
                }

                if (abilities.size >= 3) {
                    Glide.with(skill3)
                        .load(abilities[2].displayIcon)
                        .placeholder(R.drawable.skill)
                        .into(skill3)
                } else {
                    skill3.setImageDrawable(null)
                }

                if (abilities.size >= 4) {
                    Glide.with(skill4)
                        .load(abilities[3].displayIcon)
                        .placeholder(R.drawable.skill)
                        .into(skill4)
                } else {
                    skill4.setImageDrawable(null)
                }
            }
        }
    }
}
