package com.app_devs.myapplication.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"All records deleted}", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Delete?")
        builder.setMessage("Are you sure you want to delete all records ?")
        builder.create().show()
    }

}