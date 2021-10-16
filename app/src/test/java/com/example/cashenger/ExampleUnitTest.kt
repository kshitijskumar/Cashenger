package com.example.cashenger

import android.util.Patterns
import org.junit.Test

import org.junit.Assert.*
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addExpenseCommand_on_correct_asserts_true() {
//        val pattern = "^\\badd\\b(.*?)\\bfor\\b(.*?)\\bin\\b(.*?)$".toRegex(options = setOf(RegexOption.MULTILINE, RegexOption.LITERAL))
        val pattern = Regex(pattern = """^\badd\b(.*?)\bfor\b(.*?)\bin\b(.*?)$""", options = setOf(RegexOption.MULTILINE, RegexOption.LITERAL))
        val command = "addd(200)for(scooty petrol)in(expense)"
//        assertEquals("", pattern.pattern)
        val pat = Pattern.compile("^\\badd\\b(.*?)\\bfor\\b(.*?)\\bin\\b(.*?)\\z")
        assertEquals(true, pat.matcher(command).matches())
    }

    @Test
    fun addExpenseCommand_on_wrong_asserts_false() {
        val pattern = "^\\badd\\b(.*?)\\bfor\\b(.*?)\\bin\\b(.*?)$".toRegex()
        val command = "add(200)for(scooty petrol)in(expense)"
        assertEquals(false, pattern.matches(command))
    }
}