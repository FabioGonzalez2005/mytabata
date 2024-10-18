package org.iesharia.mytabata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.iesharia.mytabata.ui.theme.MytabataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MytabataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainMenu(
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
    var mostrarPantalla by remember { mutableStateOf(true) }
    var tiempoRestante by remember { mutableStateOf(exerciseTime.toLong()) }
    var isCounting by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf<CounterDown?>(null) }
    var isResting by remember { mutableStateOf(false) }

    if (mostrarPantalla) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeSelector(
                label = "Sets",
                value = sets,
                onIncrease = { sets++ },
                onDecrease = { if (sets > 1) sets-- }
            )

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
                onDecrease = { if (restTime > 4) restTime -= 5 }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    mostrarPantalla = false
                    tiempoRestante = exerciseTime.toLong()
                    counter = CounterDown(exerciseTime) { remainingTime ->
                        tiempoRestante = remainingTime
                    }
                    counter?.start()
                    isCounting = true
                    isResting = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Iniciar tabata",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    } else if (!isResting) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFF00E676)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sets: $sets",
                fontSize = 30.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${tiempoRestante}",
                fontSize = 80.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "WORK",
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                Button(
                    onClick = {
                        if (isCounting) {
                            counter?.cancel()
                            isCounting = false
                        } else {
                            counter?.start()
                            isCounting = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (isCounting) "Pausar" else "Reanudar",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        counter?.cancel()
                        tiempoRestante = exerciseTime.toLong()
                        counter = CounterDown(exerciseTime) { remainingTime ->
                            tiempoRestante = remainingTime
                        }
                        counter?.start()
                        isCounting = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Reiniciar",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            LaunchedEffect(tiempoRestante) {
                if (tiempoRestante <= 0) {
                    isCounting = false
                    counter?.cancel()
                    sets--
                    if (sets > 0) {
                        isResting = true
                        tiempoRestante = restTime.toLong()
                        counter = CounterDown(restTime) { remainingTime ->
                            tiempoRestante = remainingTime
                        }
                        counter?.start()
                    } else {
                        mostrarPantalla = true
                    }
                }
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(0.dp)
                .background(Color(0xFF2196F3)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sets: $sets",
                fontSize = 30.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${tiempoRestante}",
                fontSize = 80.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "REST",
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                Button(
                    onClick = {
                        if (isCounting) {
                            counter?.cancel()
                            isCounting = false
                        } else {
                            counter?.start()
                            isCounting = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (isCounting) "Pausar" else "Reanudar",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        counter?.cancel()
                        tiempoRestante = restTime.toLong()
                        counter = CounterDown(restTime) { remainingTime ->
                            tiempoRestante = remainingTime
                        }
                        counter?.start()
                        isCounting = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Reiniciar",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            LaunchedEffect(tiempoRestante) {
                if (tiempoRestante <= 0) {
                    isResting = false
                    tiempoRestante = exerciseTime.toLong()
                    counter = CounterDown(exerciseTime) { remainingTime ->
                        tiempoRestante = remainingTime
                    }
                    counter?.start()
                }
            }
        }
    }
}

@Composable
fun TimeSelector(
    label: String,
    value: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label)
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onDecrease,
                modifier = Modifier
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "-",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(70.dp))

            Text(
                text = value.toString(),
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(70.dp))

            Button(
                onClick = onIncrease,
                modifier = Modifier
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}