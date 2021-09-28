package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animation.databinding.ActivityMainBinding
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.animation.fragments.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        tabInit()
    }


    private fun tabInit() {
        binding.itemChooser.setSelectedTabIndicatorColor(Color.BLUE)
        binding.itemChooser.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
        binding.itemChooser.tag = ContextCompat.getColorStateList(this, R.color.purple_700)
        val numberOfTabs = 4
        // Set Tabs in the center
        binding.itemChooser.tabGravity = TabLayout.GRAVITY_CENTER
        // Show all Tabs in screen
        binding.itemChooser.tabMode = TabLayout.MODE_FIXED
        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        // Set Tab icons next to the text, instead above the text
        binding.itemChooser.isInlineLabel = true
        createPagerAdapter(numberOfTabs)
        setTab()
    }

    private fun createPagerAdapter(numberOfTabs: Int) {
        // Set the ViewPager Adapter
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        binding.tabsViewpager.adapter = adapter
        // Enable Swipe
        binding.tabsViewpager.isUserInputEnabled = true

    }

    private fun setTab() {
        TabLayoutMediator(binding.itemChooser, binding.tabsViewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Animation"
                        // tab.setIcon(R.drawable.ic_music)
                }
                1 -> {
                    tab.text = "Motion"
                }
                2 -> {
                    tab.text = "Lottie"
                }
                3 -> {
                    tab.text = "Custom"
                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()
    }



// Customise tabs views
//    private fun setCustomTabTitles() {
//        val vg = binding.itemChooser.getChildAt(0) as ViewGroup
//        val tabsCount = vg.childCount
//
//        for (j in 0 until tabsCount) {
//            val vgTab = vg.getChildAt(j) as ViewGroup
//
//            val tabChildCount = vgTab.childCount
//
//            for (i in 0 until tabChildCount) {
//                val tabViewChild = vgTab.getChildAt(i)
//                if (tabViewChild is TextView) {

                    // Change Font and Size
//                    tabViewChild.typeface = Typeface.DEFAULT_BOLD
//                    val font = ResourcesCompat.getFont(this, R.font.myFont)
//                    tabViewChild.typeface = font
//                    tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25f)
//                }
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
    }
}