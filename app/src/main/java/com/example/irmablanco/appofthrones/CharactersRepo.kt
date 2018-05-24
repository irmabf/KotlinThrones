package com.example.irmablanco.appofthrones

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import java.util.concurrent.ConcurrentHashMap

const val URL_CHARACTERS = "http://5b05b9e08be5840014ce4660.mockapi.io/characters"

object CharactersRepo {

    private var characters: MutableList<Character> = mutableListOf()

    fun requestCharacters(context: Context,
                          success: ((MutableList<Character>) -> Unit),
                          error: (() -> Unit)
                          ) {
        if (characters.isEmpty()) {
            val request = JsonArrayRequest(Request.Method.GET, URL_CHARACTERS, null, {
                response ->
                    characters = parseCharacters(response)
                    success.invoke(characters)
            }, { _ ->
                    error.invoke()
            })
            Volley.newRequestQueue(context)
                    .add(request)
        }else {
            success.invoke(characters)
        }

    }

    //Esta funcion parsea el jsonArray devuelvo por parseCharacter y retorna una lista mutable de personajes
    private fun parseCharacters(jsonArray: JSONArray): MutableList<Character>{
        //No podemos usar map porque la clase jsonarray que tiene json por defecto no tiene implementado el metodo map
        val characters = mutableListOf<Character>()
        for (index in 0..(jsonArray.length() - 1)){
            //los jsonArray son arreglos de jsonObjects, por lo que queremos recuperar el jsonObject
            val character = parseCharacter(jsonArray.getJSONObject(index))
            characters.add(character)
        }
        return characters
    }

    private fun parseCharacter(jsonCharacter: JSONObject) : Character {
        //Esta funcion nos devuelve un jsonArray como instancia de la clase Character
        //Constructor de la clase personaje, vamos a empezar a construir nuestro personaje
        return Character(
                jsonCharacter.getString("id"),
                jsonCharacter.getString("name"),
                jsonCharacter.getString("born"),
                jsonCharacter.getString("title"),
                jsonCharacter.getString("actor"),
                jsonCharacter.getString("quote"),
                jsonCharacter.getString("father"),
                jsonCharacter.getString("mother"),
                jsonCharacter.getString("spouse"),
                parseHouse(jsonCharacter.getJSONObject("house"))
        )
    }

    private  fun parseHouse(jsonHouse: JSONObject) : House{
        //Constructor de la clase house
        return House(
                jsonHouse.getString("name"),
                jsonHouse.getString("region"),
                jsonHouse.getString("words")
        )
    }

    fun findCharacterById(id: String?): Character? {
        return characters.find { character  ->
            character.id == id
        }
    }


}
