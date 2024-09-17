package ir.beigirad.customlintsample

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.beigirad.customlintsample.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : BaseFragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun initUI() {
        super.initUI()
        _binding = FragmentSecondBinding.bind(requireView())
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}