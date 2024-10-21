package org.iesharia.mytabata

import android.os.CountDownTimer
import android.util.Log

class CounterDown(var segundos: Int, var loquehacealhacertick: (Long) -> Unit) {
    private var counterState: Boolean = false
    private var tiempoRestante: Long = segundos * 1000L

    private var myCounter: CountDownTimer? = null

    fun crearCounter(tiempo: Long) {
        myCounter = object : CountDownTimer(tiempo, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tiempoRestante = millisUntilFinished
                if (counterState) loquehacealhacertick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                counterState = false
                Log.i("dam2", "mensajito")
            }
        }
    }

    fun start() {
        counterState = true
        crearCounter(tiempoRestante)
        myCounter?.start()
    }

    fun cancel() {
        counterState = false
        myCounter?.cancel()
    }
}
