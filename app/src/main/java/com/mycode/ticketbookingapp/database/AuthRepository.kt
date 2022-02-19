package com.mycode.ticketbookingapp.database

import android.app.Application
import android.util.Log
import android.widget.Toast
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

    private var setUserDataRepository=MutableLiveData<String?>()

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

    fun setUserDataMutableLiveData(): MutableLiveData<String?> {
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
                Log.d("FuckYou", ticketBookingApp.username)
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
                Toast.makeText(application,"Welcome back!",Toast.LENGTH_LONG).show()
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
        reference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              if(auth.currentUser?.uid !=null) {
                  reference.child(auth.currentUser!!.uid).setValue(ticketBookingApp)
                  setUserDataRepository.postValue("Data is stored Successfully")
              }

            }


            override fun onCancelled(error: DatabaseError) {
                setUserDataRepository.postValue("Data is not stored Successfully")
            }
        })
    }

    fun getUserData(){
        val userData:Query=reference.orderByChild("uid").equalTo(auth.currentUser?.uid)
        userData.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    val ticketBookingApp=snapshot.getValue(TicketBookingApp::class.java)
                    if(ticketBookingApp!=null){
                        getUserDataRepository.postValue(ticketBookingApp)
                    }

            }
            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}




//
//
//
//                val ref1 = FirebaseDatabase.getInstance().getReference("/User/$uid")
//
//            ref1.addListenerForSingleValueEvent(object : ValueEventListener {
//                @RequiresApi(Build.VERSION_CODES.P)
//                override fun onDataChange(p0: DataSnapshot) {
//                    val user=p0.getValue(UsersModel::class.java)
//                        if (user != null) {
//                            if (user.profilepic!="") {
//                                displayname.setText(user.username)
//                                Picasso.with(context).load(user.profilepic).into(userdp2)
//                            } else {
//                                displayname.setText(user.username)
//                            }
//                            loading_spinner1.visibility = View.GONE
//                        }
//                    }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//            })
//        val ref2 = FirebaseDatabase.getInstance().getReference("/User/")
//            ref2.addChildEventListener(object : ChildEventListener {
//                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                    snapshot.children.forEach {
//                        val user = snapshot.getValue(UsersModel::class.java)
//                        if (user != null && user.uid == FirebaseAuth.getInstance().uid) {
//                            if (user.profilepic != "") {
//                                displayname.setText(user.username)
//                                Picasso.with(context).load(user.profilepic).into(userdp2)
//
//
//                            } else {
//                                displayname.setText(user.username)
//                            }
//                            loading_spinner1.visibility = View.GONE
//                        }
//                    }
//                }
//
//                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//
//                }
//
//                override fun onChildRemoved(snapshot: DataSnapshot) {
//
//                }
//
//                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
//
