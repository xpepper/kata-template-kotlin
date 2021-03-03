package com.xpeppers.kata

import kotlin.math.min

class Character {
    val isDead
        get() = health == 0

    var health = INITIAL_HEALTH
        private set

    fun dealDamageTo(target: Character, damage: Int) {
        target.health -= min(damage, target.health)
    }

    fun heal(target: Character, healingAmount: Int) {
        if (!target.isDead)
            target.restoreHealth(healingAmount)
    }

    private fun restoreHealth(healingAmount: Int) {
        health = min(health + healingAmount, INITIAL_HEALTH)
    }

    companion object {
        private const val INITIAL_HEALTH = 1000
    }
}