package com.eit.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eit.myapplication.databinding.ProductItemLayoutBinding
import com.eit.myapplication.model.Product

class ProductAdapter(private val dataSet: List<Product>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ProductItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

            fun onBind(dataItem: Product){
                binding.productName.text = dataItem.productName
                Glide.with(binding.root.context)
                    .load(dataItem.productImage)
                    .into(binding.productImage)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}