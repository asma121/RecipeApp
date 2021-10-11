package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var ettitle:EditText
    lateinit var etname:EditText
    lateinit var etingre:EditText
    lateinit var etinstru:EditText
    lateinit var busave:Button
    lateinit var buview:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ettitle=findViewById(R.id.ettitle)
        etname=findViewById(R.id.etname)
        etingre=findViewById(R.id.etingre)
        etinstru=findViewById(R.id.etinstru)
        busave=findViewById(R.id.busave)
        buview=findViewById(R.id.buview)

        buview.setOnClickListener {
            val intent= Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        busave.setOnClickListener {
            var f = RecipeDetails.Datum(ettitle.text.toString(),etname.text.toString(), etingre.text.toString(),etinstru.text.toString())
            addUserDetails(f, onResult = {
                ettitle.setText("")
                etname.setText("")
                etingre.setText("")
                etinstru.setText("")
            })
        }
    }

    private fun addUserDetails(f :RecipeDetails.Datum ,onResult: () -> Unit){
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addUserDetails(f)?.enqueue(object : Callback<RecipeDetails.Datum?> {
                override fun onResponse(call: Call<RecipeDetails.Datum?>, response: Response<RecipeDetails.Datum?>) {
                    Toast.makeText(applicationContext,"Recipe added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<RecipeDetails.Datum?>, t: Throwable) {
                    Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}