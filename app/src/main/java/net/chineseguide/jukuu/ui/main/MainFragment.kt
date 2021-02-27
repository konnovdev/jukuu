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
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.FragmentMainBinding
import net.chineseguide.jukuu.presentation.main.MainViewModel
import net.chineseguide.jukuu.ui.util.extensions.addBackPressedListener

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

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
        setUpToolbar()
        setUpDrawer()

        addBackPressedListener {
            mainViewModel.back()
            //temporary solution to make previous menu checked when navigating back, TODO come up with a better solution
            binding.mainNavigationView.menu.getItem(0).isChecked = true;
        }
    }

    private fun setUpToolbar() {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.title = ""
    }

    private fun setUpDrawer() {
        binding.mainNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    mainViewModel.openHomeScreen()
                    binding.mainDrawerLayout.closeDrawers()
                }
                R.id.nav_settings -> {
                    mainViewModel.openSettingsScreen()
                    binding.mainDrawerLayout.closeDrawers()
                }
            }
            true
        }

        binding.mainNavigationView.menu.getItem(0).isChecked = true;

        hideKeyboardOnDrawerOpened()
    }

    private fun hideKeyboardOnDrawerOpened() {
        val activity = activity ?: return

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