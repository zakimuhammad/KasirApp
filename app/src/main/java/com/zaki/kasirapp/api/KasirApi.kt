package com.zaki.kasirapp.api

import com.zaki.kasirapp.model.Detail
import com.zaki.kasirapp.model.Kasir
import com.zaki.kasirapp.model.Penjualan
import retrofit2.Call
import retrofit2.http.*

interface KasirApi {
    @GET("produk")
    fun getProduk(): Call<List<Kasir>>

    @GET("penjualan/")
    @Headers("x-key: bd0b3ae6651538fac2515baafc9326c5")
    fun getAllPenjualan(): Call<List<Penjualan>>

    @GET("penjualan/detail")
    @Headers("x-key: bd0b3ae6651538fac2515baafc9326c5")
    fun getDetail(@Query("id") id: Int) : Call<List<Detail>>
}