package com.kamuran.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kamuran.room.R
import com.kamuran.room.model.User
import com.kamuran.room.viewModel.UserViewModel


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        view.findViewById<View>(com.kamuran.room.R.id.btnAdd).setOnClickListener {

            mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {
       val frst=   view?.findViewById<EditText>(R.id.firstName_et)
       val last=   view?.findViewById<EditText>(R.id.addLastName_et)
       val aget=   view?.findViewById<EditText>(R.id.addAge_et)

        val firstName= frst?.text.toString()
        val lastName= last?.text.toString()
        val age= aget?.text

        if(inputCheck(firstName,lastName,age)){

            val user= User(0,firstName,lastName,Integer.parseInt((age.toString())))

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"succesfuly added",Toast.LENGTH_LONG).show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"please fill out all fields",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName:String, lastName:String, age: Editable?):Boolean{

            return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) &&  age!!.isEmpty())
        }

    }

