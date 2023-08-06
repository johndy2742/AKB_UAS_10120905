package com.example.uas_10120905_johndypanca.ui.weapon
import WeaponAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_10120905_johndypanca.MainActivity
import com.example.uas_10120905_johndypanca.R
import com.example.uas_10120905_johndypanca.databinding.FragmentWeaponBinding
import com.example.uas_10120905_johndypanca.ui.api.FetchWeapon
import com.example.uas_10120905_johndypanca.ui.api.RetrofitClient
import kotlinx.coroutines.launch


class WeaponFragment : Fragment() {

    private var _binding: FragmentWeaponBinding? = null
    private val binding get() = _binding!!

    private lateinit var weaponAdapter: WeaponAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponBinding.inflate(inflater, container, false)
        val burgerMenuButton: ImageButton = binding.root.findViewById(R.id.btnBurgerMenu)
        val mainActivity = requireActivity() as MainActivity
        burgerMenuButton.setOnClickListener {
            mainActivity.getDrawerLayout().openDrawer(GravityCompat.START)
        }
        val root: View = binding.root

        // Initialize the WeaponAdapter with an empty list for now
        weaponAdapter = WeaponAdapter(emptyList())
        binding.weaponlist.adapter = weaponAdapter

        // Fetch data from the API and update the adapter when data is received
        fetchDataAndUpdateAdapter()

        return root
    }

    private fun fetchDataAndUpdateAdapter() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(FetchWeapon::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.getWeapon()
                if (response.isSuccessful) {
                    val weaponResponse = response.body()
                    val weaponList = weaponResponse?.data ?: emptyList()
                    weaponAdapter.updateWeaponList(weaponList)
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
