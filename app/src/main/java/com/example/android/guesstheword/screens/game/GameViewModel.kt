package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object{
        private const val DONE = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 10000L
    }

    private val timer:CountDownTimer
    // The list of words - the front of the list is the next word to guess
    lateinit private var wordList: MutableList<String>

    // The current score
    private var _score = MutableLiveData<Int>()

    // backing property
    val score:LiveData<Int>
        get() = _score

    // The current word
    var word = MutableLiveData<String>()

    private var finished = MutableLiveData<Boolean>()

    // backing property
    val onFinished:LiveData<Boolean>
        get() = finished

    var timeTillFinish = MutableLiveData<String>()

    init{
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onFinish() {
                finished.value = true
            }

            override fun onTick(timeLeft: Long) {
                timeTillFinish.value = DateUtils.formatElapsedTime(timeLeft/1000)
            }
        }
        timer.start()
        finished.value = false
        _score.value=0
        word.value=""

        resetList()
        nextWord()
    }

    private fun resetList(){
        wordList= mutableListOf(
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
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }

        word.value = wordList.removeAt(0)
    }
    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = _score.value?.dec()
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.inc()
        nextWord()
    }

    fun completedGame(){
        finished.value = false
    }

}