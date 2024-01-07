package com.example.socket_io_client.ui.socketIo

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.socket_io_client.ui.theme.Socket_Io_ClientTheme

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    val socketClient = ChatClient(username = "Ali")

    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp)
    ) {
        Text(
            text = "Hello !",
            modifier = modifier
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(

            onClick = {
                socketClient.connect()
            },
            modifier = Modifier.padding(all = Dp(10F)),
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
            shape = MaterialTheme.shapes.medium,
        )
        {
            Text(text = "Connect", color = Color.White)
        }

        Button(

            onClick = {
                socketClient.disconnect()
            },
            modifier = Modifier.padding(all = Dp(10F)),
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Red)),
            shape = MaterialTheme.shapes.medium,
        )
        {
            Text(text = "DisConnect", color = Color.White)
        }


    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Socket_Io_ClientTheme {
        Greeting()
    }
}