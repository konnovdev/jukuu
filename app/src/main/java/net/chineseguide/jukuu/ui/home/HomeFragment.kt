package net.chineseguide.jukuu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.chineseguide.jukuu.databinding.FragmentHomeBinding
import net.chineseguide.jukuu.di.viewModel
import net.chineseguide.jukuu.domain.entity.Result
import net.chineseguide.jukuu.ui.observeSafe

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: ResultAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeView()
    }

    private fun initView() {
        adapter = ResultAdapter(::onResultClicked)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.taskList.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.taskList.addItemDecoration(dividerItemDecoration)

        binding.taskList.setHasFixedSize(true)
        binding.taskList.adapter = adapter
    }

    private fun onResultClicked(result: Result) {
//        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(result.id)
//        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun observeView() {
        viewModel.state.observeSafe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: HomeState) {
        when (state) {
            is HomeState.EmptyResult -> {

            }
            is HomeState.Success -> {
                adapter.itemList = state.resultList
                adapter.notifyDataSetChanged()
            }
        }
    }
}
