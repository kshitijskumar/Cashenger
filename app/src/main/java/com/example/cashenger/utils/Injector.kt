package com.example.cashenger.utils

import android.content.Context
import com.example.cashenger.domain.db.dao.RecordsDao
import com.example.cashenger.domain.db.room.RecordsDb
import com.example.cashenger.domain.records.repository.RecordsRepository
import com.example.cashenger.views.features.chat.CommandCenter

class Injector private constructor() {

    private lateinit var provideContextWhenNeeded: (reason: String) -> Context

    private var commandCenter: CommandCenter? = null
    private var recordsRepo: RecordsRepository? = null

    private var recordsDao: RecordsDao? = null



    fun providesCommandCenter() : CommandCenter {
        return createOrReturnCreated(
            underConsideration = commandCenter,
            ifNotCreated = {
                commandCenter = CommandCenter()
                commandCenter!!
            }
        )
    }

    fun providesRecordsRepository() : RecordsRepository {
        return createOrReturnCreated(
            underConsideration = recordsRepo,
            ifNotCreated = {
                recordsRepo = RecordsRepository()
                recordsRepo!!
            }
        )
    }

    fun providesRecordsDao() : RecordsDao {
        return createOrReturnCreated(
            underConsideration = recordsDao,
            ifNotCreated = {
                recordsDao = RecordsDb.getRecordsDatabase(provideContextWhenNeeded.invoke("need to create db")).getRecordsDao()
                recordsDao!!
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

        fun setupInjector(getContextInNeed: (cause: String) -> Context) {
            val injector = getInjector()
            injector.provideContextWhenNeeded = getContextInNeed
        }
    }
}