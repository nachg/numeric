package org.numeric

object CalcAlg {

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
        return processResult(strRes)
    }

    private fun processResult(num: String): String {
        val res = num.substringAfterLast(" = ").toInt()
        if(res > 9) {
            return num + " = " + sumAllDigits(res.toString())
        }
        return num
    }
}