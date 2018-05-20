package com.example.irmablanco.appofthrones

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CharactersFragment: Fragment() {
    val list: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager( context)
        list
    }
    val adapter: CharactersAdapter by lazy {
        val adapter = CharactersAdapter { item, position ->
            showDetails(item.id)
        }
        adapter
    }
    /*Necesitamos implementar una variable que servirá para decir la instancia con la que está conectado el fragmento*/
    //creamos la variable clickListener que va a ser la instancia de OnItemClickListener
    lateinit  var clickListener: OnItemClickListener

    //Ahora tenemos que sobrescribir el método onAttach, pues es el primero que se ejecuta en el ciclo de vida de un fragmento
    /*Vemos que onAttach viene con un contexto como argumento, ese contexto va a ser la actividad,
    * sabemos que cualquier clase que herede de AppCompatActivity o activity hereda también de Context*/
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Necesitamos saber si se implementa la actividad a la cual nos estamos conectando tiene la capacidad de hacer un click listener,
        //si implementa la interfaz OnItemClickListener
        if (context is OnItemClickListener)
             clickListener = context
        else
            throw IllegalArgumentException("Attached activity doesn't implement CharacterFragment.OnItemClickListener")

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.adapter = adapter
        val characters: MutableList<Character> = CharactersRepo.character
        adapter.setCharacters(characters)
    }

    fun showDetails(characterId: String) {
        val intent: Intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)
        startActivity(intent)
    }
    //Aquí definimos una especie de protocolo, un comportamiento que una clase puede implementar
    //este comportamiento sera implementado por la clase CharactersActivity
    interface OnItemClickListener {
        fun onItemClicked(character: Character)
    }


}