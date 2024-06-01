package fr.epf.min1.speedycart.data

import android.util.Log
import android.view.View

fun View.click(action: (View) -> Unit){
    Log.d("BUTTON", "click !!")
    this.setOnClickListener(action)
}
