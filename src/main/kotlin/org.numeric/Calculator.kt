package org.numeric

import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import kotlin.js.Date
import kotlin.math.abs

object Calculator {
    fun render() {
        buildTitle()
        buildForm()
        buildResult()
        buildGoroscope()
        buildLifeCycle()
        buildPersonYearAndMoths()
        buildNumericYantra()
    }

    private val container by lazy { document.body!!.append.div("container") }

    private fun buildTitle() {
        container.append {
            h1 {
                +"Нумерологический калькулятор"
            }
        }
    }

    private fun buildForm() {
        container.append {
            form {
                onSubmit = "return false"
                div("form-group") {
                    label {
                        +"Дата рождения"
                    }
                    input(classes = "form-control") {
                        id = "birthDate"
                        placeholder = "01.10.2001"
                    }
                    small("form-text text-muted") {
                        +"Формат: #день.#месяц.#год"
                    }
                }
                div("form-group") {
                    label {
                        +"Год"
                    }
                    input(classes = "form-control") {
                        id = "personYear"
                        placeholder = "2001"
                    }
                }
                button(classes = "btn btn-primary") {
                    +"Рассчитать"
                    id = "calculate"
                    onClickFunction = {
                        calc()
                    }
                }
            }
        }
    }

    private fun buildResult() {
        container.append {
            br()
            div {
                id = "result"
                div("alert alert-success") {
                    +"Результаты расчета:"
                    role = "Alert"
                }
                table("table table-bordered table-hover") {
                    thead("thead-light") {
                        tr {
                            th { +"Название" }
                            th { +"Значение" }
                        }
                    }
                    tbody {
                        tr {
                            td { +"Число Дживы" }
                            td { id = "jiva" }
                        }
                        tr {
                            td { +"Число Дхармы" }
                            td { id = "dharma" }
                        }
                        tr {
                            td { +"Число Кармы" }
                            td { id = "karma" }
                        }
                        tr {
                            td { +"Число Проблем" }
                            td { id = "problemsCount" }
                        }
                        tr {
                            td { +"Интегральное число" }
                            td { id = "integrationCount" }
                        }
                    }
                }
            }
        }
    }

    private fun buildGoroscope() {
        container.append {
            br()
            div {
                id = "goroscope"
                table("table table-bordered table-hover") {
                    tbody {
                        tr {
                            td("table-fit") { id = "g3" }
                            td("table-fit") { id = "g6" }
                            td("table-fit") { id = "g9" }
                        }
                        tr {
                            td("table-fit") { id = "g2" }
                            td("table-fit") { id = "g5" }
                            td("table-fit") { id = "g8" }
                        }
                        tr {
                            td("table-fit") { id = "g1" }
                            td("table-fit") { id = "g4" }
                            td("table-fit") { id = "g7" }
                        }
                    }
                }
            }
        }
    }

    fun buildLifeCycle() {
        container.append {
            br()
            div {
                id = "goroscope"
                table("table table-bordered table-hover") {
                    thead("thead-light") {
                        tr {
                            th { +"Годы" }
                            th { +"Период планеты" }
                        }
                    }
                    tbody {
                        tr {
                            td("table-fit") { id = "p1" }
                            td("table-fit") { id = "p4" }
                        }
                        tr {
                            td("table-fit") { id = "p2" }
                            td("table-fit") { id = "p5" }
                        }
                        tr {
                            td("table-fit") { id = "p3" }
                            td("table-fit") { id = "p6" }
                        }
                    }
                }
            }
        }

        val pyear = document.getElementById("personYear") as HTMLInputElement
        pyear.value = Date().getFullYear().toString()
    }

