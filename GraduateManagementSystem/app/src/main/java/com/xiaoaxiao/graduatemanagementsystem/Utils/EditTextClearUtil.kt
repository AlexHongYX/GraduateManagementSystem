package com.xiaoaxiao.graduatemanagementsystem.Utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView

class EditTextClearUtil {
    companion object {
        /**
         * add clear icon listener->username&password common
         */
        fun addClearListener(editText: EditText, clearIcon: ImageView) {

            // 记录当前editText内容的长度，用于显示隐藏clear icon
            var editTextLength = 0

            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    // 根据输入内容长度是否大于0，显示clear按钮
                    if (s.isNotEmpty()) {
                        clearIcon.visibility = View.VISIBLE
                    } else {
                        clearIcon.visibility = View.INVISIBLE
                    }
                    editTextLength = s.length
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            // 一旦editText失去焦点，就隐藏clear icon
            editText.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    clearIcon.visibility = View.INVISIBLE
                } else {
                    if (editTextLength > 0) {
                        clearIcon.visibility = View.VISIBLE
                    }
                }
            }

            clearIcon.setOnClickListener {
                editText.setText("")
            }
        }
    }
}