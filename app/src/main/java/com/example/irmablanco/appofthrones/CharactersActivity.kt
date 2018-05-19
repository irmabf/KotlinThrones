package com.example.irmablanco.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button

class CharactersActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        val list: RecyclerView = findViewById(R.id.list)
        val adapter = CharactersAdapter() { item, position ->
            showDetails()
        }

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        val characters: MutableList<Character> = CharactersRepo.character
        adapter.setCharacters(characters)
    }
    fun showDetails() {
        val intent: Intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }
}