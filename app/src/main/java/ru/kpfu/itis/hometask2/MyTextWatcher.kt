package ru.kpfu.itis.hometask2

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.Integer.min
import java.util.regex.Pattern

class MyTextWatcher(var editText: EditText, var unenabledEditText: EditText): TextWatcher {

    private var currentText = ""
    private var mask = "+7 (9%c%c) - %c%c%c - %c%c - %c%c"
    private var initial = "+7 (9"
    private var regex = "\\+7\\s\\(9\\d\\d\\)\\s-\\s[\\d]{3}\\s-\\s[\\d]{2}\\s-\\s[\\d]{2}"

    private var selectionIndex = 0

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        if (p0.toString() != currentText) {

            val newText: String = p0.toString()

            var newCurrentText = if ((!newText.startsWith(initial)) and (newText.length < initial.length)) {
                if (currentText == "") {
                    "+7 (9$newText"
                } else {
                    "+7 (9"
                }
            } else {
                newText
            }

            newCurrentText = getNumber(newCurrentText)

            selectionIndex = selectionIndex(currentText, newCurrentText)

            unenabledEditText.isEnabled = Pattern.matches(regex, newCurrentText)
            currentText = newCurrentText
            this.editText.setText(currentText)
            this.editText.setSelection(if (selectionIndex > initial.length) selectionIndex else initial.length)


        }

    }


    private fun getNumber(text: String): String {

        var newArray = ArrayList<Char>()
        if (text.startsWith(initial) && text.length > initial.length) {
            text.substring(5).forEach {
                if (it != ')' && it!= '-' && it!= ' ') {
                    newArray.add(it)
                }
            }
        }
        //список newArray содержит цифры или символы, введенные пользователем, и не содержит элементов маски (то есть пробела, дефис и скобки).
        // Кроме того, благодаря обработке text перед записью в NewArray пользователь не может ввести некоторые служебные символы
        //Здесь же можно было бы обрабатывать просто через isDigit, не давая юзеру ввести что-то кроме цифр, но суть задания была не в этом

        var num = ""

        if ((newArray.size < 9) or (currentText.length > text.length)) {

            while (newArray.size < 9) {
                newArray.add('*')
            }

            num = String.format(mask, *newArray.toArray())
            if (num.contains('*')) {
                num = num.substring(0, num.indexOf('*'))
            }

            //В num временно записывается обновленный номер, подставленный в маску, которая обрезана до необходимого символа
            //В tempArrNum уже записывается номер со всеми служебными символами
            var tempArrNum = num.toCharArray().toMutableList()

            //некрасивый костыль, который решает проблему удаления элемента маски - вместо него удаляется ближайший символ пользователя:
            if (currentText.length > text.length) {
                for (i in text.indices) {
                    if (currentText[i] != text[i]) {
                        if (currentText[i] == ' ' || currentText[i] == '-' || currentText[i] == ')') {
                            for (j in i downTo 0) {
                                if (tempArrNum[j] != ' ' && tempArrNum[j] != ')' && tempArrNum[j] != '-') {
                                    tempArrNum.removeAt(j)
                                    break
                                }
                            }
                            break
                        }
                        break
                    }
                }
            }


            //здесь легче было повторить процедуру, чтобы записать номер уже нормально в маску
            var tempArrUnformed = ArrayList<Char>()
            tempArrNum.subList(5, tempArrNum.size).forEach {
                if (it != ')' && it!= '-' && it!= ' ') {
                    tempArrUnformed.add(it)
                }
            }

            while (tempArrUnformed.size < 9) {
                tempArrUnformed.add('*')
            }

            num =  String.format(mask, *tempArrUnformed.toArray())
            num = num.substring(0, num.indexOf('*'))

            //если номер обрубился на служебных символах, то они удаляются, чтобы было эстетичнее
            while (num.last() == ' ' || num.last() == '-' || num.last()== ')') {
                num = num.substring(0, num.length - 1)
            }

        } else if (newArray.size == 9) {
            num =  String.format(mask, *newArray.toArray())
        } else {
            num =  currentText
        }

        return num
    }

    private fun selectionIndex(curText: String, newText: String) : Int {
        var index = 0

        if (newText.startsWith(curText) || (curText.startsWith(newText))  || curText.isEmpty()) {
            index = newText.length
        } else {
            var curArray = curText.toCharArray()
            var newArray = newText.toCharArray()

            var stop = min(curArray.size, newArray.size)

            for (i in 0..stop) {
                if (curArray[i] != newArray[i]) {
                    index = i
                    break
                }
            }

            if (newArray.size < curArray.size) {
                index++
            }

        }

        return index

    }

    override fun afterTextChanged(p0: Editable?) {

    }

}
