package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
//import com.example.android.guesstheword.databinding.GameFragmentBinding


private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}

class GameViewModel :ViewModel() {
  private val _word = MutableLiveData<String>()
val word :LiveData<String> get() = _word
    // The current score
 private  val _score = MutableLiveData<Int>()
   val score : LiveData<Int>
    get()=_score
    var gameFinishedFlag = MutableLiveData<Boolean>()


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

init {
       gameFinishedFlag.value = false
       resetList()
       nextWord()
       _score.value =0
       _word.value = ""
Log.i("GameViewModel", "GameViewModel created")
}

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }
    /**
     * Resets the list of words and randomizes the order
     */
     fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
     fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
//
           gameFinishedFlag.value = true

        } else {
            _word.value = wordList.removeAt(0)
        }
//        updateWordText()
//        updateScoreText()
    }


    /** Methods for buttons presses **/

     fun onSkip() {
        _score.value = (score.value)?.minus(-1)
        nextWord()
    }

    fun onCorrect() {
        _score.value =(score.value)?.plus(1)
        nextWord()
    }
fun finishGameComplete (){
    gameFinishedFlag.value = false
}

    }




