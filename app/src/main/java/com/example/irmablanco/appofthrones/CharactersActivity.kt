package com.example.irmablanco.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_characters.*
class CharactersActivity: AppCompatActivity(), CharactersFragment.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        //Creo la instancia del fragmento
        val fragment = CharactersFragment()

        if (savedInstanceState == null){
            this.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listContainer, fragment)
                    .commit()
        }

    }
//La clase implementa este metodo de la interfaz OnItemClickListenr
    override fun onItemClicked(character: Character) {
        showDetails(character.id)
    }

    fun showDetails(characterId: String) {
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)
        startActivity(intent)
    }

}