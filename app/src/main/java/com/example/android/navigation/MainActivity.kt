/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // COMPLETED (05) Add private lateinit var drawerLayout
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // COMPLETED (06) Initialize drawerLayout var from binding
         drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        // COMPLETED (07) Add the DrawerLayout as the third parameter to setupActionBarWithNavController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        // COMPLETED (08) Hook the navigation UI up to the navigation view. (navView)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        // COMPLETED (09) Replace navController.navigateUp with NavigationUI.navigateUp with drawerLayout param
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
