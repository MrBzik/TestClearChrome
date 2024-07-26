package com.solid.testclearchrome

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.solid.testclearchrome.ui.theme.TestClearChromeTheme
import com.topjohnwu.superuser.Shell
import java.util.logging.Logger


const val cmd = "pm clear com.android.chrome"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestClearChromeTheme {

                val runner = remember {
                    SuCommandsRunner()
                }



                Buttons(
                    modifier = Modifier.fillMaxSize(),
                    runtimeExec = runner::runtimeExec,
                    processBuilderExec = runner::processBuilderExec,
                    topWuExec = runner::topWuExec
                )
            }
        }
    }
}

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    runtimeExec: () -> Unit,
    processBuilderExec: () -> Unit,
    topWuExec: () -> Unit

){

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = runtimeExec) {
            Text(text = "Runtime")
        }

        Button(onClick = processBuilderExec) {
            Text(text = "ProcessBuilder")
        }

        Button(onClick = topWuExec) {
            Text(text = "TopWu")
        }
    }
}



class SuCommandsRunner(){
    fun runtimeExec(){

        Runtime.getRuntime().exec("su -c $cmd")

    }

    fun processBuilderExec(){

        ProcessBuilder("su", "-c", cmd).start()

    }

    fun topWuExec(){

        Shell.cmd(cmd).exec()
    }
}

