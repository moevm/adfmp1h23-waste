package info.moevm.adfmp1h23waste.android

import android.annotation.SuppressLint
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

    private fun onClickTabButton(tabButton: TabButton, tabButtonBinding: TabButtonBinding) {
        fillTabButtonsDefault()
        changeImage(tabButtonBinding.tabbuttonvector, tabButton.imageActiveId)
        tabButtonBinding.tabbuttontext.setTextColor(Color.parseColor("#2F80ED"))
        findNavController(R.id.nav_host_fragment_content_main).navigate(tabButton.link)
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
