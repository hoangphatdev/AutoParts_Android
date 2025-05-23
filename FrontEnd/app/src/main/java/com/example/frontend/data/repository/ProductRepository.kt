package com.example.frontend.data.repository

import com.example.frontend.data.model.ProductData
import com.example.frontend.data.remote.ApiResponse
import com.example.frontend.data.remote.ProductApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productApiService: ProductApiService) {

    suspend fun getAllProducts(): ApiResponse<List<ProductData>> {
        return try{
            val response = productApiService.getAllProducts()
            if(response.isSuccessful){
                val list = response.body() ?: emptyList()
                ApiResponse.Success(list)
            }else{
                ApiResponse.Error("Failed to fetch all products", response.code())
            }
        }catch(e: Exception){
            ApiResponse.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getProductById(productId: Long): ApiResponse<ProductData> {
        return try{
            val response = productApiService.getProductById(productId)
            if(response.isSuccessful){
                val product = response.body() ?: ProductData(1L)
                ApiResponse.Success(product)
            }else{
                ApiResponse.Error("Failed to fetch product by id", response.code())
            }
        }catch(e: Exception){
            ApiResponse.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getImageUrls(productId: Long): ApiResponse<List<String>>{
        return try{
            val response = productApiService.getImageUrls(productId)
            if(response.isSuccessful){
                val imageUrls = response.body() ?: emptyList()
                ApiResponse.Success(imageUrls)
            }else{
                ApiResponse.Error("Failed to fetch image urls", response.code());
            }
        }catch(e: Exception){
           ApiResponse.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getImageUrl(productId: Long): ApiResponse<String> {
        return try {
            val response = productApiService.getImageUrl(productId)

            if (response.isSuccessful) {
                val imageUrl = response.body() ?: ""
                ApiResponse.Success(imageUrl)
            } else {
                ApiResponse.Error("Failed to fetch image url", response.code())
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getProductsByCategory(category: String): ApiResponse<List<ProductData>> {
        return try {
            val response = productApiService.getProductsByCategory(category)
            if (response.isSuccessful) {
                ApiResponse.Success(response.body() ?: emptyList())
            } else {
                ApiResponse.Error("get product failed", response.code())
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: "I don't know")
        }
    }

}
