package ir.beigirad.customlintsample

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.beigirad.customlintsample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun initVariables() {
        super.initVariables()

        // read input argument
        // instantiate viewModel
    }

    override fun initUI() {
        super.initUI()

        _binding = FragmentFirstBinding.bind(requireView())
        binding.buttonFirst.setOnClickListener {
            Log.v("Nav", "next button clicked")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun initObservers() {
        super.initObservers()

        // viewModel.stateFlow.collect{...}
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}