package com.mycode.ticketbookingapp.homefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mycode.ticketbookingapp.R
import com.mycode.ticketbookingapp.databinding.FragmentHomeBinding
import com.mycode.ticketbookingapp.signup.SignUpFragment
import com.mycode.ticketbookingapp.welcome.WelcomeFragmentDirections
import com.mycode.ticketbookingapp.welcome.WelcomeViewModel


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentHomeBinding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

//        binding.homeViewModel=homeViewModel
//
//        homeViewModel.navigateTo.observe(viewLifecycleOwner, Observer{
//            if(it) {
//                this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment())
//                homeViewModel.alreadyHaveAccountDone()
//            }
//        })
//        binding.card.setOnClickListener{
//            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment("action"))
//        }
        return binding.root
    }


}