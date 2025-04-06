package com.venki.cryptotracker.util

import android.content.Context
import com.venki.cryptotracker.R
import com.venki.cryptotracker.domain.NetworkError

fun NetworkError.toString(context: Context): String{

    val resId =when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUEST -> R.string.error_too_many_request
        NetworkError.NO_INTERNET -> R.string.no_internet
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.SERIALIZATION -> R.string.error_serial
        NetworkError.UNKOWN -> R.string.unkown
    }

    return  context.getString(resId)
}