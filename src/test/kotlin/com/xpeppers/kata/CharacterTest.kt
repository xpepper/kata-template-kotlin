package com.xpeppers.kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTest {
    private val character = Character()
    private val target = Character()

    @Test
    fun `Characters can deal damage to Characters`() {
        val initialTargetHealth = target.health

        character.dealDamageTo(target, 100)

        assertThat(target.health).isEqualTo(initialTargetHealth - 100)
    }

    @Test
    fun `when damage received exceeds current Health, Health becomes 0 and the character dies`() {
        character.dealDamageTo(target, target.health + 1)

        assertThat(target.health).isEqualTo(0)
        assertThat(target.isDead).isTrue()
    }

    @Test
    fun `a Character can heal a Character`() {
        character.dealDamageTo(target, 100)
        val targetHealthBefore = target.health

        character.heal(target, 50)

        assertThat(target.health).isEqualTo(targetHealthBefore + 50)
    }

    @Test
    fun `healing cannot raise health above initial health`() {
        val initialTargetHealth = target.health

        character.heal(target, 50)

        assertThat(target.health).isEqualTo(initialTargetHealth)
    }

    @Test
    fun `dead characters cannot be healed`() {
        character.dealDamageTo(target, target.health)

        character.heal(target, 1)

        assertThat(target.isDead).isTrue()
    }
}
