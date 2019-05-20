package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore :Int) : ViewModel(){
    var myFinalScore = finalScore
    init {
Log.i("ScoreViewModel" , "finalScore $finalScore")
    }
}