package com.ros.letscookapp.SearchByName

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.API.ApiClient
import com.ros.letscookapp.Details.DetailsActivity
import com.ros.letscookapp.Meals.MealsAdapter
import com.ros.letscookapp.Meals.MealsModel
import com.ros.letscookapp.R
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import com.ros.letscookapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var sharedPref: SharedPref
    private lateinit var viewModel: SearchViewModel
    private lateinit var rv: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        sharedPref = SharedPref(requireContext())

        val service = ApiClient.getApiClient(requireContext())?.create(SearchApiService::class.java)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.setSharedPref(sharedPref)
        if (service != null) {
            viewModel.setService(service)
        }
        binding.btnSearch.setOnClickListener {
            sharedPref.putString(Constants.PREF_SEARCH, binding.etSearch.text.toString())
            viewModel.callApi()
        }
        rv = binding.rvSearch
        rv.adapter = MealsAdapter(arrayListOf(), object : MealsAdapter.OnAdapterListenerMeals {
            override fun onClick(meals: MealsModel) {
                sharedPref.putString(Constants.PREF_ID_MEAL, meals.id)
                startActivity(Intent(requireContext(), DetailsActivity::class.java))
            }

        })
        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        subscribeLiveData()
        return binding.root
    }

    private fun subscribeLiveData() {
        viewModel.isResponseSearch.observe(viewLifecycleOwner, Observer {
            (binding.rvSearch.adapter as MealsAdapter).addList(it)

        })

    }

}