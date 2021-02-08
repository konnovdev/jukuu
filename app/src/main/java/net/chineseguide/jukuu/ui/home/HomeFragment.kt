package net.chineseguide.jukuu.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.FragmentHomeBinding
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.presentation.home.HomeState
import net.chineseguide.jukuu.presentation.home.HomeViewModel
import net.chineseguide.jukuu.ui.home.sentence.dialog.SentenceDialogFragment
import net.chineseguide.jukuu.ui.util.VerticalScrollListener
import net.chineseguide.jukuu.ui.util.extensions.observeSafe
import net.chineseguide.jukuu.ui.util.extensions.setOnQuerySubmittedListener


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private companion object {
        const val SLIGHTLY_VISIBLE = 0.4f
        const val COMPLETELY_VISIBLE = 1f
    }

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var searchView: SearchView

    private lateinit var sentencesAdapter: SentencesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        searchView = inflateSearchView(menu, inflater)
        setUpAdapter()
        observeState()
    }

    private fun inflateSearchView(menu: Menu, inflater: MenuInflater): SearchView {
        inflater.inflate(R.menu.home_menu, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        with(searchView) {
            setOnQuerySubmittedListener(viewModel::search)
            setIconifiedByDefault(false)
            queryHint = getString(R.string.home_fragment_search)
        }

        return searchView
    }

    private fun setUpAdapter() {
        sentencesAdapter = SentencesAdapter(::openSentenceDialog)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), linearLayoutManager.orientation)

        with(binding.sentencesRecyclerView) {
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            adapter = sentencesAdapter
            addOnScrollListener(VerticalScrollListener {
                with(linearLayoutManager) {
                    val visibleItemCount = childCount
                    val firstVisibleItemPosition = findFirstVisibleItemPosition()
                    val totalItemCount = itemCount
                    val nearTheEndOfTheList =
                        visibleItemCount + firstVisibleItemPosition >= totalItemCount - 4

                    if (nearTheEndOfTheList) {
                        viewModel.listScrolled(searchView.query.toString(), totalItemCount)
                    }
                }
            })
        }
    }

    private fun openSentenceDialog(sentence: Sentence) {
        val tag = SentenceDialogFragment::class::java.get().canonicalName
        val dialogIsNotShowing = childFragmentManager.findFragmentByTag(tag) == null
        if (dialogIsNotShowing) {
            val sentenceDialogFragment = SentenceDialogFragment.newInstance(sentence)
            sentenceDialogFragment.show(childFragmentManager, tag)
        }
    }

    private fun observeState() {
        viewModel.state.observeSafe(viewLifecycleOwner, ::renderState)
    }

    private fun renderState(state: HomeState) {
        when (state) {
            is HomeState.EmptyNoSearch -> {
                searchView.requestFocus()
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.home_fragment_perform_search_hint)
            }

            is HomeState.Progress -> {
                showInProgress()
            }

            is HomeState.Content -> {
                showContent(state.sentences)
            }

            is HomeState.EmptyResultAfterSearch -> {
                showContent(emptyList())
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.home_fragment_nothing_was_found)
            }

            is HomeState.Error -> {
                showContent(emptyList())
                binding.emptyContentStub.isVisible = true
                binding.emptyContentStub.setText(R.string.home_fragment_error_after_search)
            }
        }
    }

    private fun showInProgress() {
        searchView.isEnabled = false
        binding.emptyContentStub.isVisible = false
        binding.sentencesRecyclerView.alpha = SLIGHTLY_VISIBLE
        binding.progressBar.isVisible = true
    }

    private fun showContent(sentences: List<Sentence>) {
        binding.sentencesRecyclerView.alpha = COMPLETELY_VISIBLE
        searchView.isEnabled = true
        binding.progressBar.isVisible = false
        sentencesAdapter.setItemList(sentences)
    }
}
