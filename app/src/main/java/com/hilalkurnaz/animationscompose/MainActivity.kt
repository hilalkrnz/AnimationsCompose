package com.hilalkurnaz.animationscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hilalkurnaz.animationscompose.ui.theme.AnimationsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationsComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        var isvisible by remember {
                            mutableStateOf(false)
                        }
                        var isRound by remember {
                            mutableStateOf(false)
                        }
                        Button(onClick = {
                            isvisible = !isvisible
                            isRound = !isRound
                        }) {
                            Text(text = "Toggle")
                        }
//                        val transition = updateTransition(
//                            targetState = isRound,
//                            label = null
//                        )
//                        val borderRadius by transition.animateInt(
//                            transitionSpec = { tween(2000) },
//                            label = "borderRadius",
//                            targetValueByState = { isRound ->
//                                if (isRound) 100 else 0
//                            }
//                        )
//                        val borderRadius2 by animateIntAsState(
//                            targetValue = if (isRound) 40 else 20,
//                            animationSpec = spring(
//                                dampingRatio = Spring.DampingRatioHighBouncy,
//                                stiffness = Spring.StiffnessLow
//                            )
////                            animationSpec = tween(
////                                durationMillis = 100
////                            )
//                        )
//                        val color by transition.animateColor(
//                            transitionSpec = { tween(1000) },
//                            label = "color",
//                            targetValueByState = { isRound ->
//                                if (isRound) Color.Cyan else Color.Blue
//                            }
//                        )

//                        val transition2 = rememberInfiniteTransition(label = "")
//                        val color2 by transition2.animateColor(
//                            initialValue = Color.Blue,
//                            targetValue = Color.Cyan,
//                            animationSpec = infiniteRepeatable(
//                                animation = tween(2000),
//                                repeatMode = RepeatMode.Reverse
//                            ), label = ""
//                        )
//                        Box(
//                            modifier = Modifier
//                                .size(200.dp)
//                                .clip(RoundedCornerShape(borderRadius))
//                                .background(color)
//                        )
//                        Box(
//                            modifier = Modifier
//                                .size(200.dp)
//                                .background(color2)
//                        )
//                        AnimatedVisibility(
//                            visible = isvisible,
//                            enter = slideInHorizontally() + fadeIn(),
//                            modifier = Modifier.fillMaxWidth().weight(1f)
//                        ) {
//                            Box(modifier = Modifier.background(Color.Red))
//                        }

                        AnimatedContent(
                            targetState = isvisible,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            content = { visible ->
                                if (visible) {
                                    Box(Modifier.background(Color.Green))
                                } else {
                                    Box(Modifier.background(Color.Cyan))
                                }
                            },
                            transitionSpec = {
                                //fadeIn() togetherWith fadeOut()
                                slideInHorizontally(
                                    initialOffsetX = {
                                        //-it
                                        if (isvisible) it else -it
                                    }
                                ) togetherWith slideOutHorizontally(
                                    targetOffsetX = {
                                        //it
                                        if (isvisible) -it else it
                                    }
                                )
                            }, label = ""
                        )
                    }
                }
            }
        }
    }
}