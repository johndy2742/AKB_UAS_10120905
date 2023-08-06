package com.example.uas_10120905_johndypanca.ui.tiers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_10120905_johndypanca.MainActivity
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.databinding.FragmentTiersBinding
import com.example.uas_10120905_johndypanca.ui.api.FetchTiers
import com.example.uas_10120905_johndypanca.ui.api.RetrofitClient
import kotlinx.coroutines.launch

class TiersFragment : Fragment() {

    private var _binding: FragmentTiersBinding? = null
    private val binding get() = _binding!!

    private lateinit var tiersAdapter: TiersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTiersBinding.inflate(inflater, container, false)
        val burgerMenuButton: ImageButton = binding.root.findViewById(R.id.btnBurgerMenu)
        val mainActivity = requireActivity() as MainActivity
        burgerMenuButton.setOnClickListener {
            mainActivity.getDrawerLayout().openDrawer(GravityCompat.START)
        }
        val root: View = binding.root

        // Initialize the TiersAdapter with an empty list for now
        tiersAdapter = TiersAdapter(emptyList())
        binding.tierlist.layoutManager = LinearLayoutManager(context)
        binding.tierlist.adapter = tiersAdapter

        // Fetch data from the API and update the adapter when data is received
        fetchDataAndUpdateAdapter()

        return root
    }

    private fun fetchDataAndUpdateAdapter() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FetchTiers::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.getTiers()
                if (response.isSuccessful) {
                    val tiersResponse = response.body()
                    val tiersList = tiersResponse?.data?.tiers ?: emptyList()
                    tiersAdapter.updateTiersList(tiersList)
                } else {
                    // Handle API error
                }
            } catch (ex: Exception) {
                // Handle network error
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
