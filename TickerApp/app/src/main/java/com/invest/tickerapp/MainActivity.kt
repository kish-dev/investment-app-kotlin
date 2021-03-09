package com.invest.tickerapp

//import com.invest.tickerapp.model.network.NetworkService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.invest.tickerapp.databinding.ActivityMainBinding
import com.invest.tickerapp.view.ConfigureFragment


//Todo to write Search in ConfigureFragment, SearchFragment from StocksList, diffUtil update Recycler

class MainActivity : AppCompatActivity() {

    //TODO right init for chosen
    //TODO init in app or SaveInstanceState (saving List stocks
    //TODO Glide (ImageView)
    //TODO Search in ConfigureFragment, update list
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        val textView = findViewById<TextView>(R.id.text_view)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            textView.text = CompanyLoader.testWork()
//        }
//        val service = NetworkService.getJSONApi()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val result: Quote = service!!.getQuote("AAPL", "c12e84v48v6oi252mgog")
//                launch(Dispatchers.Main) {
//                    textView.text = result.toString()
//                }
//
//            } catch (e: Exception) {
//                Log.e("MainActivity", "${e.message}")
//            }
//        }


//        NetworkService
//            .getJSONApi()
//            ?.getPostWithID(1)
//            ?.enqueue(object : Callback<Post?> {
//                override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
//                    val post = Post(response.body())
//                    textView.append(post.id.toString() + "\n")
//                    textView.append(post.userId.toString() + "\n")
//                    textView.append(post.title + "\n")
//                    textView.append(post.body + "\n")
//                }
//
//                override fun onFailure(call: Call<Post?>, t: Throwable) {
//                    textView.append("Error occurred while getting request!")
//                    t.printStackTrace()
//                }
//            })

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame,
            ConfigureFragment()
        ).commit()
        setContentView(binding.root)
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}