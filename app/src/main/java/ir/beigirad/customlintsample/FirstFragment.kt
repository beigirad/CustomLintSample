package ir.beigirad.customlintsample

import android.util.Log
import android.annotation.SuppressLint
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.beigirad.customlintsample.databinding.FragmentFirstBinding
import java.io.File // Unused import
import kotlin.random.Random

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

        // lint warning
        binding.tvTitle.setOnTouchListener { v, event ->
            false
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

    // Missing Permission for android.permission.POST_NOTIFICATIONS
    @SuppressLint("MissingPermission")
    private fun showNotification() {
        with(NotificationManagerCompat.from(requireContext())) {
            // ...
            notify(Random.nextInt(), NotificationCompat.Builder(requireContext(), "id").build())
        }
    }
}