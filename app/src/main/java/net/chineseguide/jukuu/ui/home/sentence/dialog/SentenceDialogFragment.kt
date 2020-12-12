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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import net.chineseguide.jukuu.R
import net.chineseguide.jukuu.databinding.DialogFragmentSentenceBinding
import net.chineseguide.jukuu.di.Scopes
import net.chineseguide.jukuu.di.module.SentenceDialogModule
import net.chineseguide.jukuu.di.viewModel
import net.chineseguide.jukuu.domain.entity.Sentence
import net.chineseguide.jukuu.ui.bundleOf
import net.chineseguide.jukuu.ui.observeSafe
import toothpick.Toothpick

var Bundle.sentence: Sentence
    get() = getSerializable("sentence_arg") as Sentence
    set(value) = putSerializable("sentence_arg", value)

class SentenceDialogFragment() : DialogFragment() {

    private val viewModel by viewModel<SentencesViewModel>()

    companion object {
        fun newInstance(sentence: Sentence): DialogFragment =
            SentenceDialogFragment()
                .apply {
                    arguments = bundleOf { this.sentence = sentence }
                    val fragmentScope = Toothpick.openScope(Scopes.FRAGMENT)
                    fragmentScope.installModules(SentenceDialogModule(requireArguments().sentence))
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
        initButtons()
        observeState()
    }

    private fun allowRoundedCorners() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
            val clipboardManager =
                requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("textToCopy", text)
            clipboardManager.primaryClip = clipData
            Toast.makeText(requireActivity(), R.string.text_copied_to_clipboard, Toast.LENGTH_LONG)
                .show()
            dismiss()
        }
    }
}