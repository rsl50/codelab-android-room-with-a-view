package com.robsonlima.myapplication.repository

import androidx.annotation.WorkerThread
import com.robsonlima.myapplication.model.Word
import com.robsonlima.myapplication.room.WordDAO
import kotlinx.coroutines.flow.Flow

/**
 * This class represents the Repository.
 *
 * Code based on Android Room with a View - Kotlin codelab:
 * https://developer.android.com/codelabs/android-room-with-a-view-kotlin#8
 */
// Declares the DAO as a private property in the constructor. Pass in the DAO instead of the
// whole database, because you only need access to the DAO
class WordRepository(private val wordDAO: WordDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDAO.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work off the main
    // thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDAO.insert(word)
    }
}