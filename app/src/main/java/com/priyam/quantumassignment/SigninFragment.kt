package com.priyam.quantumassignment

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.account.WorkAccount.getClient
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.internal.TelemetryLogging.getClient
import com.google.android.gms.safetynet.SafetyNet.getClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SigninFragment : Fragment() {

    private val RC_SIGN_IN: Int = 123
    private lateinit var googleSignInClient: GoogleSignInClient

    lateinit var buttonSignin: Button
    lateinit var googleImage: ImageView
    lateinit var editEmailSignin: EditText
    lateinit var editPassSignin: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_signin, container, false)

        editEmailSignin = view.findViewById(R.id.editEmailSignIN)
        editPassSignin = view.findViewById(R.id.editPassSignIn)

        auth  = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)



        buttonSignin = view.findViewById(R.id.signInBtn)
        buttonSignin.setOnClickListener {
            login()
        }
        googleImage = view.findViewById(R.id.googleLoginBtn)
        googleImage.setOnClickListener {
            signIn()
        }



        return view
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }


    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)!!
            Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        when {
            idToken != null -> {

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                (context as Activity?)?.let {
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(requireActivity()) { task1 ->
                        if (task1.isSuccessful) {
                            Log.d(TAG, "signInWithCredential:success")
                            val user = auth.currentUser

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task1.exception)

                        }
                    }

                }

            }
            else -> {
                // Shouldn't happen.
                Log.d(TAG, "No ID token!")
            }
        }



    }


    private fun login() {
        val email = editEmailSignin.text.toString()
        val password = editPassSignin.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Some of the fields are blank.", Toast.LENGTH_SHORT)
                .show()
        }
        (context as Activity?)?.let {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
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





