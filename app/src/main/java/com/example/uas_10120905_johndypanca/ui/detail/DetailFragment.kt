package com.example.uas_10120905_johndypanca.ui.detail

import android.media.AudioManager
import android.media.MediaPlayer
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

    private lateinit var mediaPlayer: MediaPlayer
    private var voiceLineUrl: String? = null

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
        val agentData = arguments?.getParcelable<AgentResponse.Data>(ARG_AGENT_DATA) ?: return

        binding.agentname.text = agentData.displayName

        Glide.with(requireContext())
            .load(agentData.fullPortrait)
            .placeholder(R.drawable.fullportrait)
            .into(binding.fullportrait)

        Glide.with(requireContext())
            .load(agentData.role.displayIcon)
            .placeholder(R.drawable.role_ic)
            .into(binding.role)

        binding.roletext.text = agentData.role.displayName

        Glide.with(requireContext())
            .load(agentData.abilities[0].displayIcon)
            .placeholder(R.drawable.skill)
            .into(binding.skill1ic)

        Glide.with(requireContext())
            .load(agentData.abilities[1].displayIcon)
            .placeholder(R.drawable.skill)
            .into(binding.skill2ic)

        Glide.with(requireContext())
            .load(agentData.abilities[2].displayIcon)
            .placeholder(R.drawable.skill)
            .into(binding.skill3ic)

        Glide.with(requireContext())
            .load(agentData.abilities[3].displayIcon)
            .placeholder(R.drawable.skill)
            .into(binding.skill4ic)

        // Set the click listener for the back button
        binding.backButton.setOnClickListener {
            // Navigate back to the previous screen or close the fragment
            findNavController().popBackStack()
        }

        // Extract the voiceline URL from agentData
        val voiceLineUrl = agentData.voiceLine?.mediaList?.get(0)?.wave
        if (voiceLineUrl != null) {
            this.voiceLineUrl = voiceLineUrl
            prepareMediaPlayer(voiceLineUrl)
        }
    }

    private fun prepareMediaPlayer(voiceLineUrl: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setDataSource(voiceLineUrl)
        mediaPlayer.setOnPreparedListener { player ->
            player.start()
        }
        mediaPlayer.prepareAsync()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer.release()
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
