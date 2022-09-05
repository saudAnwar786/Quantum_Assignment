package com.priyam.quantumassignment


import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class SignupFragment : Fragment() {

    lateinit var editNameSignUp: EditText
    lateinit var editEmailSignUp: EditText
    lateinit var editNumberSignUp: EditText
    lateinit var editPassSignUp: EditText

    lateinit var firebaseAuth : FirebaseAuth

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


        firebaseAuth = FirebaseAuth.getInstance()


        return view

    }

    private fun signupUser() {
        val name = editNameSignUp.text.toString()
        val contact = editNumberSignUp.text.toString()
        val email = editEmailSignUp.text.toString()
        val password = editPassSignUp.text.toString()

        (context as Activity?)?.let {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    it,OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {

                        } else {

                        }
                    })
        }
    }
}

