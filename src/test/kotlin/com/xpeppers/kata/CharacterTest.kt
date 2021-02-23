package com.xpeppers.kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CharacterTest {
    @Test
    fun `Characters can deal damage to Characters`() {
        val character = Character()
        val target = Character()
        val initialTargetHealth = target.health

        character.dealDamageTo(target, 100)

        assertThat(target.health).isEqualTo(initialTargetHealth - 100)
    }

    @Test
    fun `when damage received exceeds current Health, Health becomes 0 and the character dies`() {
        val character = Character()
        val target = Character()

        character.dealDamageTo(target, target.health + 1)

        assertThat(target.health).isEqualTo(0)
        assertThat(target.isDead).isTrue()
    }
}
