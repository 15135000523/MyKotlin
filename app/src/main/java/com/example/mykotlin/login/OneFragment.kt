package com.example.mykotlin.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.mykotlin.R

/**
 * A simple [Fragment] subclass.
 */
class OneFragment : Fragment() {

    private lateinit var model: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = activity?.run {
             ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(LoginViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        return inflater.inflate(R.layout.fragment_one, container, false)
    }


}
