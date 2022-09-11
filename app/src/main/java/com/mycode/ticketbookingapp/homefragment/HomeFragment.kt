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
import com.mycode.ticketbookingapp.network.TMBDConstants
import com.mycode.ticketbookingapp.signup.SignUpFragment
import com.mycode.ticketbookingapp.welcome.WelcomeFragmentDirections
import com.mycode.ticketbookingapp.welcome.WelcomeViewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentHomeBinding=
            DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.homeViewModel=homeViewModel

        binding.lifecycleOwner=this


        homeViewModel.action.observe(viewLifecycleOwner, Observer{
            if(it==true){
                this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.ACTION))
                homeViewModel.ActionDone()

            }
        })

        binding.card1.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.CRIME))
        }

        binding.card2.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.COMEDY))
        }

        binding.card3.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.MUSIC))
        }

        binding.card4.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.FANTASY))
        }

        binding.card5.setOnClickListener{
            this.findNavController().navigate(HomeFragmentDirections.actionHomeToGridMovieFragment(TMBDConstants.HISTORY))
        }


        //Initialize the adapter onClick event happen on each object (lamba function)
        return binding.root
    }


}