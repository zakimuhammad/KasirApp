package com.zaki.kasirapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaki.kasirapp.adapter.DetailAdapter
import com.zaki.kasirapp.api.RetrofitInstance
import com.zaki.kasirapp.databinding.FragmentDetailBinding
import com.zaki.kasirapp.model.Detail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    private lateinit var detailAdapter: DetailAdapter
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val riwayat = args.riwayat

        binding.pbDetail.visibility = View.VISIBLE
        RetrofitInstance().services.getDetail(riwayat.id).enqueue(object : Callback<List<Detail>> {
            override fun onResponse(call: Call<List<Detail>>, response: Response<List<Detail>>) {
                binding.pbDetail.visibility = View.GONE
                response.body()?.let { generateDataList(it) }
            }

            override fun onFailure(call: Call<List<Detail>>, t: Throwable) {
                binding.pbDetail.visibility = View.GONE
                Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateDataList(data: List<Detail>) {
        detailAdapter = DetailAdapter()
        detailAdapter.differ.submitList(data)
        binding.rvKasir.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}