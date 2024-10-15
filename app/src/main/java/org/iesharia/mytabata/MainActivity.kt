package org.iesharia.mytabata

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.iesharia.mytabata.ui.theme.MytabataTheme

var counterState : Boolean = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MytabataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    var theCounter by remember { mutableStateOf("00") }

    Column {
        Text(
            text = theCounter,
            modifier = modifier
        )
        Button(onClick = {
            if (!counterState) {

            object : CountDownTimer(99000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    theCounter = (millisUntilFinished / 1000).toString()
                }

                override fun onFinish() {
                    counterState = true
                }
            }.start()
            counterState = true
        }}) {


            Text(
                text = "Pulsar"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MytabataTheme {
        Counter()
    }
}