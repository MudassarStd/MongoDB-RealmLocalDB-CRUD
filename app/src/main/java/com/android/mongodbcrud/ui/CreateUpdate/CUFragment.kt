package com.android.mongodbcrud.ui.CreateUpdate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.android.mongodbcrud.R
import com.android.mongodbcrud.databinding.FragmentCUBinding
import com.android.mongodbcrud.databinding.FragmentHomeBinding
import com.android.mongodbcrud.model.BasicInfo
import com.android.mongodbcrud.viewmodel.MainViewModel


class CUFragment : Fragment() {

    private lateinit var viewModel : MainViewModel

    private var _binding: FragmentCUBinding? = null
    // This property is only non-null between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCUBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnAddRecord.setOnClickListener {
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()

            val basicInfo = BasicInfo().apply {
                this.name = name
                this.age = age
            }
            viewModel.insertBasicInfo(basicInfo)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}