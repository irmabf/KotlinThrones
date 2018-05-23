package com.example.irmablanco.appofthrones

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.header_character.*
import kotlinx.android.synthetic.main.data_character.*

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

        character?.let {
            with(character){
                labelName.text = name
                labelTitle.text = title
                labelActor.text = actor
                labelBorn.text = born
                labelParents.text = "${father} & ${mother}"
                labelQuote.text = quote
                labelSpouse.text = spouse
                val overlayColor = House.getOverlayColor(character.house.name)
                //los fragmentos estan ligados a contextos, luego getDrawable(context, colorid
                imgOverlay.background = context?.let { it1 -> ContextCompat.getDrawable(it1, overlayColor) }

                val baseColor = House.getBaseColor(character.house.name)
                btnHouse.backgroundTintList = context?.let { it1 -> ContextCompat.getColorStateList(it1, baseColor) }

                val idDrawable = House.getIcon(character.house.name)
                val drawable = context?.let { it1 -> ContextCompat.getDrawable(it1, idDrawable) }
                btnHouse.setImageDrawable(drawable)
            }
        }
        btnHouse.setOnClickListener{
            Toast.makeText(context, character?.house?.words, Toast.LENGTH_SHORT).show()
        }
    }
}