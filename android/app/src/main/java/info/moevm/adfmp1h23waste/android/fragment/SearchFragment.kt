package info.moevm.adfmp1h23waste.android.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import info.moevm.adfmp1h23waste.android.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var resultView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        resultView = TextView(this.context)
        binding.searchLayout.searchField.addTextChangedListener(OnSearchTestChanged(resultView))
    }

    class OnSearchTestChanged(
        private val textViewToChange: TextView
    ) : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            // ignore
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // ignore
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textViewToChange.text = s ?: "empty"
        }
    }
}
