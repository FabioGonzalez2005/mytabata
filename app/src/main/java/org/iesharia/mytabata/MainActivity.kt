package org.iesharia.mytabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.iesharia.mytabata.ui.theme.MytabataTheme

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
fun MainMenu(modifier: Modifier = Modifier) {
    var sets by remember { mutableStateOf(3) }
    var exerciseTime by remember { mutableStateOf(30) }
    var restTime by remember { mutableStateOf(15) }

    Spacer(modifier = Modifier.height(16.dp))

    TimeSelector(
        label = "Tiempo de ejercicio (segundos)",
        value = exerciseTime,
        onIncrease = { exerciseTime += 5 },
        onDecrease = { if (exerciseTime > 5) exerciseTime -= 5 }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TimeSelector(
        label = "Tiempo de descanso (segundos)",
        value = restTime,
        onIncrease = { restTime += 5 },
        onDecrease = { if (restTime > 5) restTime -= 5 }
    )

    Spacer(modifier = Modifier.height(32.dp))

    Button(onClick = { }) {
        Text("Iniciar Tabata")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MytabataTheme {
        MainMenu()
    }
}