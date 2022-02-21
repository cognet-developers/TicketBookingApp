package com.mycode.ticketbookingapp.profilefragment



import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mycode.ticketbookingapp.MainActivity
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentProfileBinding
import com.mycode.ticketbookingapp.profilefragment.editprofile.EditProfileFragment
import com.mycode.ticketbookingapp.profilefragment.settingsfragment.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment: Fragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       val binding:FragmentProfileBinding=
           DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
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

//         rootView.settings1.setOnClickListener{
//             activity?.let{
//                 val intent = Intent(it, SettingsActivity::class.java)
//                 it.startActivity(intent)
//             }
//         }


        val application:Application = requireNotNull(this.activity).application
        val activity:Activity= this.requireActivity()
        val viewModelFactory= ProfileViewModelFactory(application,activity)
        val profileViewModel = ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)

        binding.profileViewModel=profileViewModel
        binding.lifecycleOwner=this

//        profileViewModel.navigateToEditProfile.observe(viewLifecycleOwner, Observer {
//            if(it==true) {
//                findNavController().navigate(R.layout.fragment_editprofile)
//
//                profileViewModel.navigateToEditProfileDone()
//            }
//        })

        profileViewModel.loggedUser.observe(viewLifecycleOwner, Observer{
            if(it == true){
                val intent= Intent(application, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        })

        profileViewModel.navigateTosettings.observe(viewLifecycleOwner, Observer{
            if(it==true) {
                val intent = Intent(application, SettingsActivity::class.java)
//                intent.flags =
//                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                profileViewModel.navigateTosettingsdone()
            }

        })


        return binding.root
        }


}





