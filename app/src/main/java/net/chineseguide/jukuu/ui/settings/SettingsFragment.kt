package net.chineseguide.jukuu.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.FragmentSettingsBinding
import net.chineseguide.jukuu.domain.entity.AppLocale
import net.chineseguide.jukuu.ui.util.config.LocaleManager
import net.chineseguide.jukuu.ui.util.config.ThemeManager
import net.chineseguide.jukuu.ui.util.extensions.getLocale
import net.chineseguide.jukuu.ui.util.extensions.setOnItemSelectedListener
import net.chineseguide.jukuu.ui.util.extensions.toAppLocale

// TODO move all the logic and navigation to the presentation layer
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.main_fragment_menu_settings)

        setUpLanguageItem()
        setUpThemeItem()
    }

    private fun setUpLanguageItem() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.settings_fragment_languages_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.languageSpinner.adapter = adapter
        }

        binding.languageSpinner.setSelection(resources.getLocale().toAppLocale().id)

        binding.languageSpinner.setOnItemSelectedListener { languageId ->

            val selectedLocale = AppLocale.getById(languageId)
            if (resources.getLocale().toAppLocale() != selectedLocale) {
                LocaleManager.saveLocale(
                    requireContext(),
                    selectedLocale.languageCode,
                    selectedLocale.countryCode
                )
                activity?.recreate()
            }
        }

        binding.languageTv.setOnClickListener { binding.languageSpinner.performClick() }
    }

    private fun setUpThemeItem() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> binding.nightThemeSwitch.isChecked = true
            Configuration.UI_MODE_NIGHT_NO -> binding.nightThemeSwitch.isChecked = false
        }

        binding.nightThemeTv.setOnClickListener {
            binding.nightThemeSwitch.isChecked = !binding.nightThemeSwitch.isChecked

            ThemeManager.saveNightThemeOn(requireContext(), binding.nightThemeSwitch.isChecked)

            if (binding.nightThemeSwitch.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}