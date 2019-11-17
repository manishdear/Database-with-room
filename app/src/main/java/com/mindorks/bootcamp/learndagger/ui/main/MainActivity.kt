package com.mindorks.bootcamp.learndagger.ui.main

import android.os.Bundle
import android.widget.TextView

import com.mindorks.bootcamp.learndagger.MyApplication
import com.mindorks.bootcamp.learndagger.R
import com.mindorks.bootcamp.learndagger.di.component.DaggerActivityComponent
import com.mindorks.bootcamp.learndagger.di.module.ActivityModule
import com.mindorks.bootcamp.learndagger.ui.home.HomeFragment

import javax.inject.Inject

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        getDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvData = findViewById<TextView>(R.id.tv_message)
        val tvData2 = findViewById<TextView>(R.id.tv_message_2)

        viewModel.users.observe(this, Observer {
            tvData.text = it.toString()
        })

        viewModel.addresses.observe(this, Observer {
            tvData2.text = it.toString()
        })

        addHomeFragment()
    }

    private fun addHomeFragment() {
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
                    .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllAddresses()
        viewModel.getAllUsers()

    }

    override fun onStop() {
        super.onStop()
        viewModel.deleteAddress()
    }

    private fun getDependencies() {
        DaggerActivityComponent
                .builder()
                .applicationComponent((application as MyApplication).applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}
