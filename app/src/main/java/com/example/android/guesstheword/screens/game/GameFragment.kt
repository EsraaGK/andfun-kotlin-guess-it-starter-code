/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {
    private lateinit var gameViewModel: GameViewModel
//    // The current word
//    private var word = ""
//
//    // The current score
//    private var score = 0
//
//    // The list of words - the front of the list is the next word to guess
//    private lateinit var wordList: MutableList<String>

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("GameViewModel", "ViewModelProviders.of")
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        binding.gameViewModel = gameViewModel
        binding.setLifecycleOwner(this)
//       gameViewModel.resetList()
//        gameViewModel.nextWord()

        //   binding.correctButton.setOnClickListener {
        //  gameViewModel.onCorrect()
//            updateWordText()
//            updateScoreText()
        //  }
        //  binding.skipButton.setOnClickListener {
        //   gameViewModel.onSkip()
//            updateWordText()
//            updateScoreText()
        // }
//   //     updateScoreText()
//        updateWordText()
//        gameViewModel.word.observe(this, Observer { newValue -> binding.wordText.text = newValue })
//        gameViewModel.score.observe(this, Observer { newValue -> binding.scoreText.text = newValue.toString() })
        gameViewModel.gameFinishedFlag.observe(this, Observer { flag ->
            if (flag)
                gameFinished()
        })
        return binding.root

    }

//    /**
//     * Resets the list of words and randomizes the order
//     */
//    private fun resetList() {
//        wordList = mutableListOf(
//                "queen",
//                "hospital",
//                 "basketball",
//                "cat",
//                "change",
//                "snail",
//                "soup",
//                "calendar",
//                "sad",
//                "desk",
//                "guitar",
//                "home",
//                "railway",
//                "zebra",
//                "jelly",
//                "car",
//                "crow",
//                "trade",
//                "bag",
//                "roll",
//                "bubble"
//        )
//        wordList.shuffle()
//    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        gameViewModel.finishGameComplete()

        val action = GameFragmentDirections.actionGameToScore(
                gameViewModel.score.value ?: 0)

        findNavController(this).navigate(action)

    }


    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

//    /**
//     * Moves to the next word in the list
//     */
//    private fun nextWord() {
//        //Select and remove a word from the list
//        if (wordList.isEmpty()) {
//            gameFinished()
//        } else {
//            word = wordList.removeAt(0)
//        }
//        updateWordText()
//        updateScoreText()
//    }

//    /** Methods for buttons presses **/
//
//    private fun onSkip() {
//        score--
//        nextWord()
//    }
//
//    private fun onCorrect() {
//        score++
//        nextWord()
//    }

    /** Methods for updating the UI **/

//    private fun updateWordText() {
//        binding.wordText.text = gameViewModel.word
//
//    }
//
//    private fun updateScoreText() {
//        binding.scoreText.text = gameViewModel.score.toString()
//    }
}
