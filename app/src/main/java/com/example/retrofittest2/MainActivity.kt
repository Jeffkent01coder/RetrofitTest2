package com.example.retrofittest2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest2.adapter.MyAdapter
import com.example.retrofittest2.databinding.ActivityMainBinding
import com.example.retrofittest2.model.Post
import com.example.retrofittest2.repository.Repository
import com.example.retrofittest2.viewModel.MainViewModel
import com.example.retrofittest2.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getCustomPosts(2, "id", "desc")

        val myPost = Post("AndyJeff", 2, "Jeff", 2)
        viewModel.pushPost(myPost)

        viewModel.pushPost2(2, 2, "Jeff", "THEE CODER")

        //observe the data
        viewModel.myCustomPosts.observe(this) { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    myAdapter.setData(it)
                }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }

        }
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getPost("2344555")
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.headers().toString())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()

            }

        }
    }
    private fun setUpRecyclerview(){
        binding.rvView.adapter = myAdapter
        binding.rvView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

}