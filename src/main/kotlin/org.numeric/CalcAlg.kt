package org.numeric

object CalcAlg {
    var lastRes = 0
    fun sumAllDigits(num: String): String {
        var res = 0
        var strRes = ""
        num.forEach {
            val s = it.toString()
            strRes += "$s + "
            res += s.trim().toInt()
        }
        strRes = strRes.substringBeforeLast(" + ")
        strRes += " = $res"
        lastRes = res
        return processResult(strRes)
    }

    fun sumAllDigitsRes(num: Int): String {
        if(num < 0) {
            return num.toString()
        }
        return sumAllDigitsRes(num.toString())
    }

    fun sumAllDigitsRes(num: String): String {
        sumAllDigits(num)
        return lastRes.toString()
    }

    private fun processResult(num: String): String {
        val res = num.substringAfterLast(" = ").toInt()
        if(res > 9) {
            return num + " = " + sumAllDigits(res.toString())
        }
        return num
    }
}