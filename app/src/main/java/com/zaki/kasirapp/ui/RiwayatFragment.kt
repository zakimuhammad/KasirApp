package com.zaki.kasirapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.kasirapp.R
import com.zaki.kasirapp.adapter.ListAdapter
import com.zaki.kasirapp.api.RetrofitInstance
import com.zaki.kasirapp.model.Penjualan
import kotlinx.android.synthetic.main.fragment_kasir.*
import kotlinx.android.synthetic.main.fragment_riwayat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RiwayatFragment : Fragment() {

    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter()

        pbListPenjualan.visibility = View.VISIBLE
        RetrofitInstance().services.getAllPenjualan().enqueue(object : Callback<List<Penjualan>>{
            override fun onResponse(call: Call<List<Penjualan>>, response: Response<List<Penjualan>>) {
                pbListPenjualan.visibility = View.GONE
                response.body()?.let { generateDataList(it) }
            }

            override fun onFailure(call: Call<List<Penjualan>>, t: Throwable) {
                pbListPenjualan.visibility = View.GONE
                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        })

        listAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("riwayat", it)
            }

            findNavController().navigate(
                    R.id.action_riwayatFragment_to_detailFragment,
                    bundle
            )
        }
    }

    private fun generateDataList(data: List<Penjualan>) {
        listAdapter.differ.submitList(data)
        rvListPenjualan.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}