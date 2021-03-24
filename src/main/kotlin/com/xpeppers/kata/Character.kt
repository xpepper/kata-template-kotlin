package com.xpeppers.kata

import kotlin.math.min

class Character(private val level: Int = 1, private val maxAttackRange: Int = 0) {
    val isDead
        get() = health == 0

    var health = INITIAL_HEALTH
        private set

    fun dealDamageTo(target: Character, damage: Int, distanceFromTarget: Int = 0) {
        if (target != this && isTargetInRange(distanceFromTarget)) {
            val actualDamage = computeActualDamage(damage, target)
            target.receiveDamage(actualDamage)
        }
    }

    private fun isTargetInRange(distanceFromTarget: Int) = distanceFromTarget <= maxAttackRange

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
        fun melee(level: Int = 1): Character = Character(level, maxAttackRange = 2)
        fun ranged(level: Int = 1): Character = Character(level, maxAttackRange = 20)

        private const val INITIAL_HEALTH = 1000
    }
}