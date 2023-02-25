package info.moevm.adfmp1h23waste.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import info.moevm.adfmp1h23waste.android.R
import info.moevm.adfmp1h23waste.android.databinding.FragmentHomeBinding
import info.moevm.adfmp1h23waste.android.user.UserManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpInstructionButton()
        fillAchievements(binding.achievementsLayout)

        return binding.root
    }

    private fun setUpInstructionButton() {
        binding.imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_instructionFragment)
        }
    }

    private fun fillAchievements(layout: LinearLayout) {
        if (UserManager.instance.user == null) {
            layout.addView(TextView(this.context).apply {
                this.text = "Достижения доступны только авторизованным пользователям"
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
