package com.vicky7230.okcredit_problem.ui.home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.vicky7230.okcredit_problem.R
import com.vicky7230.okcredit_problem.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber
import javax.inject.Inject


class HomeActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var homeViewModel: HomeViewModel
    lateinit var sources: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        init()
    }

    private fun init() {

        setSupportActionBar(toolbar)

        showSourcesDialog()

    }

    private fun showSourcesDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select sources")
        val choices = arrayOf(
            "arts",
            "automobiles",
            "books",
            "business",
            "fashion",
            "food",
            "health",
            "home",
            "insider",
            "magazine",
            "movies",
            "nyregion",
            "obituaries",
            "opinion",
            "politics",
            "realestate",
            "science",
            "sports",
            "sundayreview",
            "technology",
            "theater",
            "t-magazine",
            "travel",
            "upshot",
            "us",
            "world"
        )
        val checkedSources = arrayListOf<String>()
        builder.setMultiChoiceItems(choices, null) { dialog, which, isChecked ->
            if (isChecked)
                checkedSources.add(choices[which])
            else
                checkedSources.remove(choices[which])
        }

        builder.setPositiveButton("OK", null)
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()

        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

        positiveButton.setOnClickListener {
            if (checkedSources.size < 3) {
                showToast("Please select at least 3")
                return@setOnClickListener
            }

            if (checkedSources.size > 5) {
                showToast("Max 5 can be selected")
                return@setOnClickListener
            }

            Timber.e(checkedSources.toString())
            dialog.dismiss()
            sources = checkedSources
            addMenuToBottomTabs()

        }

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun addMenuToBottomTabs() {
        for (i in sources.indices) {
            when (sources[i]) {
                "arts" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_art)
                }
                "automobiles" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_automobile)
                }
                "books" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_book_black_24dp)
                }
                "business" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_business)
                }
                "fashion" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_fashion)
                }
                "food" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_food)
                }
                "health" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_health)
                }
                "home" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_home)
                }
                "insider" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_insider)
                }
                "magazine" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_magzine)
                }
                "movies" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_movie)
                }
                "nyregion" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_nyc)
                }
                "obituaries" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_iobituaries)
                }
                "opinion" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_opinion)
                }
                "politics" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_pollitics)
                }
                "realestate" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_realestate)
                }
                "science" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_science)
                }
                "sports" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_sports)
                }
                "sundayreview" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_sunday)
                }
                "technology" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_tech)
                }
                "theater" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_theater)
                }
                "t-magazine" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_magzine)
                }
                "travel" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_travel)
                }
                "upshot" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_up)
                }
                "us" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_us)
                }
                "world" -> {
                    bottom_navigation.menu.add(Menu.NONE, i, Menu.NONE, sources[i])
                        .setIcon(R.drawable.ic_earth)
                }
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            view_pager.setCurrentItem(item.itemId, true)
            false
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation.menu.getItem(position).isChecked = true
            }
        })

        view_pager.adapter = PagerAdapter(
            sources,
            supportFragmentManager
        )
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}
