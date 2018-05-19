package com.example.irmablanco.appofthrones

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import  android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CalendarView
import android.widget.TextView

class CharactersAdapter:RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {
    constructor(): super(){
        itemClickListener = null
    }
    constructor(itemClickListener:  ( (Character, Int) -> Unit) ) :super(){
        this.itemClickListener = itemClickListener
    }
    private val items = mutableListOf<Character>()

    //Tenemos que conectar nuestro adaptador con la actividad a través de algún elemento
    //Vamos a añadir una lambda que nos van a pasar en el constructor,
    //Cuando se construya el adaptador, voy  especificar qué hacer si se hizo click a un
    //elemento de la lista
    //Creamos una instancia privada de un val itemClickListener, que sera una lambda
    //La lambda recibe  un argumento del tipo Character, pues en el momento que hacemos click
    //necesitamos especificar a que personaje se le dio click y tambien la posicion, que es un int
    //Especificamos después cual es el tipo de dato que va a devolver
    private val itemClickListener: ((Character, Int) -> Unit)?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        holder.character = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setCharacters(characters: MutableList<Character>){
        items.clear()
        items.addAll(characters)
        notifyDataSetChanged()
    }
    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var character: Character? = null
            set(value) {
                itemView.findViewById<TextView>(R.id.label_name).text = value?.name
                field = value
            }
        init {
            //Cada vez que se le de click se va a ejecutar el código que está dentro de esta lambda
                itemView.setOnClickListener{
                    character?.let {
                        itemClickListener?.invoke(character as Character, adapterPosition)
                }
            }
        }
    }
}
