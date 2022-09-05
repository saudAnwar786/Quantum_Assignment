package com.priyam.quantumassignment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SigninFragment : Fragment() {

    private val RC_SIGN_IN: Int = 123
    private lateinit var googleSignInClient: GoogleSignInClient

    lateinit var buttonSignin: Button
    lateinit var googleImage: ImageView
    lateinit var fbLoginBtn: Button
    lateinit var editEmailSignin: EditText
    lateinit var editPassSignin: EditText
    private lateinit var auth: FirebaseAuth

    lateinit var callbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_signin, container, false)

        editEmailSignin = view.findViewById(R.id.editEmailSignIN)
        editPassSignin = view.findViewById(R.id.editPassSignIn)

        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)



        buttonSignin = view.findViewById(R.id.signInBtn)
        buttonSignin.setOnClickListener {
            loginWithEmail()
        }
        googleImage = view.findViewById(R.id.googleLoginBtn)
        googleImage.setOnClickListener {
            GooglesignIn()
        }

        callbackManager = CallbackManager.Factory.create()

        fbLoginBtn = view.findViewById(R.id.fbLoginBtn)
        printHashKey()

        fbLoginBtn.setPermission(listOf("email","public_profile","user_gender",
            "user_birthday","user_friends"))
        fbLoginBtn.registerCallback
        fbLoginBtn.setOnClickListener {
            fbSignIn()
        }

        return view
    }

    private fun printHashKey() {
        try {
            val info: PackageInfo = requireContext().packageManager.getPackageInfo(
                requireContext().packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashkey = String(Base64.encode(md.digest(), 0))
                Log.d("FBTAG", "printHashKey HashKey : $hashkey"  )
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.d("FBTAG", "printHashKey: ", e)
        } catch (e: Exception) {
            Log.d("FBTAG", "printHashKey: ", e)
        }

    }

    private fun fbSignIn() {

    }

    private fun GooglesignIn() {

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }

        callbackManager.onActivityResult(resultCode , resultCode, data)



    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)!!
            Log.d("MYTAG", "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.d("MYTAG", "signInResult:failed code=" + e.statusCode)

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        when {
            idToken != null -> {

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                (context as Activity?)?.let {
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {

                                val firebaseUser = auth.currentUser
                                Toast.makeText(
                                    context,
                                    "Authentication Sucess using Google.",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()


                            } else {
                                Toast.makeText(
                                    context,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }

                }

            }
            else -> {
                Toast.makeText(context, "No ID Token", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }


    private fun loginWithEmail() {
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







