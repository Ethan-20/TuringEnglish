package com.example.turingenglish.ui

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.turingenglish.MyApplication
import com.example.turingenglish.R
import com.example.turingenglish.activity.ActivityCollector
import com.example.turingenglish.activity.index.IndexActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // Snippet from "Navigate to the next Fragment" section goes here.

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val passwordTextInput = view.findViewById<TextInputEditText>(R.id.password_edit_text)
        val passwordEditText = view.findViewById<TextInputEditText>(R.id.password_edit_text)
        val nextButton = view.findViewById<MaterialButton>(R.id.next_button)
        nextButton.setOnClickListener {
            if (!isPasswordValid(passwordEditText.text)) {
                passwordEditText.error = getString(R.string.shr_error_password)
            }
            else{
                ActivityCollector.startOtherActivity(MyApplication.context,IndexActivity::class.java)
            }
        }

        passwordEditText.setOnKeyListener { view, i, keyEvent ->
            if (isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error=null
            }
            false
        }
        return view
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private  fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 6
    }

}