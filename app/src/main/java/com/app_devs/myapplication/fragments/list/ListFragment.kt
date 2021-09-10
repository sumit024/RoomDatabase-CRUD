package com.app_devs.myapplication.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app_devs.myapplication.R
import com.app_devs.myapplication.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_list, container, false)
        view.fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val adapterObj=ListAdapter()
        view.recyclerView.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=adapterObj
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapterObj.setData(it)
        })
        return view
    }

}