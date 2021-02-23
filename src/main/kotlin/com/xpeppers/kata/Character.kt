package com.xpeppers.kata

class Character {
    var health = 1000
        private set

    fun dealDamageTo(target: Character, damage: Int) {
        target.health -= damage
    }
}