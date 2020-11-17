package com.ros.letscookapp.Home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ros.letscookapp.API.ApiClient
import com.ros.letscookapp.Meals.MealsActivity
import com.ros.letscookapp.SharedPreferences.Constants
import com.ros.letscookapp.SharedPreferences.SharedPref
import com.ros.letscookapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rv: RecyclerView
    private lateinit var viewModel: HomeViewModel
    private lateinit var sv: SearchView
    private lateinit var sharedPref: SharedPref


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        sharedPref = SharedPref(requireContext())
        val service = ApiClient.getApiClient(requireContext())?.create(HomeApiService::class.java)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        if (service != null) {
            viewModel.setService(service)
        }
        sv = binding.searchview
        rv = binding.recyclerview
        rv.adapter = CategoryAdapter(arrayListOf(), object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                sharedPref.putString(Constants.PREF_CATEGORY, category.name)
                startActivity(Intent(requireContext(), MealsActivity::class.java))
            }

        })
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.callApi()
        subscribeLiveData()

        val id: Int =
                sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null)
        val textView = sv.findViewById<TextView>(id)

        val id_icon: Int =
                sv.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null)

        textView.setTextColor(Color.BLACK)

        textView.setHintTextColor(Color.GRAY)

        val searchHintIcon: ImageView = sv.findViewById(id_icon) as ImageView
        searchHintIcon.setImageResource(android.R.drawable.ic_menu_search)

        return binding.root
    }

    private fun subscribeLiveData() {
        viewModel.isResponseAllMeals.observe(viewLifecycleOwner, Observer {
            (binding.recyclerview.adapter as CategoryAdapter).addList(it)
            sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    var recentSearches: ArrayList<CategoryModel> = ArrayList()
                    for (data in it) {
                        if (data.name.contains(query)) {
                            recentSearches.add(data)
                        }
                    }
                    (binding.recyclerview.adapter as CategoryAdapter).addList(recentSearches)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    var recentSearches: ArrayList<CategoryModel> = ArrayList()
                    for (data in it) {
                        if (data.name.contains(newText)) {
                            recentSearches.add(data)
                        }
                    }
                    (binding.recyclerview.adapter as CategoryAdapter).addList(recentSearches)
                    return false
                }

            })
        })
    }

}