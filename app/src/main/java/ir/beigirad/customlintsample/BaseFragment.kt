package ir.beigirad.customlintsample

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes contentLayoutId: Int = 0
) : Fragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        initVariables(savedInstanceState)
        initVariables()
        super.onCreate(savedInstanceState)
        initializedVariables()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // view.layoutDirection = View.LAYOUT_DIRECTION_RTL
        initUI(savedInstanceState)
        initUI()
        initializedUI()
        initObservers()
    }

    protected open fun initVariables(savedInstanceState: Bundle?) {
    }

    protected open fun initVariables() {
    }

    protected open fun initializedVariables() {
    }

    protected open fun initUI(savedInstanceState: Bundle?) {
    }

    protected open fun initUI() {
    }

    protected open fun initializedUI() {
    }

    protected open fun initObservers() {
    }
}