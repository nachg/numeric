package org.numeric

import kotlinx.browser.document
import kotlinx.html.*
import kotlinx.html.dom.append
import kotlinx.html.js.div
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement

object Calculator {
    fun render() {
        buildTitle()
        buildForm()
        buildResult()
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

        val dharma = document.getElementById("dharma") as HTMLElement
        dharma.innerText = CalcAlg.sumAllDigits(day + month)

        val karma = document.getElementById("karma") as HTMLElement
        karma.innerText = CalcAlg.sumAllDigits(day + month + year)
    }
}
