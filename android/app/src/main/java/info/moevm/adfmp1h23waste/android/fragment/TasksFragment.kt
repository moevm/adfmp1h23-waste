package info.moevm.adfmp1h23waste.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import info.moevm.adfmp1h23waste.android.R
import info.moevm.adfmp1h23waste.android.databinding.FragmentTasksBinding
import info.moevm.adfmp1h23waste.android.databinding.SelectAnswerBinding
import info.moevm.adfmp1h23waste.android.databinding.TrashImageBinding
import info.moevm.adfmp1h23waste.android.user.Answer
import info.moevm.adfmp1h23waste.android.user.Task

class TasksFragment : Fragment() {

    private var testCorrectAnswer: String = ""
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        testStartTask(Tasks.FIRST_TASK.taskEnum)
        binding.buttonNextTask.setOnClickListener { onClickNextTask() }
        binding.imageButton.contentContainer.setOnClickListener {
            findNavController().navigate (
                R.id.action_TasksFragment_to_InstructionFragment
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillAnswer(answer: Answer, selectAnswerBinding: SelectAnswerBinding) {
        val linkTitle: TextView = selectAnswerBinding.title
        val linkDescription: TextView = selectAnswerBinding.description

        changeAnswer(selectAnswerBinding, AnswersXml.SELECT.answerXml)
        changeImage(selectAnswerBinding.file, answer.imageId)
        linkTitle.text = answer.title
        linkDescription.text = answer.description
        selectAnswerBinding.button.setOnClickListener { onClickAnswer(selectAnswerBinding) }
    }

    private fun fillTask(task: Task, trashImageBinding: TrashImageBinding) {
        val linkTitle: TextView = trashImageBinding.trashtext

        changeImage(trashImageBinding.trashimageEk4, task.imageId)
        testCorrectAnswer = task.correctAnswer
        linkTitle.text = task.title
    }

    private fun changeTextTask(string: String) {
        val linkTitle: TextView = binding.mainText
        linkTitle.text = string
    }

    private fun testStartTask(task: Task) {
        fillTask(task, binding.trashImage)
        fillAnswer(Answers.FIRST_ANSWER.answerEnum, binding.selectAnswer1)
        fillAnswer(Answers.SECOND_ANSWER.answerEnum, binding.selectAnswer2)
        fillAnswer(Answers.THIRD_ANSWER.answerEnum, binding.selectAnswer3)
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
            changeAnswer(selectAnswerBinding, AnswersXml.CORRECT.answerXml)
            changeTextTask(getString(R.string.correct))
        } else {
            changeAnswer(selectAnswerBinding, AnswersXml.INCORRECT.answerXml)
            changeTextTask(getString(R.string.incorrect))
        }
    }


    private fun onClickNextTask() {
        changeTextTask(getString(R.string.select_garbage_bag))
        testStartTask(Tasks.SECOND_TASK.taskEnum)
    }

    private fun checkCorrectAnswer(selectAnswerBinding: SelectAnswerBinding) {
        if (selectAnswerBinding.description.text.toString() == testCorrectAnswer) {
            changeAnswer(selectAnswerBinding, AnswersXml.CORRECT.answerXml)
        }
    }


    private fun showCorrectAnswers() {
        checkCorrectAnswer(binding.selectAnswer1)
        checkCorrectAnswer(binding.selectAnswer2)
        checkCorrectAnswer(binding.selectAnswer3)
    }

    enum class Answers(val answerEnum: Answer) {
        FIRST_ANSWER(Answer(R.drawable.file, "1", "Корзина")),
        SECOND_ANSWER(Answer(R.drawable.file, "2", "Мусорка")),
        THIRD_ANSWER(Answer(R.drawable.file, "3", "Пакет"))
    }

    enum class Tasks(val taskEnum: Task) {
        FIRST_TASK(Task(R.drawable.trashimage_ek4, "Бутылка пластиковая", "Мусорка")),
        SECOND_TASK(Task(R.drawable.trashimage_ek4, "Бутылка не пластиковая", "Пакет"))
    }

    enum class AnswersXml(val answerXml: Int) {
        INCORRECT(R.drawable.answer_incorrect_button),
        CORRECT(R.drawable.answer_correct_button),
        SELECT(R.drawable.answer_select_button)
    }
}
