package net.chineseguide.jukuu.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import net.chineseguide.jukuu.databinding.FragmentDetailsBinding
import net.chineseguide.jukuu.di.viewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModel<DetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.title.observe(viewLifecycleOwner, Observer { binding.title.text = it })
        viewModel.details.observe(viewLifecycleOwner, Observer { binding.details.text = it })
    }
}