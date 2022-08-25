package com.example.fragmentex

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentex.databinding.FragmentABinding
class FragmentA :
    Fragment() {
    private var _binding: FragmentABinding? = null
    private val binding get () = _binding!!
    var mainActivity: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(
            inflater,
            container,
            false
        )
        binding . btnShowFragmentB . setOnClickListener {
            mainActivity!!.openFragmentOnFrameLayoutB(
                1
            )
        }
        binding . btnShowFragmentC . setOnClickListener {
            mainActivity!!.openFragmentOnFrameLayoutB(
                2
            )
        }
        return binding.root
    }
}
