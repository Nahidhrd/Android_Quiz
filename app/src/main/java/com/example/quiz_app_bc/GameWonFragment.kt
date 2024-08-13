package com.example.quiz_app_bc

import android.content.Intent
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
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.quiz_app_bc.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    lateinit var binding: FragmentGameWonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGameWonBinding.inflate(layoutInflater)

        val score = GameWonFragmentArgs.fromBundle(requireArguments()).score
        val numQues = GameWonFragmentArgs.fromBundle(requireArguments()).numberOfQuestion

        binding.apply {

            txtScore.text = "Your Score $score is out of $numQues Quiz"

        }

        setAppbarMenu()

        binding.nextMatchButton.setOnClickListener {
           findNavController().navigate(R.id.action_gameWonFragment_to_startFragment)

        }
        return binding.root
    }

    private fun setAppbarMenu() {
        val menuHost : MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.winner_menu,menu)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

           return when(menuItem.itemId){
                R.id.share -> {
                    shareAchievement()
                    true
                }
                else -> false
            }

            }

        },viewLifecycleOwner,Lifecycle.State.RESUMED)
    }

    private fun shareAchievement() {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,binding.txtScore.text.toString())
            startActivity(shareIntent)

    }

}