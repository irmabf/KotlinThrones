package com.example.irmablanco.appofthrones

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_characters.*

/*Observamos que aquí tenemos que heredar de la interfaz definida en CharactersFragment OnItemClickListener
* De este modo estaremos poniendo en contacto la actividad con el fragmento**/
/**
 * Tenemos una clase que se llama characters activity, esa clase hereda de la clase AppCompatActivity,
 * sabemos que hereda porque esta llamando al constructor padre de la superclase
 * Seguido de la coma estamos diciendo que esa actividad tiene las facultades de comportarse como un OnItemClickListener,
 * es decir, PUEDE RECIBIR UN EVENTO DE CLICK DEL FRAGMENTO*/
/**
 * Una vez que tenemos la interfaz OnItemClickListener implementada, podemos hacer un overrida la funcion OnItemClickListener
 * continuando el protocolo, continuamos alli ->*/
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
/*
* La actividad, CharactersActivity va a decidir qué hacer cuando reciba ese click,
* finalmentE: TENEMOS QUE CONECTAR NUESTRO FRAGMENTO CON NUESTRA ACTIVIDAD,
* para ello vamos a characters fragment y alli tendremos que crear una variable la cual nos diga
*esa instancia con la que está conectado el fragmento, el cual va a ser el clicklistener, vamos entonces
* a CharacterFragment y creamos : var clickListener**/
//La clase implementa este metodo de la interfaz OnItemClickListenr

    /**AHORA, UNA VEZ CONECTADA CharactersActivity con CharactersFragment, toca comunicar CharactersDetail con CharactersActivity
     * Para ello, debemos asegurarnos de que en onItemClicked se meustren los detalles de la activididad
     *
     * Para esto, creamos el metodo showDetails (que luego sera renombrado a showFragmentDetails), dentro de showDetails, que antes de ser nombrado a showFragmentes es asi:
     *
     * fun showDetails(characterId: String) {
     *  val intent: Intent = Intent(context, DetailActivity)  ************* Esta linea indica que, de momento, showDetails ira desde una actividad -> el context que es CharactersActivity a otra,  DetailsActivity
     *  intent.putExtra("key_id", characterId)
     *  startActivity(intent)
     * }
     *
     * Una vez definida la funcion showDetails, la ejecutamos en onItemClicked (es la que hemos renombrado a showFragmentDetails)
     *
     * Version incial de showDetails antes de refactorizarla a showFragmentDetails:
     *
     * override fun onItemClicked(character: Character) {
     *  showDetails(character.id)
     * }
     *
     * Tras esto, ya no vamos a mostrar los detalles desde CharactersFragment, si no que lo que vamos a invocar en el fragmento es el onClickListener, con lo que:::::::
     *
     *
     * tenemos que irnos al adaptador en CharactersFragment y modificar la forma de la lambda***********************:
     *  val adapter = CharactersAdapter { item, position -> showDetails(item.id) }
     *adapter
     ***************modificar la lambda a:
     *
     * :::::::::::::en el adaptador vamos a acceder al clicklistener que es la actividad y le vamos a indicar que un item fue clickeado, le vamos a mandar la instancia de ese item
     * ****ojo ahora tene,os que ir a la declaracion de showDetails en Characters activity y hacer el siguiente refactor:
     * ****
     * ****fun showDetails(charactersId: String) {
     *         val intent: Intent = Intent(this, Detail)
     *         DetailActivity::class.javA
     *         startActivity(intent)
     * **** } ------------------------> Es decir, en CharactesActiviyt fun showDetails cambiamos Intent(context, characterId) por (this, characterId) para decirle cual es el contexto
     *
     * En este momento el fragmento ya deberia estar conectado con la actividad
     *
     *
     * ****
     * ****
     * val adapter = CharactersAdapter { item, position -> showDetails() }
     *
     *
     * */

    override fun onItemClicked(character: Character) {

        //Si la vista de detalle está disponible:
       if (isDetailViewAvailable())
       //Enlazamos al fragmento, al que obviamente tenemos que pasar el charcter.id
           showFragmentDetails(character.id)
       //Si la vista no está disponible lanzamos la actividad -> esto ocurrirá cuando no estemos en una tablet
       //También aquí pasamos el character.id
       else
           launchDetailActivity(character.id)
    }

    /*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    Ahora tenemos que implementar las funciones showFragmentDetails y launchDetailActivity,
    estas funciones sustituiran ah showDetails
    *
    *
    * */
    //Al recibir el evento del click en la lista
    //funcion para comprobar si la vista de detalle está disponible
    /**
     * Antes de refactorizar, comprobabamos si la vista estaba disponible de la siguiente forma con una función inline
     *
     * fun isDetailViewAvailable() = findViewById<FrameLayout>(R.id.detailContainer) != null
     *Con ayuda de las kotlin extensions, podemos hacerlo asi:
     * fun isDetailViewAvailable() = detailContainer != null
     *
     * 📌 La funcion isDetailViewAvailable checkea si existe un detailContainer,
     * recordamos que el detailContainer es el id que hemos dado al frameLayout activity_characters
     * version tablet, por lo tanto, si existe detailContainer, significara que estamos en una tablet,
     * y si no existe estamos en un movil.
     *
     * Utilizamos esta función dentro de la fun onItemClicked ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
     *
     *
     *
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * Ahora nos encontramos con que el character.id se lo estamos pasando unicamente
     * a launchDetailActivity, pero showFragmentDetails también lo necesita
     * ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * Pasamos a figurar la lógica para pasar el character.id tambien a showFragmentDetails
     *
     * */
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


