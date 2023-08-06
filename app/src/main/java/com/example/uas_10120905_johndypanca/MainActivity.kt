package com.example.uas_10120905_johndypanca

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.uas_10120905_johndypanca.databinding.ActivityMainBinding
import com.example.uas_10120905_johndypanca.ui.weapon.DamageRange
import com.example.uas_10120905_johndypanca.ui.weapon.ShopData
import com.example.uas_10120905_johndypanca.ui.weapon.Weapon
import com.example.uas_10120905_johndypanca.ui.weapon.WeaponStats
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController // Declare navController as a class-level property
    private lateinit var drawerLayout: DrawerLayout
    fun getDrawerLayout(): DrawerLayout {
        return drawerLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Find the burger menu button by ID
        val burgerMenuButton: ImageButton = findViewById(R.id.btnBurgerMenu)

        // Set up the click listener for the burger menu button
        burgerMenuButton.setOnClickListener {
            // Open the navigation drawer when the button is clicked
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Find the NavHostFragment from the content_main layout
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as? NavHostFragment

        navHostFragment?.let {
            // Get the NavController from the NavHostFragment
            navController = it.navController
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_agent, R.id.nav_weapon, R.id.nav_tiers
                ), drawerLayout
            )

            navView.setupWithNavController(navController)
        }

        // Sample weapon data
        val weaponList = listOf(
            // ... add your other weapon items here ...
            Weapon(
                shopData = ShopData("3200", "Heavy Weapon"),
                displayName = "Odin",
                weaponStats = WeaponStats(
                    fireRate = 12.0, magazineSize = 100,
                    damageRanges = listOf(
                        DamageRange(0, 30, 38.0, 95.0, 32.3),
                        DamageRange(30, 50, 31.0, 77.5, 26.35)
                    ),
                ),
                displayIcon = "https://media.valorant-api.com/weapons/63e6c2b6-4a8e-869c-3d4c-e38355226584/displayicon.png"
            )
        )
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Check if the current destination is the DetailFragment
                if (navController.currentDestination?.id == R.id.fragmentDetail) {
                    // Navigate back to the previous screen or close the fragment
                    navController.popBackStack()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
