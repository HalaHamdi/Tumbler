package com.example.tumbler.activityandmessages

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tumbler.R
import com.example.tumbler.UserPagesActivity
import com.example.tumbler.databinding.FragmentChat2Binding
import com.example.tumbler.model.entity.chat.Chat_User_Data
import com.google.android.material.bottomnavigation.BottomNavigationView

class Chat2Fragment : Fragment() {
    lateinit var binding: FragmentChat2Binding
    lateinit var rv_showData: RecyclerView
    lateinit var edt_message: EditText
    lateinit var btn_send: Button
    var userList: ArrayList<Chat_User_Data> = ArrayList()
    var myshered: SharedPreferences? = null
    var userName: String? = null
    val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView()
    }
    /**
     * On sendbtn click listener the text in Edit text appears in chat view with the name of the user
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChat2Binding.inflate(inflater, container, false)
        myshered = getActivity()?.getSharedPreferences("myshared", 0)
        userName = myshered?.getString("blog_username", "").toString()
        rv_showData = binding.rvShowData
        edt_message = binding.editMessage
        btn_send = binding.btnSend
        rv_showData.adapter = userRecyclerView
        btn_send.setOnClickListener {
            if (binding.editMessage.text.isNotEmpty()) {
                userList.add(
                    Chat_User_Data(
                        userName.toString(),
                        edt_message.text.toString(),
                        R.drawable.chatvector2
                    )
                )
                userRecyclerView.setList(userList)
                edt_message.setText("")
            }
        }
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.GONE
        navBar.visibility = View.GONE
        return binding.root
    }

    /**
     * Function to make the Bottom Navigation visible
     */
    override fun onDestroy() {
        super.onDestroy()
        val navBar: BottomNavigationView = (requireActivity() as UserPagesActivity).binding.footerNavigation
        val fab = (requireActivity() as UserPagesActivity).binding.createPostButton
        fab.visibility = View.VISIBLE
        navBar.visibility = View.VISIBLE
    }
}
