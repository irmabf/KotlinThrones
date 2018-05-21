package com.example.irmablanco.appofthrones

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_detail.*
//Tenemos que conectar fragment detail con detailfragment, tenemos que sobrescribir primero a onCreateView, e incfar la vista xml fragment_detail*******101
class DetailFragment: Fragment() {
    companion object {
        fun newInstance(id: String): DetailFragment {
            val  instance = DetailFragment()
            val args = Bundle()
            args.putString("key_id", id)
            instance.arguments = args
            return instance
        }
    }
    /**
     * *****101 ojo la vista layoutInflater no va a ser nula as ique se quita el ?
     *<
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    /*
    * Ahora tenemos que trabajar con DetailFragment y DetailActivity -> sabemos que la instanciación de las vistas
    * se realiza en el metodo onViewCreated, que sucede posteriormente a que se ejecute el método onCreateView
    *
    * */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("key_id")
        val character = CharactersRepo.findCharacterById(id)


    }




}