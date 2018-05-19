package com.example.irmablanco.appofthrones

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
    fun findCharacterById(id: String): Character? {
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
                house = House(
                        name = "Casa ${int}",
                        region = "Region ${int}",
                        words = "Lema ${int}"
                )
        )

    }
}
