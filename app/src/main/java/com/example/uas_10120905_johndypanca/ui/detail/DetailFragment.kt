package com.example.uas_10120905_johndypanca.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.databinding.FragmentDetailBinding
import com.example.uas_10120905_johndypanca.ui.agent.AgentResponse

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the agent data passed from the argument
        val agentData = arguments?.getParcelable<AgentResponse.Data>("agent") as? AgentResponse.Data

        if (agentData != null) {
            binding.agentname.text = agentData.displayName

            Glide.with(requireContext())
                .load(agentData.fullPortrait)
                .placeholder(R.drawable.fullportrait)
                .into(binding.fullportrait)

            Glide.with(requireContext())
                .load(agentData.role.displayIcon)
                .placeholder(R.drawable.role_ic) // Set a placeholder image while loading
                .into(binding.role)

            binding.roletext.text = agentData.role.displayName

            Glide.with(requireContext())
                .load(agentData.abilities[0].displayIcon)
                .placeholder(R.drawable.skill) // Set a placeholder image while loading
                .into(binding.skill1ic)

            Glide.with(requireContext())
                .load(agentData.abilities[1].displayIcon)
                .placeholder(R.drawable.skill) // Set a placeholder image while loading
                .into(binding.skill2ic)

            Glide.with(requireContext())
                .load(agentData.abilities[2].displayIcon)
                .placeholder(R.drawable.skill) // Set a placeholder image while loading
                .into(binding.skill3ic)

            Glide.with(requireContext())
                .load(agentData.abilities[3].displayIcon)
                .placeholder(R.drawable.skill) // Set a placeholder image while loading
                .into(binding.skill4ic)

            // Set the click listener for the back button
            binding.backButton.setOnClickListener {
                // Navigate back to the previous screen or close the fragment
                findNavController().popBackStack()
            }

        } else {
            // Handle the case when agentData is null (e.g., show a default name or error message)
            binding.agentname.text = "Agent Data Not Available"
            binding.fullportrait.setImageResource(R.drawable.fullportrait) // Show a default image if the agent image is not available
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        // The argument key for passing agent data to DetailFragment
        const val ARG_AGENT_DATA = "agent"
    }

    fun onBackPressed() {
        // Navigate back to the previous screen or close the fragment
        findNavController().popBackStack()
    }
}
