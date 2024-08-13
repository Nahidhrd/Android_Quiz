package com.example.quiz_app_bc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.quiz_app_bc.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    lateinit var binding : FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_game_over,container,false)
        // Inflate the layout for this fragment

        binding.tryAgainButton.setOnClickListener {

            findNavController().navigate(R.id.action_gameOverFragment_to_startFragment)
        }
        return binding.root

    }



}