package com.example.cashenger.utils

import com.example.cashenger.views.features.chat.CommandCenter

class Injector private constructor() {

    private var commandCenter: CommandCenter? = null

    fun providesCommandCenter() : CommandCenter {
        return createOrReturnCreated(
            underConsideration = commandCenter,
            ifNotCreated = {
                commandCenter = CommandCenter()
                commandCenter!!
            }
        )
    }

    private fun <T: Any?>createOrReturnCreated(
        underConsideration: T?,
        ifNotCreated: () -> T
    ) : T {
        return underConsideration ?: ifNotCreated.invoke()
    }

    companion object {
        private var INSTANCE: Injector? = null
        fun getInjector() : Injector {
            if (INSTANCE == null) {
                INSTANCE = Injector()
            }
            return INSTANCE!!
        }
    }
}