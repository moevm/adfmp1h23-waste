package info.moevm.adfmp1h23waste.android.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import info.moevm.adfmp1h23waste.android.R
import info.moevm.adfmp1h23waste.android.databinding.FragmentTasksBinding
import info.moevm.adfmp1h23waste.android.databinding.SelectAnswerBinding
import info.moevm.adfmp1h23waste.android.databinding.TrashimageBinding
import info.moevm.adfmp1h23waste.android.user.Answer
import info.moevm.adfmp1h23waste.android.user.Task
import java.util.*

class TasksFragment : Fragment() {

    private var testCorrectAnswer: String = ""
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        testStartTask(Tasks.FIRST.taskEnum)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillAnswer(answer: Answer, selectAnswerBinding: SelectAnswerBinding) {
        val linkTitle: TextView = selectAnswerBinding.title
        val linkDescription: TextView = selectAnswerBinding.description

        changeAnswer(selectAnswerBinding, AnswerXmls.SELECT.answerXml)
        changeImage(selectAnswerBinding.file, answer.imageId)
        linkTitle.text = answer.title
        linkDescription.text = answer.description
        selectAnswerBinding.button.setOnClickListener { onClickAnswer(selectAnswerBinding) }
    }

    private fun fillTask(task: Task, trashimageBinding: TrashimageBinding) {
        val linkTitle: TextView = trashimageBinding.trashtext

        changeImage(trashimageBinding.trashimageEk4, task.imageId)
        testCorrectAnswer = task.correctAnswer
        linkTitle.text = task.title
    }

    private fun testStartTask(task: Task) {
        fillTask(task, binding.trashImage)
        fillAnswer(Answers.FIRST.answerEnum, binding.selectAnswer1)
        fillAnswer(Answers.SECOND.answerEnum, binding.selectAnswer2)
        fillAnswer(Answers.THIRD.answerEnum, binding.selectAnswer3)
    }

    private fun changeImage(imageView: ImageView, imageId: Int) {
        imageView.setImageResource(imageId)
    }

    private fun onClickAnswer(selectAnswerBinding: SelectAnswerBinding) {
        checkAnswer(selectAnswerBinding)
        showCorrectAnswers()
    }

    private fun changeAnswer(selectAnswerBinding: SelectAnswerBinding, resId: Int) {
        val xml: View = selectAnswerBinding.button
        xml.setBackgroundResource(resId)
    }

    private fun checkAnswer(selectAnswerBinding: SelectAnswerBinding) {
        if (selectAnswerBinding.description.text.toString() == testCorrectAnswer) {
            changeAnswer(selectAnswerBinding, AnswerXmls.CORRECT.answerXml)
        } else {
            changeAnswer(selectAnswerBinding, AnswerXmls.INCORRECT.answerXml)
        }
        testJumpNextTask()
    }

    private fun testJumpNextTask() {

        Timer().schedule(object : TimerTask() {
            override fun run() {
                testStartTask(Tasks.SECOND.taskEnum)
            }
        }, 300)
    }

    private fun checkCorrectAnswer(selectAnswerBinding: SelectAnswerBinding) {
        if (selectAnswerBinding.description.text.toString() == testCorrectAnswer) {
            changeAnswer(selectAnswerBinding, AnswerXmls.CORRECT.answerXml)
        }
    }


    private fun showCorrectAnswers() {
        checkCorrectAnswer(binding.selectAnswer1)
        checkCorrectAnswer(binding.selectAnswer2)
        checkCorrectAnswer(binding.selectAnswer3)
    }

    enum class Answers(val answerEnum: Answer) {
        FIRST(Answer(R.drawable.file, "1", "Корзина")),
        SECOND(Answer(R.drawable.file, "2", "Мусорка")),
        THIRD(Answer(R.drawable.file, "3", "Пакет"))
    }

    enum class Tasks(val taskEnum: Task) {
        FIRST(Task(R.drawable.trashimage_ek4, "Бутылка пластиковая", "Мусорка")),
        SECOND(Task(R.drawable.trashimage_ek4, "Бутылка не пластиковая", "Пакет"))
    }

    enum class AnswerXmls(val answerXml: Int) {
        INCORRECT(R.drawable.answer_incorrect_button),
        CORRECT(R.drawable.answer_correct_button),
        SELECT(R.drawable.answer_select_button)
    }
}