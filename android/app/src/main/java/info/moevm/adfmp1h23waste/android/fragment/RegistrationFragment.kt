package info.moevm.adfmp1h23waste.android.fragment

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import info.moevm.adfmp1h23waste.android.R
import info.moevm.adfmp1h23waste.android.databinding.FragmentRegistrationBinding
import info.moevm.adfmp1h23waste.android.databinding.InputFieldRegistrationBinding
import info.moevm.adfmp1h23waste.android.user.InputFieldRegistration

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_RegistrationFragment_to_AuthorizationFragment)
        }
        fillsInputFieldsRegistration()

        return binding.root
    }

    private fun fillsInputFieldsRegistration() {
        fillInputFieldRegistration(
            InputFieldsRegistration.NAME.inputFieldRegistration,
            binding.inputFieldName
        )
        fillInputFieldRegistration(
            InputFieldsRegistration.EMAIL.inputFieldRegistration,
            binding.inputFieldEmail
        )
        fillInputFieldRegistration(
            InputFieldsRegistration.PASSWORD.inputFieldRegistration,
            binding.inputFieldPassword
        )
        fillInputFieldRegistration(
            InputFieldsRegistration.REPEAT_PASSWORD.inputFieldRegistration,
            binding.inputFieldRepeatPassword
        )
    }

    private fun changeToPassword(inputFieldRegistration: InputFieldRegistration, linkField: EditText) {
        if(inputFieldRegistration.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            linkField.transformationMethod = PasswordTransformationMethod.getInstance();
        }
    }

    private fun fillInputFieldRegistration(
        inputFieldRegistration: InputFieldRegistration,
        inputFieldRegistrationBinding: InputFieldRegistrationBinding
    ) {
        val linkTitle: TextView = inputFieldRegistrationBinding.paramText
        val linkField: EditText = inputFieldRegistrationBinding.registrationTextField
        linkTitle.text = getText(inputFieldRegistration.paramName)
        changeToPassword(inputFieldRegistration, linkField)
        addRegistrationInputChangedListener(inputFieldRegistrationBinding)
    }

    private fun addRegistrationInputChangedListener(
        inputFieldRegistrationBinding: InputFieldRegistrationBinding
    ) {
        inputFieldRegistrationBinding.registrationTextField.addTextChangedListener(
            OnRegistrationInputTextChanged(
            )
        )
    }

    enum class InputFieldsRegistration(val inputFieldRegistration: InputFieldRegistration) {
        NAME(InputFieldRegistration(R.string.nameField, "", InputType.TYPE_CLASS_TEXT)),
        EMAIL(InputFieldRegistration(R.string.emailField, "", InputType.TYPE_CLASS_TEXT)),
        PASSWORD(InputFieldRegistration(R.string.passwordHint, "", InputType.TYPE_TEXT_VARIATION_PASSWORD)),
        REPEAT_PASSWORD(InputFieldRegistration(R.string.repeatPasswordHint, "",InputType.TYPE_TEXT_VARIATION_PASSWORD))
    }

    class OnRegistrationInputTextChanged : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            // ignore
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // ignore
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //inputFieldRegistration.inputText = (s ?: "empty") as String
            // ignore
        }
    }
}
