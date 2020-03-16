package com.iboism.gpxrecorder.util

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.FileProvider
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpPost
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by bradpatras on 12/19/17.
 */
class ShareHelper(val context: Context) {
    fun shareFile(gpxSharedFile: File) {
        val contentUri = FileProvider.getUriForFile(context, "com.iboism.gpxrecorder.fileprovider", gpxSharedFile)

        if (contentUri != null) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            shareIntent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/octet-stream", "text/plain"))
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
            shareIntent.type = "application/octet-stream"
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"))
        }
    }

    fun sendFile(gpxFile: File) {

//        val url = "http://7e873089.ngrok.io/savePath/michal/"
////        val file = gpxFile.readText()
////        val request = url+file
////        request.httpPost()

        Fuel.post("http://7e873089.ngrok.io/savePath/michal/")
                .header(Headers.CONTENT_TYPE, "application/xml")
                .body(gpxFile)
                .also { println(it) }
                .response { result -> Log.e("url", result.toString())}

//        val client = OkHttpClient()
//
//        val mediaType = MediaType.parse("application/xml")
//        val body = RequestBody.create(mediaType, gpxFile.readText())
//        val request = Request.Builder()
//                .url("http://7e873089.ngrok.io/savePath/michal")
//                .post(body)
//                .addHeader("Content-Type", "application/xml")
//                .addHeader("User-Agent", "PostmanRuntime/7.20.1")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cache-Control", "no-cache")
//                .addHeader("Postman-Token", "745ba67e-4c1f-4260-a92e-75ea495b09f3,31d85ecc-7d6a-4a8c-9d9f-3c60d9721883")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Content-Length", "117747")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("cache-control", "no-cache")
//                .build()
//
//        val response = client.newCall(request).execute();
//
//        Log.e("url", response.toString())

        //println(response)

//        val reqParam = URLEncoder.encode(gpxFile.readText(), "UTF-8")
//
//        val mURL = URL("http://7e873089.ngrok.io/savePath/michal/lat-lng/lat-lng     $reqParam")
//
//        with(mURL.openConnection() as HttpURLConnection) {
//
//            requestMethod = "POST"
//
//            println("POST? URL : $url")
//            println("POST? hello")
//            println("POST? Response Code : $responseCode")
//
//            BufferedReader(InputStreamReader(inputStream)).use {
//                val response = StringBuffer()
//
//                var inputLine = it.readLine()
//                while (inputLine != null) {
//                    response.append(inputLine)
//                    inputLine = it.readLine()
//                }
//                it.close()
//                println("POST? Response : $response")
//            }
//        }
    }
}