package com.mycode.ticketbookingapp.ui.home.profilefragment


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.mycode.ticketbookingapp.ui.welcome.WelcomeActivity
import com.squareup.picasso.Picasso
import com.mycode.ticketbookingapp.R
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
//        val uid=FirebaseAuth.getInstance().uid
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
//        rootView.edit.setOnClickListener {
//            activity?.let {
//                val intent = Intent(it, EditProfile::class.java)
//                it.startActivity(intent)
//            }
//        }
//         rootView.settings1.setOnClickListener{
//             activity?.let{
//                 val intent = Intent(it, SettingsActivity::class.java)
//                 it.startActivity(intent)
//             }
//         }
//            rootView.logout.setOnClickListener {
//                activity?.let {
//                    Firebase.auth.signOut()
//                    val intent = Intent(it, WelcomeActivity::class.java)
////                    intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    it.startActivity(intent)
//                    it.finish()
//                }
//            }
            return rootView
        }


}





