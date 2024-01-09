package com.example.socket_io_client.ui.socketIo

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket

object SocketIOManager {


    private var socket: Socket? = null

    fun connect() {
        try {
            val options = IO.Options()
            options.reconnection = true
            options.timeout = 5000

            socket = IO.socket("https://tracking.dev.mdawm.com/ws", options)

            socket?.on(Socket.EVENT_CONNECT) {
                // Handle connection success
                println("Socket connected")
                Log.d("connect to server", "Connected to server")
            }?.on(Socket.EVENT_DISCONNECT) {
                // Handle disconnection
                println("Socket disconnected")
                Log.d("stop connect", "Stop Connected to server")
            }?.on(Socket.EVENT_RECONNECTING) {
                // Handle reconnection
                println("Socket Reconnecting")
                Log.d("Reconnect", "Reconnecting to server")
            }?.on(Socket.EVENT_CONNECT_ERROR) {
                // Handle connection error
                println("Socket connection error")
                Log.d("Error Connect", "Error connecting to server")
            }

            socket?.connect()
            Log.d(" Success ", "Connected to server Success ")

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d(" Error", e.toString())

        }
    }

    fun disconnect() {
        socket?.disconnect()
        Log.d(" Disconnect", "DisConnected to server")
    }

    fun sendMessage(message: String) {
        socket?.emit("chat message", message)
    }


}


//object SocketIOManager {
//    private var socket: Socket? = null
//
//    init {
//        connect()
//    }
//
//    private fun createSocket(): Socket? {
//        try {
//            val options = IO.Options()
//            options.reconnection = true
//            options.timeout = 10000
//
//            return IO.socket("https://tracking.dev.mdawm.com/ws", options)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.d("Error", e.toString())
//        }
//        return null
//    }
//
//
//    private fun setupSocketEvents() {
//        socket?.on(Socket.EVENT_CONNECT) {
//            println("Socket connected")
//            Log.d("connect to server", "Connected to server")
//        }?.on(Socket.EVENT_DISCONNECT) {
//            println("Socket disconnected")
//            Log.d("stop connect", "Disconnected from server")
//        }?.on(Socket.EVENT_RECONNECTING) {
//            println("Socket Reconnecting")
//            Log.d("Reconnect", "Reconnecting to server")
//        }?.on(Socket.EVENT_CONNECT_ERROR) { args ->
//            val errorMessage = if (args.isNotEmpty()) args[0] as? String else "Unknown error"
//            println("Socket connection error: $errorMessage")
//            Log.d("Error Connect", "Error connecting to server: $errorMessage")
//        }?.on(Socket.EVENT_CONNECT_TIMEOUT) {
//            println("Socket connection timeout")
//            Log.d("Error Connect", "Connection timeout")
//        }?.on(Socket.EVENT_ERROR) { args ->
//            val error = if (args.isNotEmpty()) args[0] as? Exception else null
//            println("Socket general error: $error")
//            Log.d("Error Connect", "General error: $error")
//        }
//    }
//
//    fun connect() {
//        socket = createSocket()
//        setupSocketEvents()
//        socket?.connect()
//        Log.d("Success", "Connected to server Success")
//    }
//
//    fun disconnect() {
//        socket?.disconnect()
//        Log.d("Disconnect", "Disconnected from server")
//    }
//
//    fun sendMessage(message: String) {
//        socket?.emit("chat message", message)
//    }
//}