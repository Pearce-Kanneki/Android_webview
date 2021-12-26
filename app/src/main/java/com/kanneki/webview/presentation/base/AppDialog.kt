package com.kanneki.webview.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.kanneki.webview.databinding.DialogMessageBinding

object AppDialog {
    /**
     *  注意: 使用AppDialog時要確定binding是否有設定view，若無設定會造成閃退
     */
    private lateinit var binding: DialogMessageBinding

    private fun setAlertDialog(context: Context): AlertDialog {
        getDialogLayout(context)

        return AlertDialog.Builder(context).setView(binding.root).create()
    }

    /**
     *  離開APP的提示Dialog
     */
    fun exitDialog(context: Context, callback: (Boolean) -> Unit) {
        setAlertDialog(context)
            .apply {
                binding.dialogOk.setOnClickListener {
                    callback(true)
                    dismiss()
                }
                binding.dialogCancel.setOnClickListener {
                    callback(false)
                    dismiss()
                }
            }
            .show()

    }

    /**
     *  一般訊息提示Dialog，無回傳任何訊息
     */
    fun messageDialog(context: Context, message: String?) {
        message?.let { mes ->
            setAlertDialog(context)
                .apply {
                    binding.dialogCancel.visibility = View.GONE
                    binding.dialogMessage.text = mes
                    binding.dialogOk.setOnClickListener { dismiss() }
                }
                .show()
        }
    }

    private fun getDialogLayout(context: Context) {
        binding = DialogMessageBinding.inflate(LayoutInflater.from(context))
    }
}