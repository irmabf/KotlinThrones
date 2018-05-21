package com.example.irmablanco.appofthrones

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_detail.*

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

        if (savedInstanceState == null){
            val fragment = DetailFragment.newInstance(id)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit()
        }
    }
}
