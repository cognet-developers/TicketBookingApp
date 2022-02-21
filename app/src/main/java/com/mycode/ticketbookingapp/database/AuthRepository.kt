package com.mycode.ticketbookingapp.database

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mycode.ticketbookingapp.model.TicketBookingApp


class AuthRepository(application: Application){
    private var auth: FirebaseAuth
    private var firebaseDatabase:FirebaseDatabase
    private var reference:DatabaseReference
    private var application:Application
    private var firebaseUserAuthRepository= MutableLiveData<FirebaseUser?>()
    private var userLoggedAuthRepository=MutableLiveData<Boolean?>()
    private var setUserDataRepository=MutableLiveData<Boolean?>()
    private var getUserDataRepository=MutableLiveData<TicketBookingApp?>()


    init{
        this.application=application
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("ticketBookingAppDB")
        auth= FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            firebaseUserAuthRepository.postValue(auth.currentUser)

        }
    }

    fun getFirebaseUserMutableLiveData(): MutableLiveData<FirebaseUser?>{
        return firebaseUserAuthRepository
    }

    fun getUserLoggedMutableLiveData(): MutableLiveData<Boolean?> {
        return userLoggedAuthRepository
    }

    fun setUserDataMutableLiveData(): MutableLiveData<Boolean?> {
        return setUserDataRepository
    }

    fun getUserDataMutableLiveData(): MutableLiveData<TicketBookingApp?> {
        return getUserDataRepository
    }

    fun register(username:String,email:String,password:String){
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(application, "Please enter you email address or password", Toast.LENGTH_LONG)
                .show()
            return
        }
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                firebaseUserAuthRepository.postValue(auth.currentUser)
                val ticketBookingApp=TicketBookingApp(username,email,password)
                setUserData(ticketBookingApp)
                Log.d("SignUp", "${it.result?.user?.uid}")
            }

            .addOnFailureListener{
                Toast.makeText(application, "${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    fun login(email:String,password: String){
        if(email.isEmpty()||password.isEmpty()){
            Toast.makeText(application, "Please enter you email address or password", Toast.LENGTH_LONG)
                .show()
            return
        }
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                firebaseUserAuthRepository.postValue(auth.currentUser)
                Log.d("SignIn", "${it.result?.user?.uid}")
            }

            .addOnFailureListener{
                Toast.makeText(application,"${it.message}",Toast.LENGTH_LONG).show()
            }
    }

    fun signOut(){
        auth.signOut()
        userLoggedAuthRepository.postValue(true)

    }



    fun setUserData(ticketBookingApp: TicketBookingApp){
        reference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (auth.currentUser?.uid != null) {
                    reference.child(auth.currentUser!!.uid).setValue(ticketBookingApp)
                }

                setUserDataRepository.postValue(true)
            }


            override fun onCancelled(error: DatabaseError) {
                setUserDataRepository.postValue(false)
            }
        })
    }

    fun getUserData(){
        val userData:Query=firebaseDatabase.getReference("/ticketBookingAppDB/${auth.currentUser?.uid}")
        userData.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    val ticketBookingApp = snapshot.getValue(TicketBookingApp::class.java)
                    if (ticketBookingApp != null) {
                        getUserDataRepository.postValue(ticketBookingApp)
                    }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}
