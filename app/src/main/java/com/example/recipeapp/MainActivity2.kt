package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var rvrecipe:RecyclerView
    var userdata:List<RecipeDetails.Datum>?=null
    var displayResponse =ArrayList<ArrayList<String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        rvrecipe=findViewById(R.id.rvrecipe)

        getUserDetails(onResult = {
            userdata = it
            val datumList = userdata
            for (datum in datumList!!) {
                displayResponse+= arrayListOf(arrayListOf("recipe title:${datum.title}\n","recipe author:${datum.author}\n","recipe ingredients:${datum.ingredients}\n","recipe instructions:${datum.instructions}\n"))
                 /* displayResponse.add("${datum.title}")
               displayResponse.add("${datum.author}")
               displayResponse.add("${datum.ingredients}")
               displayResponse.add("${datum.instructions}")*/
            }
            rvrecipe.adapter=myAdapter(displayResponse)
            rvrecipe.layoutManager= LinearLayoutManager(this)
        })

    }
    private fun getUserDetails(onResult: (List<RecipeDetails.Datum>?) -> Unit) {
        val apiInterface = APIClient().getClinet()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getUserDetails()?.enqueue(object : Callback<List<RecipeDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<RecipeDetails.Datum>>,
                    response: Response<List<RecipeDetails.Datum>>
                ) {
                    onResult(response.body())
                }

                override fun onFailure(call: Call<List<RecipeDetails.Datum>>, t: Throwable) {
                    onResult(null)
                }

            })
        }
    }
}