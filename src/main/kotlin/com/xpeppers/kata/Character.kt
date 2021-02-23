package com.xpeppers.kata

import kotlin.math.min

class Character {
    val isDead
        get() = health == 0
    var health = 1000
        private set

    fun dealDamageTo(target: Character, damage: Int) {
        target.health -= min(damage, target.health)
    }
}