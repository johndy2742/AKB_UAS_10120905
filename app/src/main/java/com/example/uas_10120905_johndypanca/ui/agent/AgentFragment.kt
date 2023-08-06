package com.example.uas_10120905_johndypanca.ui.agent

import AgentAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.uas_10120905_johndypanca.MainActivity
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.databinding.FragmentAgentBinding
import com.example.uas_10120905_johndypanca.ui.api.FetchAgent
import com.example.uas_10120905_johndypanca.ui.api.RetrofitClient
import kotlinx.coroutines.launch

class AgentFragment : Fragment() {

    private var _binding: FragmentAgentBinding? = null
    private val binding get() = _binding!!
    private lateinit var agentAdapter: AgentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentAgentBinding.inflate(inflater, container, false)
        val burgerMenuButton: ImageButton = binding.root.findViewById(R.id.btnBurgerMenu)
        val mainActivity = requireActivity() as MainActivity
        burgerMenuButton.setOnClickListener {
            mainActivity.getDrawerLayout().openDrawer(GravityCompat.START)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        agentAdapter = AgentAdapter { agent ->
            // Navigate to DetailFragment and pass the agent data
            val bundle = bundleOf("agent" to agent)
            findNavController().navigate(R.id.action_agentFragment_to_fragmentDetail, bundle)
        }

        binding.agentlist.adapter = agentAdapter

        getAgentList()
    }

    private fun getAgentList() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FetchAgent::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.getData()
                if (response.isSuccessful()) {
                    val body = response.body()
                    val data = body?.data
                    if (data != null && data.isNotEmpty()) {
                        // Set the list of agents and abilities to the agentAdapter
                        agentAdapter.setAgentAbilitiesList(data.map { agent ->
                            Pair(agent, agent.abilities)
                        })
                    } else {

                    }
                } else {

                }
            } catch (ex: Exception) {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
