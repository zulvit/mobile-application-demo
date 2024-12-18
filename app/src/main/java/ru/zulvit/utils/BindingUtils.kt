package ru.zulvit.utils

import android.text.TextUtils

object BindingUtils {
    @JvmStatic
    fun listToString(prefix: String, list: List<String>?): String {
        return if (list.isNullOrEmpty()) "$prefix Unknown"
        else "$prefix${TextUtils.join(", ", list)}"
    }
}
