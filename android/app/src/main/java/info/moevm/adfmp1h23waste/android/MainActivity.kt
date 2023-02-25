package info.moevm.adfmp1h23waste.android

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import info.moevm.adfmp1h23waste.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val tabLayout = createTabBar()
        binding.toolbar.addView(tabLayout)
    }

    private fun createTabBar(): TabLayout {
        val tabLayout = TabLayout(this)

        addTabTo(tabLayout, TabName.HOME.tabName)
        addTabTo(tabLayout, TabName.TASKS.tabName)
        addTabTo(tabLayout, TabName.SEARCH.tabName)
        addTabTo(tabLayout, TabName.PROFILE.tabName)
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
                    .navigate(R.id.AuthorizationFragment)
            }
        }
    }

    enum class TabName(val tabName: String) {
        HOME("Домой"),
        TASKS("Задания"),
        SEARCH("Поиск"),
        PROFILE("Профиль")
    }
}
