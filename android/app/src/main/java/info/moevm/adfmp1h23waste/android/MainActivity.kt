package info.moevm.adfmp1h23waste.android

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import info.moevm.adfmp1h23waste.android.databinding.ActivityMainBinding
import info.moevm.adfmp1h23waste.android.databinding.TabButtonBinding
import info.moevm.adfmp1h23waste.android.user.TabButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillTabButtonsDefault()

        //val tabLayout = createTabBar()
        //binding.toolbar.addView(tabLayout)
    }

    private fun createTabBar(): TabLayout {
        val tabLayout = TabLayout(this)

        addTabTo(tabLayout, TabName.HOME.tabName)
        addTabTo(tabLayout, TabName.TASKS.tabName)
        addTabTo(tabLayout, TabName.SEARCH.tabName)
        addTabTo(tabLayout, TabName.PROFILE.tabName)
        addTabTo(tabLayout, TabName.LOGIN.tabName)
        val tabListener = TabListener(this)
        tabLayout.addOnTabSelectedListener(tabListener)
        return tabLayout
    }

    private fun addTabTo(tabLayout: TabLayout, name: String) {
        val tab = tabLayout.newTab()
        tab.text = name
        tabLayout.addTab(tab)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    class TabListener(private val activity: Activity) : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: Tab?) {
            // Ignore
        }

        override fun onTabUnselected(tab: Tab?) {
            // Ignore
        }

        override fun onTabSelected(tab: Tab?) {
            if (tab == null) {
                return
            }

            when (tab.text) {
                TabName.HOME.tabName -> activity.findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.FirstFragment)
                TabName.TASKS.tabName -> activity.findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.TasksFragment)
                TabName.SEARCH.tabName -> activity.findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.SearchFragment)
                TabName.PROFILE.tabName -> activity.findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.ProfileFragment)
                TabName.LOGIN.tabName -> activity.findNavController(R.id.nav_host_fragment_content_main)
                    .navigate(R.id.AuthorizationFragment)
            }
        }
    }

    enum class TabName(val tabName: String) {
        HOME("Домой"),
        TASKS("Задания"),
        SEARCH("Поиск"),
        PROFILE("Профиль"),
        LOGIN("Вход"),
    }


    @SuppressLint("ResourceAsColor")
    private fun fillTabButtonDefault(tabButton: TabButton, tabbuttonBinding: TabButtonBinding) {

        val linkTitle: TextView = tabbuttonBinding.tabbuttontext
        linkTitle.text = tabButton.title
        linkTitle.setTextColor(Color.parseColor("#333333"))
        changeImage(tabbuttonBinding.tabbuttonvector, tabButton.imageId)
        tabbuttonBinding.tabbuttonvector.setOnClickListener {
            onClickTabButton(
                tabButton,
                tabbuttonBinding
            )
        }
        tabbuttonBinding.tabbuttontext.setOnClickListener {
            onClickTabButton(
                tabButton,
                tabbuttonBinding
            )
        }

    }

    private fun fillTabButtonsDefault() {
        fillTabButtonDefault(TabButtons.HOME.tabButton, binding.tabbuttonhome)
        fillTabButtonDefault(TabButtons.TASKS.tabButton, binding.tabbuttontasks)
        fillTabButtonDefault(TabButtons.SEARCH.tabButton, binding.tabbuttonsearch)
        fillTabButtonDefault(TabButtons.PROFILE.tabButton, binding.tabbuttonprofile)
        fillTabButtonDefault(TabButtons.LOGIN.tabButton, binding.tabbuttonlogin)
    }

    private fun onClickTabButton(tabButton: TabButton, tabbuttonBinding: TabButtonBinding) {
        fillTabButtonsDefault()
        changeImage(tabbuttonBinding.tabbuttonvector, tabButton.imageActiveId)
        tabbuttonBinding.tabbuttontext.setTextColor(Color.parseColor("#2F80ED"))
        this.findNavController(R.id.nav_host_fragment_content_main).navigate(tabButton.link)
    }

    private fun changeImage(imageView: ImageView, imageId: Int) {
        imageView.setImageResource(imageId)
    }

    enum class TabButtons(val tabButton: TabButton) {
        HOME(
            TabButton(
                R.drawable.tabbuttonhome,
                R.drawable.tabbuttonhomeactive,
                "Домой",
                R.id.FirstFragment
            )
        ),
        TASKS(
            TabButton(
                R.drawable.tabbuttontasks,
                R.drawable.tabbuttontasksactive,
                "Задания",
                R.id.TasksFragment
            )
        ),
        SEARCH(
            TabButton(
                R.drawable.tabbuttonsearch,
                R.drawable.tabbuttonsearchactive,
                "Поиск",
                R.id.SearchFragment
            )
        ),
        PROFILE(
            TabButton(
                R.drawable.tabbuttonprofile,
                R.drawable.tabbuttonprofileactive,
                "Профиль",
                R.id.ProfileFragment
            )
        ),
        LOGIN(
            TabButton(
                R.drawable.tabbuttonlogin,
                R.drawable.tabbuttonloginactive,
                "Вход",
                R.id.AuthorizationFragment
            )
        ),
    }
}
