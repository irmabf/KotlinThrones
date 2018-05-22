package com.example.irmablanco.appofthrones

import java.util.*

object CharactersRepo {
    val character: MutableList<Character> = mutableListOf()
    get() {
        if (field.isEmpty())
            field.addAll(dummyCharacters())
        return  field
    }

    private  fun dummyCharacters(): MutableList<Character> {

        return (1..10).map {

            intToCharacter(it)

        }.toMutableList()
    }
    fun findCharacterById(id: String?): Character? {
        return character.find { character  ->
            character.id == id
        }
    }
    private  fun intToCharacter(int: Int): Character {
        return Character(
                name = "Personaje ${int}",
                title = "Título ${int}",
                born = "Naci en ${int}",
                actor = "Actor ${int}",
                quote = "Frase ${int}",
                father = "Padre ${int}",
                mother = "Madre ${int}",
                spouse = "Esposa ${int}",
                house = dummyHouse()
        )

    }

    private  fun dummyHouse(): House {
        val ids = arrayOf("stark", "greyjoy","lannister", "tyrell", "arryn", "baratheon", "tully")
        val randomIdPosition = Random().nextInt(ids.size)

        return House(name = ids[randomIdPosition],
                region = "Region",
                words = "Lema")
    }
}
