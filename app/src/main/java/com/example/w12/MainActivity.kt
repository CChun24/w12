package com.example.w12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet = findViewById<Button>(R.id.btnGetAll)
        btnGet.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)

            val objRequest = JsonArrayRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getAll",
                null,
                {
                    response -> try {

                    var nameList: StringBuffer = StringBuffer()
                    for(i in 0 until response.length()) {
                        val objStudent: JSONObject = response.getJSONObject(i)
                        nameList.append(objStudent.getString("name"))
                    }
                    findViewById<TextView>(R.id.lblResult).text = nameList
                } catch(e: JSONException) {
                        findViewById<TextView>(R.id.lblResult).text = e.message
                    }
                },
                {
                    error -> findViewById<TextView>(R.id.lblResult).text = error.message
                }
            )

            rq?.add(objRequest)
        }

        val btnSearch = findViewById<Button>(R.id.btnSearch)
        btnSearch.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)
            val param = findViewById<TextView>(R.id.tfID).text.toString()
            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/getById?id=$param",
                null,
                {
                    response -> try {
                    findViewById<TextView>(R.id.tfName).text = response.getString("name")
                    findViewById<TextView>(R.id.tfProgramme).text = response.getString("programme")
                } catch(e: JSONException) {
                    findViewById<TextView>(R.id.lblResult).text = e.message
                }
                },
                {
                    error -> findViewById<TextView>(R.id.lblResult).text = error.message
                }
            )

            rq?.add(objRequest)
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val rq: RequestQueue = Volley.newRequestQueue(this)
            val id = findViewById<TextView>(R.id.tfID).text.toString()
            val name = findViewById<TextView>(R.id.tfName).text.toString()
            val programme = findViewById<TextView>(R.id.tfProgramme).text.toString()
            val objRequest = JsonObjectRequest(
                Request.Method.GET,
                "http://demo.onmyfinger.com/home/Add?id=$id&name=$name&programme=$programme",
                null,
                {
                    response -> try {
                    findViewById<TextView>(R.id.lblResult).text = response.toString()
                } catch(e: JSONException) {
                    findViewById<TextView>(R.id.lblResult).text = e.message
                }
                },
                {
                        error -> findViewById<TextView>(R.id.lblResult).text = error.message
                }
            )

            rq?.add(objRequest)
        }
    }
}