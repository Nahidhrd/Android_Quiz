package com.example.quiz_app_bc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.quiz_app_bc.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater)

        binding.playButton.setOnClickListener {

            findNavController().navigate(R.id.action_startFragment_to_gameFragment)

        }

        setDrawerMenu()

        // Inflate the layout for this fragment
        return binding.root
    }



    private fun setDrawerMenu() {
        val menuHost : MenuHost = requireActivity()
        menuHost.addMenuProvider(object :MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.drawer_menu,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                return when(menuItem.itemId) {

                R.id.rulesFragment -> {

                    rulesFragmentSet()
                    true
                }


                R.id.aboutFragment -> {

                    aboutFragmentSet()
                    true
                }

                    else -> false
                }

            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)

    }

    private fun rulesFragmentSet() {

        findNavController().navigate(R.id.action_startFragment_to_gameRulesFragment)

    }

    private fun aboutFragmentSet(){

        findNavController().navigate(R.id.action_startFragment_to_aboutFragment2)

    }

}