package com.eit.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eit.myapplication.databinding.ActivityMainBinding
import com.eit.myapplication.model.*
import com.eit.myapplication.viemodel.ProductViewModel
import com.eit.myapplication.view.ProductAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val service: Service by lazy{
        Service.initRetrofit()
    }
    private val repository: Repository by lazy{
        RepositoryImpl(service)
    }
    private val viewModel: ProductViewModel by lazy{
        ViewModelProvider(this,
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(p0: Class<T>): T {
                return ProductViewModel(repository) as T
            }
        })[ProductViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.products.observe(this){
            updateData(it)
        }
        binding.productList.layoutManager = LinearLayoutManager(this)
    }

    fun updateData(data: UIState){
        when (data){
            is UIState.Response -> {
                updateAdapter(data.data)
            }
            is UIState.Error -> {
                showError(data.errorMessage)
            }
        }
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun updateAdapter(data: ProductResponse) {
        binding.productList.adapter = ProductAdapter(data.products)
    }
}