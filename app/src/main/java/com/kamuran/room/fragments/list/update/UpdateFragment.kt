package com.kamuran.room.fragments.list.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kamuran.room.R
import com.kamuran.room.model.User
import com.kamuran.room.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel:UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        view.updatefirstName_et.setText(args.currentUser.firstName)
        view.updatelastName_et.setText(args.currentUser.lastName)
        view.updateage_et.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }

        //Add menu

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName= updatefirstName_et.text.toString()
        val lastName= updatelastName_et.text.toString()
        val age= Integer.parseInt(updateage_et.text.toString())

        if(inputCheck(firstName,lastName,updateage_et.text)){
            //usr Object
            val updateUser= User(args.currentUser.id,firstName,lastName,age)
            //update currentuser
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(),"updated Succesfully!",Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"please fiil out all fields!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName:String, lastName:String, age: Editable?):Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) &&  age!!.isEmpty())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("evet"){
            _,_->mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"succesfully removed: ${args.currentUser.firstName}",
            Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("hayır"){
            _,_->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}