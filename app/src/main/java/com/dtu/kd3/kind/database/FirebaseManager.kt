package com.dtu.kd3.kind.database

import android.util.Log
import com.dtu.kd3.kind.model.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Hassan on 19-01-2023
 * S205409
 * Stonebank
 *
 * FirebaseManager is a class that is used to manage the firebase database
 *
 */
class FirebaseManager {

    companion object {
        val instance = FirebaseManager()
    }

    /**
     * login is a function that is used to login the user
     *
     * @param email: String is the email of the user
     * @param password: String is the password of the user
     *
     */

    fun login(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("FirebaseManager", "login: Success")
            } else {
                Log.d("FirebaseManager", "login: Failed")
            }
        }
    }

    /**
     *
     * createAccount is a function that is used to create an account for the user
     *
     * @param email: String is the email of the user
     * @param password: String is the password of the user
     * @param userViewModel: UserViewModel is the view model that is used to store the user data
     *
     */

    fun createAccount(email: String, password: String, viewModel: UserViewModel) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("FirebaseManager", "createAccount: Success")
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val database = FirebaseDatabase.getInstance().getReference("users")
                val user = HashMap<String, Any>()
                user["name"] = viewModel.name.value
                user["donator"] = viewModel.donator.value
                user["donated"] = viewModel.donated.value
                user["subscribed"] = viewModel.subscribed
                user["paymentmethod"] = viewModel.paymentmethod.value
                user["percentages"] = viewModel._percentages
                database.child(userId!!).setValue(user)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("FirebaseManager", "createAccount: User data stored successfully")
                        } else {
                            Log.d("FirebaseManager", "createAccount: Failed to store user data")
                        }
                    }
            } else {
                Log.d("FirebaseManager", "createAccount: Failed")
            }
        }
    }

    /**
     * getName is a function that is used to get the name of the user
     *
     * @param userId: String is the id of the user
     *
     */

    fun getName(userId: String, onSuccess: (String) -> Unit, onFailure: () -> Unit) {
        val database = FirebaseDatabase.getInstance().getReference("users/$userId/name")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val name = dataSnapshot.getValue(String::class.java)
                if (name != null) {
                    onSuccess(name)
                } else {
                    onFailure()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure()
            }
        })
    }

}