    private val monthsArr = arrayOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь",
    )

    fun buildPersonYearAndMoths() {
        container.append {
            br()
            form {
                div("form-group row") {
                    label("col-sm-2 col-form-label") {
                        +"Личный год"
                    }
                    div("col-sm-10") {
                        input(type = InputType.text, classes = "form-control-plaintext") {
                            attributes["readonly"] = "true"
                            id = "personYearT"
                        }
                    }
                }
                for(i in 0..11) {
                    div("form-group row") {
                        label("col-sm-2 col-form-label") {
                            +monthsArr[i]
                        }
                        div("col-sm-10") {
                            input(type = InputType.text, classes = "form-control-plaintext") {
                                attributes["readonly"] = "true"
                                id = "monthsP_$i"
                            }
                        }
                    }
                }
            }
        }
    }

    fun buildNumericYantra() {
        container.append {
            br()
            div {
                id = "numbericYantra"
                table("table table-bordered table-hover") {
                    tbody {
                        for(i in 1..4) {
                            tr {
                                for(j in 1..4) {
                                    td("table-fit") { id = "yantra_${i}_$j" }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun calc() {
        val birthDate = document.getElementById("birthDate") as HTMLInputElement
        val date = birthDate.value
        val splitDate = date.split(".")

        val day = splitDate[0]
        val month = splitDate[1]
        val year = splitDate[2]

        val jiva = document.getElementById("jiva") as HTMLElement
        jiva.innerText = CalcAlg.sumAllDigits(day)
        val jivaRes = CalcAlg.lastRes

        val dharma = document.getElementById("dharma") as HTMLElement
        dharma.innerText = CalcAlg.sumAllDigits(day + month)
        val dharmaRes = CalcAlg.lastRes

        val karma = document.getElementById("karma") as HTMLElement
        karma.innerText = CalcAlg.sumAllDigits(day + month + year)
        val karmaRes = CalcAlg.lastRes

        CalcAlg.sumAllDigits(month)
        val a = abs(jivaRes - CalcAlg.lastRes)

        CalcAlg.sumAllDigits(year)
        val b = abs(jivaRes - CalcAlg.lastRes)
        val problemsCount = abs(a-b)

        val pc = document.getElementById("problemsCount") as HTMLElement
        pc.innerText = problemsCount.toString()

        CalcAlg.sumAllDigits((karmaRes * 2).toString())
        val ic = document.getElementById("integrationCount") as HTMLElement
        ic.innerText = CalcAlg.lastRes.toString()

        ///////////
        val g11 = document.getElementById("g1") as HTMLElement
        g11.innerText = getNumCount(date, "1")

        val g12 = document.getElementById("g2") as HTMLElement
        g12.innerText = getNumCount(date, "2")

        val g13 = document.getElementById("g3") as HTMLElement
        g13.innerText = getNumCount(date, "3")

        ///////////
        val g21 = document.getElementById("g4") as HTMLElement
        g21.innerText = getNumCount(date, "4")

        val g22 = document.getElementById("g5") as HTMLElement
        g22.innerText = getNumCount(date, "5")

        val g23 = document.getElementById("g6") as HTMLElement
        g23.innerText = getNumCount(date, "6")

        ///////////
        val g31 = document.getElementById("g7") as HTMLElement
        g31.innerText = getNumCount(date, "7")

        val g32 = document.getElementById("g8") as HTMLElement
        g32.innerText = getNumCount(date, "8")

        val g33 = document.getElementById("g9") as HTMLElement
        g33.innerText = getNumCount(date, "9")


        ////
        val resP1 = (35 - karmaRes)
        val p1 = document.getElementById("p1") as HTMLElement
        p1.innerText = resP1.toString()

        val resP2 = 27 + resP1
        val p2 = document.getElementById("p2") as HTMLElement
        p2.innerText = resP2.toString()

        val resP3 = resP2 + 1
        val p3 = document.getElementById("p3") as HTMLElement
        p3.innerText = resP3.toString()

        // Дни
        CalcAlg.sumAllDigits(month)
        val p4 = document.getElementById("p4") as HTMLElement
        p4.innerText = CalcAlg.lastRes.toString()

        CalcAlg.sumAllDigits(day)
        val p5 = document.getElementById("p5") as HTMLElement
        p5.innerText = CalcAlg.lastRes.toString()

        CalcAlg.sumAllDigits(year)
        val p6 = document.getElementById("p6") as HTMLElement
        p6.innerText = CalcAlg.lastRes.toString()

        //персональный год и месяц
        val personYearV = document.getElementById("personYear") as HTMLInputElement
        val personYear = personYearV.value
        CalcAlg.sumAllDigits(personYear)
        CalcAlg.sumAllDigits((dharmaRes + CalcAlg.lastRes).toString())
        val pyRes = CalcAlg.lastRes

        val py = document.getElementById("personYearT") as HTMLInputElement
        py.value = (CalcAlg.lastRes).toString()

        for(i in 0..11) {
            val pm = document.getElementById("monthsP_$i") as HTMLInputElement
            CalcAlg.sumAllDigits((pyRes + i + 1).toString())
            pm.value = (CalcAlg.lastRes).toString()
        }

        //рассчет янтры
        val y11 = document.getElementById("yantra_1_1") as HTMLElement
        y11.innerText = "Месяц: " + CalcAlg.sumAllDigitsRes(month)

        val y12 = document.getElementById("yantra_1_2") as HTMLElement
        y12.innerText = "День: " + CalcAlg.sumAllDigitsRes(day)

        val y13 = document.getElementById("yantra_1_3") as HTMLElement
        y13.innerText = "Год: " + CalcAlg.sumAllDigitsRes(year)

        val y14 = document.getElementById("yantra_1_4") as HTMLElement
        y14.innerText = "Число кармы: " + karmaRes.toString()


        val y21 = document.getElementById("yantra_2_1") as HTMLElement
        y21.innerText = "Энергия: " + CalcAlg.sumAllDigitsRes((extractInt(y13) - 2))

        val y22 = document.getElementById("yantra_2_2") as HTMLElement
        y22.innerText = "Близкие: " + CalcAlg.sumAllDigitsRes((extractInt(y14) + 2))

        val y23 = document.getElementById("yantra_2_3") as HTMLElement
        y23.innerText = "Расходы: " + CalcAlg.sumAllDigitsRes((extractInt(y11) - 2))

        val y24 = document.getElementById("yantra_2_4") as HTMLElement
        y24.innerText = "Отношения: " + CalcAlg.sumAllDigitsRes((extractInt(y12) + 2))


        val y31 = document.getElementById("yantra_3_1") as HTMLElement
        y31.innerText = "Талант: " + CalcAlg.sumAllDigitsRes((extractInt(y14) + 1))

        val y32 = document.getElementById("yantra_3_2") as HTMLElement
        y32.innerText = "Радость: " + CalcAlg.sumAllDigitsRes((extractInt(y13) + 1))

        val y33 = document.getElementById("yantra_3_3") as HTMLElement
        y33.innerText = "Кармическая задача: " + CalcAlg.sumAllDigitsRes((extractInt(y12) - 1))

        val y34 = document.getElementById("yantra_3_4") as HTMLElement
        y34.innerText = "Любовь: " + CalcAlg.sumAllDigitsRes((extractInt(y11) - 1))


        val y41 = document.getElementById("yantra_4_1") as HTMLElement
        y41.innerText = "Деньги: " + CalcAlg.sumAllDigitsRes((extractInt(y12) + 1))

        val y42 = document.getElementById("yantra_4_2") as HTMLElement
        y42.innerText = "Забота: " + CalcAlg.sumAllDigitsRes((extractInt(y11) - 3))

        val y43 = document.getElementById("yantra_4_3") as HTMLElement
        y43.innerText = "Возможности: " + CalcAlg.sumAllDigitsRes((extractInt(y14) + 3))

        val y44 = document.getElementById("yantra_4_4") as HTMLElement
        y44.innerText = "Мировоззрение: " + CalcAlg.sumAllDigitsRes((extractInt(y13) - 1))
    }

    fun extractInt(el: HTMLElement): Int {
        return el.innerText.split(" ").last().toInt()
    }

    fun getNumCount(source: String, num: String): String {
        val c = source.count{ num.contains(it) }
        var res = ""
        repeat(c) {
            res += num
        }
        return res
    }
}
