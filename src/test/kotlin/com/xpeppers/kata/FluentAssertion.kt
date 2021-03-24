package com.xpeppers.kata

import org.assertj.core.api.Assertions.assertThat

fun action(action: () -> Unit) = FluentAssertion(action)

class FluentAssertion(private val action: () -> Unit) {
    fun increasesBy(amount: Int, subject: () -> Int) {
        val amountBefore = subject()
        action()
        val amountAfter = subject()
        assertThat(amountAfter).isEqualTo(amountBefore + amount)
    }

    fun decreasesBy(amount: Int, subject: () -> Int) = increasesBy(-amount, subject)

    fun doesNotChange(subject: () -> Int) = increasesBy(0, subject)
}