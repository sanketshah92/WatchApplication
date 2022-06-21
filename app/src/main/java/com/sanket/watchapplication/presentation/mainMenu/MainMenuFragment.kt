package com.sanket.watchapplication.presentation.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.wear.widget.WearableLinearLayoutManager
import com.sanket.watchapplication.R
import com.sanket.watchapplication.presentation.mainMenu.utils.CustomScrollingLayoutCallback
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment() {
    private lateinit var menuAdapter: MainMenuAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareAdapter()
    }

    private fun getMenuData(): List<String> {
        val menuItems = mutableListOf<String>()
        menuItems.add(resources.getString(R.string.action_measure))
        menuItems.add(resources.getString(R.string.action_export))
        return menuItems
    }

    private fun prepareAdapter() {
        menuAdapter =
            MainMenuAdapter(getMenuData(), object : MainMenuAdapter.OnMenuItemClickListener {
                override fun onMenuItemSelected(selectedItem: String) {
                    when (selectedItem) {
                        resources.getString(R.string.action_measure) -> {
                            findNavController().navigate(R.id.action_mainMenuFragment_to_measureHeartRateFragment)
                        }
                        resources.getString(R.string.action_export) -> {
                            findNavController().navigate(R.id.action_mainMenuFragment_to_exportHeartRateConfirmFragment)
                        }
                    }
                }
            })
        setupMenuView()
    }

    private fun setupMenuView() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(mainMenuRecycler)
        mainMenuRecycler.apply {
            layoutManager = WearableLinearLayoutManager(activity, CustomScrollingLayoutCallback())
            isEdgeItemsCenteringEnabled = true
            adapter = menuAdapter
        }
    }
}