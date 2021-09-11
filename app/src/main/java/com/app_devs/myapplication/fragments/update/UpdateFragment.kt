package com.app_devs.myapplication.fragments.update

import android.app.AlertDialog
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app_devs.myapplication.R
import com.app_devs.myapplication.model.User
import com.app_devs.myapplication.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

   private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)
        view.et_update_name.setText(args.currentUser.name)
        view.et_update_age.setText(args.currentUser.age.toString())
        view.et_update_status.setText(args.currentUser.vaccinationStatus)
        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        view.btn_update.setOnClickListener{
            updateItem()
        }
        setHasOptionsMenu(true)
        return view

    }
    private fun updateItem()
    {
        val name=et_update_name.text.toString()
        val age=et_update_age.text
        val status=et_update_status.text.toString()
        if(verifyInput(name, age, status)){
            val updatedUser: User = User(args.currentUser.id,name,Integer.parseInt(age.toString()),status)
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"All fields should be filled",Toast.LENGTH_SHORT).show()
        }
    }
    private fun verifyInput(name:String, age: Editable, status:String):Boolean
    {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(age) && TextUtils.isEmpty(status) )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder=AlertDialog.Builder(requireContext())
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Deleted ${args.currentUser.name}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setTitle("Delete ${args.currentUser.name} ?")
        builder.setMessage("Are you sure you want to delete  ${args.currentUser.name} ?")
        builder.create().show()
    }

}