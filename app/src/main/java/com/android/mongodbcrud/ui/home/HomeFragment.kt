package com.android.mongodbcrud.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mongodbcrud.BasicInfoAdapter
import com.android.mongodbcrud.R
import com.android.mongodbcrud.databinding.FragmentHomeBinding
import com.android.mongodbcrud.model.BasicInfo
import com.android.mongodbcrud.ui.CreateUpdate.CUFragment
import com.android.mongodbcrud.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.realm.kotlin.types.RealmInstant
import org.mongodb.kbson.ObjectId

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only non-null between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel : MainViewModel
    private var adapter = BasicInfoAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.btnInsert.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.MainFrameLayout, CUFragment())
                addToBackStack(null)
                commit()
            }
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteAllBasicInfo()
        }

        binding.btnFetch.setOnClickListener {
            viewModel.getData()
            binding.rvDataList.adapter = adapter
            binding.rvDataList.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.data.observe(viewLifecycleOwner){data ->
            data?.let{
                adapter.updateData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}