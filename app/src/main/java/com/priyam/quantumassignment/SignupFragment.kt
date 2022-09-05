package com.priyam.quantumassignment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {

    lateinit var editNameSignUp: EditText
    lateinit var editEmailSignUp: EditText
    lateinit var editNumberSignUp: EditText
    lateinit var editPassSignUp: EditText
    lateinit var buttonSignup: Button

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_signup, container, false)
        editNameSignUp = view.findViewById(R.id.editNameSignUp)
        editEmailSignUp = view.findViewById(R.id.editEmailSignUp)
        editNumberSignUp = view.findViewById(R.id.editNumberSignUp)
        editPassSignUp = view.findViewById(R.id.editPassSignUp)
        buttonSignup = view.findViewById(R.id.signUpBtn)


        firebaseAuth = FirebaseAuth.getInstance()

        buttonSignup.setOnClickListener {
            signupUser()
        }


        return view

    }

    private fun signupUser() {
        val name = editNameSignUp.text.toString()
        val contact = editNumberSignUp.text.toString()
        val email = editEmailSignUp.text.toString()
        val password = editPassSignUp.text.toString()

        if (email.isBlank() || password.isBlank() || name.isBlank() || contact.isBlank()) {
            Toast.makeText(context, "Some of the fields are blank or incorrect", Toast.LENGTH_SHORT)
                .show()
        } else if (password.length < 6) {
            Toast.makeText(context, "Password too short", Toast.LENGTH_SHORT).show()
        } else {
            (context as Activity?)?.let {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        it, OnCompleteListener<AuthResult?> { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Signed up successfully.",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                Toast.makeText(context, "Some error occurred.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
            }
        }


    }
}

