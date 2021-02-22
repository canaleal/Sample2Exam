package com.canaleal.sample2.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.canaleal.sample2.R

/**
 * Assignment1 created by alexc
 * student ID: 991514796
 * on 2021-01-29 */
class AboutFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder
                .setTitle(R.string.app_name)
                .setMessage(R.string.author)
                .setPositiveButton(android.R.string.ok,null)
        return builder.create()
    }
}