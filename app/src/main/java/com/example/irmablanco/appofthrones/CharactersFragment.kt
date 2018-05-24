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
            clickListener.onItemClicked(item)
        }
        adapter
    }
    /*Necesitamos implementar una variable que servirá para decir la instancia con la que está conectado el fragmento*/
    //creamos la variable clickListener que va a ser la instancia de OnItemClickListener
    /**
     * Creamos la variable instancia de OnitemClickListener para poder conectar CharactersFragment con CharactersActivity
     * Habría que controllar la nullabilidad diciendo
     * var clickListener: OnItemClickListener= = null, pero utilizamos un lateinit mejor,
     * Con la palabra  reservada lateinit indicamos que esta variable va a ser inicializada en algún punto del tiempo, sin embargo, damos por hecho
     *que la variable no puede ser nula, con el el lateinit, nosotros nos hacemos responsables de implementar que esa inicializacion tardia se ejecute
     * en algun punto deltiempo próximo y que ese punto del tiempo no afecte a un futuro para no tener nullpointer exception.
     * Como en el ciclo de vida el primer método que ese ejecuta es el OnAttach, implementamos en dicho método el  middleware para la inicializacion en el meotdo OnAttach,
     * igualando ahí el clickListener a context, es decir a algo que sea un OnItemClickListener, que  la actividad que implemente dicha interfaz*/
    lateinit  var clickListener: OnItemClickListener

    //Ahora tenemos que sobrescribir el método onAttach, pues es el primero que se ejecuta en el ciclo de vida de un fragmento
    /*Vemos que onAttach viene con un contexto como argumento, ese contexto va a ser la actividad,
    * sabemos que cualquier clase que herede de AppCompatActivity o activity hereda también de Context*/
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Necesitamos saber si se implementa la actividad a la cual nos estamos conectando tiene la capacidad de hacer un click listener,
        //si implementa la interfaz OnItemClickListener
        /**Si el contexto: es decir, la actividad a la cual nos estamos conectando que es CharactersActivity tiene la capacidad de manejar
         * el OnItemClickListener -> es decir, si la actividad a la que nos estamos conectando implementa la interfaz OnItemClickListwrnr,
         * entonces clickLister es igual a esa actividad, de lo contrario, lanzar una excepción.
         * **/
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


    }

    override fun onResume() {
        super.onResume()
        requestCharacters()
    }
    private fun requestCharacters() {
        context?.let {
            CharactersRepo.requestCharacters(it,
                { characters ->
                    adapter.setCharacters(characters)

                },
                {

                })
        }
    }

    //Aquí definimos una especie de protocolo, un comportamiento que una clase puede implementar
    //este comportamiento sera implementado por la clase CharactersActivity
    interface OnItemClickListener {
        fun onItemClicked(character: Character)
    }


}