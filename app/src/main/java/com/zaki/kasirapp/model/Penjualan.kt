package com.zaki.kasirapp.model

import java.io.Serializable

data class Penjualan(
        val id: Int,
        val tanggal: String?,
        val harga: String?,
        val created_at: String?,
        val created_by: Int?,
        val updated_at: String?,
        val updated_by: Int?,
        val deleted_at: String?,
        val deleted_by: Int?
) : Serializable