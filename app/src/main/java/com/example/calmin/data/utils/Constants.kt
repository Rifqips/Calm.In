package com.example.calmin.data.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import com.example.calmin.R

object Constants {

    fun dialogHistory(
        context: Context,
        score: String,
        description: String,
        alignment: Int = Gravity.CENTER
    ): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.window!!.apply {
            val params: WindowManager.LayoutParams = this.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes.windowAnimations = android.R.transition.fade
            setGravity(Gravity.CENTER)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setContentView(R.layout.custom_dialog_history)
        val tvScore = dialog.findViewById<TextView>(R.id.tv_score)
        when (alignment) {
            Gravity.CENTER -> tvScore.gravity  = Gravity.CENTER_VERTICAL or Gravity.CENTER
            Gravity.START -> tvScore.gravity = Gravity.CENTER_VERTICAL or Gravity.START
            Gravity.END -> tvScore.gravity = Gravity.CENTER_VERTICAL or Gravity.END
        }
        tvScore.text = score
        return dialog
    }

}