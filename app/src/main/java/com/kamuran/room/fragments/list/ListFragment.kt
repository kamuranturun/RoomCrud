package com.kamuran.room.fragments.list


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamuran.room.R
import com.kamuran.room.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var mUserViewMolder: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(com.kamuran.room.R.layout.fragment_list, container, false)

        //recylerView
        val adapter= ListAdapter()
        val recyclerView= view.recyclerView
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //userViewModel
        mUserViewMolder= ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewMolder.readAllData.observe(viewLifecycleOwner, Observer{ user->
            adapter.setData(user)
        })

        view.findViewById<View>(com.kamuran.room.R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(com.kamuran.room.R.id.action_listFragment_to_addFragment)
        }
        //add menu
        setHasOptionsMenu(true)

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
        deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("evet"){
                _,_->mUserViewMolder.deleteAllUsers()
            Toast.makeText(requireContext(),"succesfully removed everythings",
                Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("hayır"){
                _,_->}
        builder.setTitle("Delete everythings ")
        builder.setMessage("Are you want to delete everythings?")
        builder.create().show()
    }
}