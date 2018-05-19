package com.example.irmablanco.appofthrones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_character.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("key_id")
        val character = CharactersRepo.findCharacterById(id)
        val button: Button = findViewById(R.id.button)

       character?.let {
           with(character){
               labelName.text = name
               labelTitle.text = title
               labelActor.text = actor
               labelBorn.text = born
               labelParents.text = "${father} & ${mother}"
               labelQuote.text = quote
               labelSpouse.text = spouse
               button.text = house.name
           }
       }


        button.setOnClickListener{
            Toast.makeText(this@DetailActivity, character?.house?.name, Toast.LENGTH_SHORT).show()
        }
    }
}
