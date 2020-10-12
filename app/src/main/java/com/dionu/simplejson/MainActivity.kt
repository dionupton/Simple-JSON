package com.dionu.simplejson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager

    lateinit var jsonSportArray: JSONArray
    lateinit var client: OkHttpClient

    val url = "https://www.thesportsdb.com/api/v1/json/1/all_sports.php"
    var sportsArray = ArrayList<Sport>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetch_button.setOnClickListener {
            fetch()
            fetch_button.isClickable = false

        }

    }

    fun setRecyclerView() {

        viewManager = LinearLayoutManager(this)
        viewAdapter = SportAdapter(sportsArray)


        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    private fun fetch() {
        doAsync {
            client = OkHttpClient()
            var request: Request = Request.Builder()
                .url(url)
                .build()

            try {
                val response = client.newCall(request).execute()
                uiThread {
                    toast("Success")
                    parseResult(response.body!!.string())
                }
            } catch (e: java.lang.Exception) {
                Log.e("FETCHDEBUG", "Failed with error : ${e.message}")
                fetch_button.isClickable = true
            }
        }
    }


    private fun parseResult(result: String) {
        try {
            val jsonData = JSONObject(result)
            jsonSportArray = jsonData.getJSONArray("sports")
            for (i in 0 until jsonSportArray.length()) {
                val sport = Sport(
                    getString("idSport", i),
                    getString("strSport", i),
                    getString("strFormat", i),
                    getString("strSportThumb", i),
                    getString("strSportThumbGreen", i),
                    getString("strSportDescription", i)
                )
                Log.d("PARSEDEBUG", "SPORT CREATED : ${sport}")
                sportsArray.add(sport)
            }
            setRecyclerView()

        } catch (e: Exception) {
            Log.e("PARSEDEBUG", "ERROR : ${e.message}")

        }
    }

    private fun getString(s: String, i: Int): String {
        return jsonSportArray.getJSONObject(i).getString(s)
    }


}