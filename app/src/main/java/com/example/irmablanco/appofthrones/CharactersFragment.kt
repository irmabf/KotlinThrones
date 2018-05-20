package com.example.irmablanco.appofthrones

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


}