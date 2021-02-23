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
}
