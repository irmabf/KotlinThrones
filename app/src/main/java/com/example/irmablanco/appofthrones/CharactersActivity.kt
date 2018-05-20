package com.example.irmablanco.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_characters.*

class CharactersActivity: AppCompatActivity(), CharactersFragment.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)
        //Creo la instancia del fragmento
        val fragment = CharactersFragment()

        if (savedInstanceState == null)
            this.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.listContainer, fragment)
                    .commit()
    }


//La clase implementa este metodo de la interfaz OnItemClickListenr
    override fun onItemClicked(character: Character) {
        //Si la vista de detalle está disponible:
       if (isDetailViewAvailable())
       //Para poder mostrar los fragmentos de ese detalle tenemos que pasar el id del character
           showFragmentDetails(character.id)
       else
           launchDetailActivity(character.id)
    }
    //funcion para comprobar si la vista de detalle está disponible
    private fun isDetailViewAvailable() = detailContainer != null

    private fun showFragmentDetails(characterId: String) {

        val detailFragment = DetailFragment.newInstance(characterId)

        supportFragmentManager
                .beginTransaction()
                //Ponemos el id del contenedor y el fragmento que vamos a pasar, que es el fragmento de detalle
                .replace(R.id.detailContainer, detailFragment)
                //recordamos que hay que commitear las transacciones
                .commit()

    }
    private fun launchDetailActivity(characterId: String) {
        val intent: Intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_id", characterId)
        startActivity(intent)
    }

}


