package com.bdscala.hashing

import java.security.MessageDigest

object BdHashingUtils {

  def md5OfString(str: String): String = {
    val md = MessageDigest.getInstance("MD5")
    md.update(str.getBytes)
    val digest = md.digest();
    val hashCode = digest.map("%02X".format(_)).mkString
    hashCode
  }

  def main(args: Array[String]): Unit ={
    println(BdHashingUtils.md5OfString("this is a test string"))
  }

}
