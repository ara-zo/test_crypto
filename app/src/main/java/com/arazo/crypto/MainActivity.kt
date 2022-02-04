package com.arazo.crypto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.arazo.crypto.utils.ChCrypto
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		btn_encrypt.setOnClickListener {
			tv_decrypt.text = ""

			val text = et_test.text
			val encryptText = ChCrypto.aesEncrypt(text.toString())
			Log.d(TAG, "text: $text")
			Log.d(TAG, "Encrypt: $encryptText")

			tv_encrypt.text = encryptText
		}

		btn_decrypt.setOnClickListener {
			val text = tv_encrypt.text
			val decryptText = ChCrypto.aesDecrypt(text.toString())
			Log.d(TAG, "Decrypt: $decryptText")

			tv_decrypt.text = decryptText
		}
	}

	companion object {
		const val TAG = "MainActivity"
	}

}