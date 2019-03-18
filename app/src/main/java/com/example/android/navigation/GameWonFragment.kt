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

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        // Set an OnClickListener which will return us to the GameFragment for another match
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        // COMPLETED (01) Add setHasOptionsMenu(true)
        // This allows onCreateOptionsMenu to be called
        setHasOptionsMenu(true)
        return binding.root
    }

    // COMPLETED (02) Create getShareIntent method
    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())

        return ShareCompat.IntentBuilder.from(activity)
                .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
                .setType("text/plain")
                .intent
    }

    // COMPLETED (03) Create shareSuccess method
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    // COMPLETED (04) Override and fill out onCreateOptionsMenu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Inflate the winner_menu ...
        inflater.inflate(R.menu.winner_menu, menu)
        // Check if the activity resolves...
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // ...and set the share menu item to invisible if the activity doesn't
            // resolve
            menu.findItem(R.id.share).isVisible = false
        }
    }

    // COMPLETED (05) Override onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Call the shareSuccess method when the item id matches R.id.share
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
