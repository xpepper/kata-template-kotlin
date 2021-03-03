package com.xpeppers.kata

import kotlin.math.min

class Character(private val level: Int = 1) {
    val isDead
        get() = health == 0

    var health = INITIAL_HEALTH
        private set

    fun dealDamageTo(target: Character, damage: Int) {
        if (target != this) {
            val actualDamage = computeActualDamage(damage, target)
            target.receiveDamage(actualDamage)
        }
    }

    fun heal(healingAmount: Int) {
        if (!isDead)
            restoreHealth(healingAmount)
    }

    private fun computeActualDamage(damage: Int, target: Character): Int {
        return when {
            target.level - level >= 5 -> damage / 2
            level - target.level >= 5 -> damage + damage / 2
            else -> damage
        }
    }

    private fun receiveDamage(damage: Int) {
        health -= min(damage, health)
    }

    private fun restoreHealth(healingAmount: Int) {
        health = min(health + healingAmount, INITIAL_HEALTH)
    }

    companion object {
        private const val INITIAL_HEALTH = 1000
    }
}