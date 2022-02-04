package com.arazo.crypto.utils

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by arazo on 2022-02-04.
 */
object ChCrypto {
	@JvmStatic
	fun aesEncrypt(v: String) = AES256.encrypt(v)
	@JvmStatic
	fun aesDecrypt(v: String) = AES256.decrypt(v)
}

private object AES256 {
	const val secretKey = ""
	const val iv = ""

	private fun cipher(opmode: Int): Cipher {
		if (secretKey.length != 32) throw RuntimeException("SecretKey length is not 32 chars")
		val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
		val sk = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
		val iv = IvParameterSpec(iv.toByteArray(Charsets.UTF_8))
		c.init(opmode, sk, iv)
		return c
	}

	fun encrypt(str: String): String {
		val encrypted = cipher(Cipher.ENCRYPT_MODE).doFinal(str.toByteArray(Charsets.UTF_8))
		return String(Base64.encode(encrypted, Base64.DEFAULT))
	}

	fun decrypt(str: String): String {
		val byteStr = Base64.decode(str.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
		return String(cipher(Cipher.DECRYPT_MODE).doFinal(byteStr))
	}
}