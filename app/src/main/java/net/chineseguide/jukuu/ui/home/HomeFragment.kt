package net.chineseguide.jukuu.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.FragmentHomeBinding
import net.chineseguide.jukuu.di.viewModel
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.ui.home.sentence.dialog.SentenceDialogFragment
import net.chineseguide.jukuu.ui.observeSafe

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var adapter: SentencesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeView()
    }

    private fun initView() {
        adapter = SentencesAdapter(::onSentenceClicked)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.taskList.layoutManager = layoutManager

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.taskList.addItemDecoration(dividerItemDecoration)

        binding.taskList.setHasFixedSize(true)
        binding.taskList.adapter = adapter

        binding.searchBar.setOnClickListener {
            (it as SearchView).onActionViewExpanded()
        }
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(s: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                viewModel.search(binding.searchBar.query.toString())
                return true
            }
        })
        binding.searchBar.isIconified = false
        binding.searchBar.requestFocus()
    }

    private fun onSentenceClicked(sentence: Sentence) {
        val sentenceDialogFragment = SentenceDialogFragment.newInstance(sentence)
        sentenceDialogFragment.show(parentFragmentManager.beginTransaction(), "tag")
    }

    private fun observeView() {
        viewModel.state.observeSafe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: HomeState) {
        when (state) {
            is HomeState.EmptyNoSearch -> {
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.perform_search_hint)
            }

            is HomeState.Progress -> {
                showInProgress()
            }
            is HomeState.Success -> {
                showContent(state.sentenceCollection.sentences)

            }
            is HomeState.EmptyResultAfterSearch -> {
                showContent(emptyList())
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.nothing_was_found)
            }
            is HomeState.Error -> {
                showContent(emptyList())
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.error_after_search)
            }
        }
    }

    private fun showInProgress() {
        binding.searchBar.isEnabled = false
        binding.emptyContentStub.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showContent(sentences: List<Sentence>) {
        binding.searchBar.isEnabled = true
        binding.progressBar.isVisible = false
        adapter.setItemList(sentences)
    }
}
