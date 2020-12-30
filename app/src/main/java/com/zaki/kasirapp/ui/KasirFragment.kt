package com.zaki.kasirapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.kasirapp.R
import com.zaki.kasirapp.adapter.KasirAdapter
import com.zaki.kasirapp.api.RetrofitInstance
import com.zaki.kasirapp.model.Kasir
import kotlinx.android.synthetic.main.fragment_kasir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KasirFragment : Fragment() {

    private lateinit var kasirAdapter: KasirAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kasir, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pbKasir.visibility = View.VISIBLE
        RetrofitInstance().services.getProduk().enqueue(object : Callback<List<Kasir>> {
            override fun onResponse(call: Call<List<Kasir>>, response: Response<List<Kasir>>) {
                pbKasir.visibility = View.GONE
                response.body()?.let { generateDataList(it) }
            }

            override fun onFailure(call: Call<List<Kasir>>, t: Throwable) {
                pbKasir.visibility = View.GONE
                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun generateDataList(data: List<Kasir>) {
        kasirAdapter = KasirAdapter()
        kasirAdapter.differ.submitList(data)
        rvKasir.apply {
            adapter = kasirAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}