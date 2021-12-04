package com.example.cashenger.utils

import java.util.regex.Pattern

sealed class Commands(val regexPattern: Pattern) {
    object NoValidCommands : Commands(Pattern.compile(""))
    object AddExpenseCommand : Commands(Pattern.compile(CommandsRegex.ADD_AMOUNT_FOR_TITLE_IN_EXPENSE))
    object ShowExpenses : Commands(Pattern.compile("showexpenses"))
    object ShowIncomes : Commands(Pattern.compile("showincomes"))
    object ShowAll : Commands(Pattern.compile("showall"))
    object ShowAllCmds : Commands(Pattern.compile("showallcmds"))
}
