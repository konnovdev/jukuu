package net.chineseguide.jukuu.ui.home.sentence.dialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.DialogFragmentSentenceBinding
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.presentation.home.sentence.dialog.SentencesViewModel
import net.chineseguide.jukuu.ui.util.bundleOf
import net.chineseguide.jukuu.ui.util.observeSafe
import net.chineseguide.jukuu.ui.util.showLongToast

var Bundle.sentence: Sentence
    get() = getSerializable("sentence_arg") as Sentence
    set(value) = putSerializable("sentence_arg", value)

@AndroidEntryPoint
class SentenceDialogFragment() : AppCompatDialogFragment() {

    private val viewModel: SentencesViewModel by viewModels()

    companion object {
        fun newInstance(sentence: Sentence): DialogFragment =
            SentenceDialogFragment()
                .apply {
                    arguments = bundleOf { this.sentence = sentence }
                }
    }

    private lateinit var binding: DialogFragmentSentenceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentSentenceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        allowRoundedCorners()
        attachAnimation()
        initButtons()
        observeState()
    }

    private fun allowRoundedCorners() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun attachAnimation() {
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
    }

    private fun initButtons() {
        binding.copyOriginalTextButton.setOnClickListener {
            viewModel.copyOriginalText()
        }

        binding.copyTranslatedTextButton.setOnClickListener {
            viewModel.copyTranslatedText()
        }

        binding.copyBothButton.setOnClickListener {
            viewModel.copyBoth()
        }
    }

    private fun observeState() {
        viewModel.clipboardText.observeSafe(viewLifecycleOwner) { text ->
            copyTextToClipboard(text)
            showLongToast(R.string.sentence_dialog_fragment_text_copied_to_clipboard)
            dismiss()
        }
    }

    private fun copyTextToClipboard(text: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("textToCopy", text)
        clipboardManager.setPrimaryClip(clipData)
    }
}