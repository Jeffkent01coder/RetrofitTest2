package com.example.retrofittest2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retrofittest2.databinding.ActivityMainBinding
import com.example.retrofittest2.repository.Repository
import com.example.retrofittest2.viewModel.MainViewModel
import com.example.retrofittest2.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getPost()


        binding.button.setOnClickListener {
            val myNumber = binding.numberEditText.text.toString()
            viewModel.getCustomPosts(Integer.parseInt(myNumber))

            //observe mutable data
            viewModel.myCustomPosts.observe(this) { response ->
                if (response.isSuccessful) {
                    binding.textView.text = response.body().toString()
                    response.body()?.forEach {
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)

                    }
                } else {
                    binding.textView.text = response.code().toString()
                }
            }
        }

    }
}