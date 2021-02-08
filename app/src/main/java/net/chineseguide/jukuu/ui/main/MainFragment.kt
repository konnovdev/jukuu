package net.chineseguide.jukuu.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as AppCompatActivity

        binding.mainNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    // TODO create a router and handle all the navigation on the presentation layer
                    val navController =
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    if (navController.currentDestination?.id != R.id.homeFragment) {
                        navController.navigate(R.id.homeFragment)
                    }
                    binding.mainDrawerLayout.closeDrawers()
                }
                R.id.nav_settings -> {
                    val navController =
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                    if (navController.currentDestination?.id != R.id.settingsFragment) {
                        navController.navigate(R.id.settingsFragment)
                    }
                    binding.mainDrawerLayout.closeDrawers()
                }
            }
            true
        }

        binding.mainNavigationView.menu.getItem(0).isChecked = true;

        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = ""

        val drawerToggle = object : ActionBarDrawerToggle(
            activity,
            binding.mainDrawerLayout,
            binding.toolbar,
            R.string.main_fragment_drawer_accessibility_action_open,
            R.string.main_fragment_drawer_accessibility_action_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
            }
        }

        binding.mainDrawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }
}