package info.moevm.adfmp1h23waste.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import info.moevm.adfmp1h23waste.android.R
import info.moevm.adfmp1h23waste.android.databinding.AchievementBinding
import info.moevm.adfmp1h23waste.android.databinding.FragmentHomeBinding
import info.moevm.adfmp1h23waste.android.user.Achievement
import kotlin.math.round


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUpTransitionButtons()
        fillAchievements()

        return binding.root
    }

    private fun setUpTransitionButtons() {
        binding.imageButton.contentContainer.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_instructionFragment)
        }
        binding.About.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_aboutFragment)
        }
    }

    private fun fillAchievement(achievementBinding: AchievementBinding, achievement: Achievement) {
        val linkTitle = achievementBinding.title
        val linkDescription = achievementBinding.description

        linkTitle.text = achievement.name
        linkDescription.text = "Выполнено " +
                round(achievement.current).toInt().toString() +
                " из " + round(achievement.max).toInt().toString()
    }

    private fun fillAchievements() {
        fillAchievement(binding.achievement1, Achievements.FIRST_ACHIEVEMENT.achievement)
        fillAchievement(binding.achievement2, Achievements.SECOND_ACHIEVEMENT.achievement)
        fillAchievement(binding.achievement3, Achievements.THIRD_ACHIEVEMENT.achievement)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    enum class Achievements(val achievement: Achievement) {
        FIRST_ACHIEVEMENT(Achievement("Всего заданий", 94.0, 365.0)),
        SECOND_ACHIEVEMENT(Achievement( "Подряд без ошибок", 94.0, 365.0)),
        THIRD_ACHIEVEMENT(Achievement( "Процент правильных", 94.0, 365.0))
    }
}

