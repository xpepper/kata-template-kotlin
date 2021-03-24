package com.xpeppers.kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTest {
    private val character = Character()
    private val target = Character()

    @Test
    fun `Characters can deal damage to Characters`() {
        action { character.dealDamageTo(target, 100) }.decreasesBy(100) { target.health }
    }

    @Test
    fun `when damage received exceeds current Health, Health becomes 0 and the character dies`() {
        character.dealDamageTo(target, target.health + 1)

        assertThat(target.health).isEqualTo(0)
        assertThat(target.isDead).isTrue()
    }

    @Test
    fun `a Character can heal itself`() {
        character.dealDamageTo(target, 100)

        action { target.heal(50) }.increasesBy(50) { target.health }
    }

    @Test
    fun `healing cannot raise health above initial health`() {
        action { target.heal(50) }.doesNotChange { target.health }
    }

    @Test
    fun `dead characters cannot heal themselves`() {
        character.dealDamageTo(target, target.health)

        target.heal(1)

        assertThat(target.isDead).isTrue()
    }

    @Test
    fun `a Character cannot deal damage to itself`() {
        action { character.dealDamageTo(character, 1) }.doesNotChange { character.health }
    }

    @Test
    fun `if the target is 5 or more levels above the attacker, damage is reduced by 50%`() {
        val attacker = Character(1)
        val target = Character(6)

        action { attacker.dealDamageTo(target, 100) }.decreasesBy(50) { target.health }
    }

    @Test
    fun `if the target is 5 or more levels below the attacker, damage is increased by 50%`() {
        val attacker = Character(6)
        val target = Character(1)

        action { attacker.dealDamageTo(target, 100) }.decreasesBy(150) { target.health }
    }

    @Test
    fun `a Character cannot deal damage to an out-of-range target`() {
        val attacker = Character(maxAttackRange = 10)

        action { attacker.dealDamageTo(target, 100, 11) }.doesNotChange { target.health }
    }

    @Test
    fun `Melee characters have an attack range of 2 meters`() {
        val attacker = Character.melee()

        action { attacker.dealDamageTo(target, 100, 3) }.doesNotChange { target.health }

        action { attacker.dealDamageTo(target, 100, 2) }.decreasesBy(100) { target.health }
    }

    @Test
    fun `Ranged characters have an attack range of 20 meters`() {
        val attacker = Character.ranged()

        action { attacker.dealDamageTo(target, 100, 21) }.doesNotChange { target.health }

        action { attacker.dealDamageTo(target, 100, 20) }.decreasesBy(100) { target.health }
    }
}
