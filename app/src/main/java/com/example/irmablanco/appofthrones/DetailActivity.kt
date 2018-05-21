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

        /**
         * Estamos indicando a la actividad a traves de un intent el id del personaje, sin embargo ya no vamos a tener
         * en la actividad de detalle esta interfaz de usuario de DetailActivity, únicamente vamos a tener la del fragmento
         * --->> esto implica que tenemos que comunicar posteriormente
         * esta instancia  del id -------->
         * *********val id = intent.getStringExtra("key_id")******************************
         * ********val character = CharactersRepo.findCharacterById(id)*******************
         * ---------> hasta el fragmento, para que ese fragmento sepa
         * cual es la informacion del personaje que tiene que poblar
         *
         * */
        val id = intent.getStringExtra("key_id")
        val character = CharactersRepo.findCharacterById(id)
        //val button: Button = findViewById(R.id.button)
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
            Toast.makeText(this@DetailActivity, character?.house?.words, Toast.LENGTH_SHORT).show()
        }
    }
}
