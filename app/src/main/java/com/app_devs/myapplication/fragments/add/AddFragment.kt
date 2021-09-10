package com.app_devs.myapplication.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app_devs.myapplication.R
import com.app_devs.myapplication.model.User
import com.app_devs.myapplication.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)
        view.btn_add.setOnClickListener {
            addToDatabase()
        }
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        return view
    }

    private fun addToDatabase() {
        val name=et_name.text.toString()
        val age=et_age.text
        val vaccination_status=et_status.text.toString()
        if(verifyInput(name,age,vaccination_status))
        {
            val user= User(0,name,Integer.parseInt(age.toString()),vaccination_status)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"All fields should be filled",Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyInput(name:String, age:Editable,status:String):Boolean
    {
        return !(TextUtils.isEmpty(name) &&TextUtils.isEmpty(age) &&TextUtils.isEmpty(status) )
    }
}