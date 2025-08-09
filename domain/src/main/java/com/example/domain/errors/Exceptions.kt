package com.example.domain.errors

import android.os.Message

class ServerError(message: String) : Exception(message)

class ConnectionError(message: String) : Exception(message)

class NoDataError(message: String) : Exception(message)