package com.priyam.quantumassignment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class SigninFragment : Fragment() {

    lateinit var buttonSignin: Button
    lateinit var editEmailSignin: EditText
    lateinit var editPassSignin: EditText
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_signin, container, false)

        editEmailSignin = view.findViewById(R.id.editEmailSignIN)
        editPassSignin = view.findViewById(R.id.editPassSignIn)

        firebaseAuth = FirebaseAuth.getInstance()

        buttonSignin = view.findViewById(R.id.signInBtn)
        buttonSignin.setOnClickListener {
            login()
        }



        return view
    }

    private fun login(){
        val email = editEmailSignin.text.toString()
        val password = editPassSignin.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Some of the fields are blank.", Toast.LENGTH_SHORT)
                .show()
        }
        (context as Activity?)?.let {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    it, OnCompleteListener<AuthResult?> { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                context,
                                "Success",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
        }


    }

}