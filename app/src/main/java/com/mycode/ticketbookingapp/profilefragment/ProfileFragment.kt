package com.mycode.ticketbookingapp.profilefragment



import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.mycode.ticketbookingapp.network.GenresListProperty
import com.mycode.ticketbookingapp.network.TMBDApi
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.network.list
import com.mycode.ticketbookingapp.profilefragment.settingsfragment.SettingsActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.*
import retrofit2.await


class ProfileFragment: Fragment() {

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
       val binding:FragmentProfileBinding=
           DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)



        val application:Application = requireNotNull(this.activity).application
        val activity:Activity= this.requireActivity()
        val viewModelFactory= ProfileViewModelFactory(application,activity)
        val profileViewModel = ViewModelProvider(this,viewModelFactory).get(ProfileViewModel::class.java)

        binding.profileViewModel=profileViewModel
        binding.lifecycleOwner=this


        profileViewModel.navigateToEditProfile.observe(viewLifecycleOwner, Observer {
            if(it==true) {
                this.findNavController()
                    .navigate(ProfileFragmentDirections.actionProfileToEditProfile())
                profileViewModel.navigateToEditProfileDone()


                //To try out the data output we get from the api Movies Details
//                var viewModelJob = Job()
//                val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//                coroutineScope.launch {
//                    var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ACTION, TMBDConstants.API_KEY)
//                    try {
//
//                        var listResult = getPropertiesDeferred.await()
//                       Log.d("Api Data",listResult.toString())
//
//                    }catch(e:Exception){
//                        Log.d("Exception","${e}")
//                        }
//                }
//            }


                //To try out the data output we get from the api Genres List

//                fun getGenresList(l: List<list>):List<list>{
//                    val localMovies: MutableList<list> = mutableListOf()
//                    l.forEach {
//                        localMovies.add(
//                            list(it.id,it.poster_path,it.original_title,it.vote_average))
//                                    Log.d("Api Data",
//                                        it.poster_path + " " +it.original_title+" " +it.vote_average
//                                    )
//
//                    }
//                    return localMovies
//
//                }
//
//                var viewModelJob = Job()
//                val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//                coroutineScope.launch {
//                    var getPropertiesDeferred = TMBDApi.retrofitService.getGenresList(TMBDConstants.ACTION, TMBDConstants.API_KEY)
//                    try {
//
//                        var listResult = getPropertiesDeferred.await()
//                        val genresList=getGenresList(listResult.items)
//                       Log.d("Api Data",genresList.toString())
//
//                    }catch(e:Exception){
//                        Log.d("Exception","${e}")
//                        }
//                }

           }



            })



        profileViewModel.getData.observe(viewLifecycleOwner, Observer {
            if(it!=null){
               loading_spinner1.visibility=View.GONE
                profileViewModel.function()
            }
        })

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
                startActivity(intent)
                profileViewModel.navigateTosettingsdone()
            }

        })


        return binding.root
        }


}





