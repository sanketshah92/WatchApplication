package com.sanket.watchapplication.presentation.mainMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
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
        menuItems.add("Action 1")
        menuItems.add("Measure HR")
        menuItems.add("Export HR Data")
        menuItems.add("Action 3")
        menuItems.add("Action 4")
        return menuItems
    }

    private fun prepareAdapter() {
        menuAdapter =
            MainMenuAdapter(getMenuData(), object : MainMenuAdapter.OnMenuItemClickListener {
                override fun onMenuItemSelected(selectedItem: String) {
                    when(selectedItem){
                        "Measure HR"-> {
                            //Navigate to Measure HR
                        }
                        "Export HR Data"->{
                            //Navigate to Export Heart Rate
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