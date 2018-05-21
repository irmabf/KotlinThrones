package com.example.irmablanco.appofthrones

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//Tenemos que conectar fragment detail con detailfragment, tenemos que sobrecargar primero a onCreateView, inclar la vista xml
class DetailFragment: Fragment() {

    companion object {
        fun newInstance(id: String): DetailFragment{
            val  instance = DetailFragment()

            val args = Bundle()
            args.putString("key_id", id)
            return instance
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("key_id")
    }
}