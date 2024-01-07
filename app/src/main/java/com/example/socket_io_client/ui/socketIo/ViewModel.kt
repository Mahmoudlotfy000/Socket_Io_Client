package com.example.socket_io_client.ui.socketIo

import android.util.Log
import io.socket.client.Socket
import io.socket.client.IO
import kotlin.math.log

class ChatClient(private val username: String) {
    private val socket: Socket = IO.socket("https://tracking.dev.mdawm.com")
    fun connect() {
        socket.connect()

        socket.on(Socket.EVENT_CONNECT) {
            println("Connected to server")
            Log.d(" connect to server ","Connected to server");
            socket.emit("chat message", "$username joined the chat")
        }

        socket.on("chat message") { args ->
            val message = args[0] as String
            println("Received message: $message")

        }

        socket.on(Socket.EVENT_DISCONNECT) {
            println("Disconnected from server")
            Log.d(" disconnect","DisConnected to server");
        }

        sendMessage(message = "connect Success")
    }

    private fun sendMessage(message: String) {
        socket.emit("chat message", "$username: $message")
    }

    fun disconnect() {
        socket.disconnect()
        sendMessage(message = "connect stop")
        Log.d(" stop connect","Stop Connected to server");
    }
}

//fun main() {
//    val client = ChatClient("Alice")
//    client.connect()
//
//    Thread.sleep(1000) // Give some time for connection to establish
//
//    client.sendMessage("Hello, everyone!")
//
//    Thread.sleep(3000) // Allow time to receive messages
//
//    client.disconnect()
//}


//import io.socket.client.IO
//import io.socket.client.Socket
//import io.socket.emitter.Emitter
//
//fun main() {
//    val options = IO.Options.builder()
//        .setForceNew(true)
//        .build()
//
//    val socket = IO.socket("http://example.com", options)
//
//    socket.on(Socket.EVENT_CONNECT, Emitter.Listener {
//        println("Connected to server")
//        socket.emit("chat message", "Hello, Socket.IO!")
//    }).on("chat message", Emitter.Listener { args ->
//        val message = args[0] as String
//        println("Received: $message")
//    }).on(Socket.EVENT_DISCONNECT, Emitter.Listener {
//        println("Disconnected from server")
//    })
//
//    socket.connect()
//}