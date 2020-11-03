package com.example.usuariosapi

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getUserById(view: View){//view->nombre View->clase(int, string...)
        //var userId=GetUserId.text.toString()//var is mutable
        //val url="https://localhost:44391/api/contacts"//val is static
        val url="http://192.168.1.67:99/api/contacts"//val is static
        MyAsyncTask().execute(url)
    }

    inner class MyAsyncTask: AsyncTask<String,String,String>(){
        override fun doInBackground(vararg params: String?): String {
            try{
                var url=URL(params[0])
                val urlConnect=url.openConnection() as HttpURLConnection//trata de abrir conexión url y crea un objeto url connection
                urlConnect.connectTimeout=7000

                var inString = ConvertStreamToString(urlConnect.inputStream)

                publishProgress(inString)
            } catch (ex:Exception){
                print(ex)
            }
            return " "
        }
        override fun onProgressUpdate(vararg  values: String?){
            try {
                var array = JSONArray(values[0])
                val data = array.getJSONObject("")
                val Id=data.get("Id").toString()
                val Name = data.get("Name").toString()
                val LastName=data.get("LastName").toString()
                var userInfo = Id+ " "+ Name + " " + LastName
                tvUserInfo.text=userInfo
            } catch (ex:Exception){
                println(ex)
            }
        }
    }

    fun ConvertStreamToString(inputStream:InputStream):String{
        val bufferedReader=BufferedReader(InputStreamReader(inputStream))
        var line:String
        var allString:String=""

        try{
            do{
                line=bufferedReader.readLine()
                if(line !=null){
                    allString+=line
                }
            }while (line!=null)
        } catch (ex:Exception){
            println(ex)
        }
        return allString
    }
}