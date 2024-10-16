package org.iesharia.mytabata

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.Composable

class CounterDown(var segundos: Int, var loquehacealhacertick: (Long) -> Unit) {
    var myCounter : CountDownTimer
    var counterState : Boolean = false

    init {
        myCounter = object : CountDownTimer((segundos * 1000L), 1000) {

            override fun onTick(millisUntilFinished: Long) {
                loquehacealhacertick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                counterState = false
            }
        }
    }
}