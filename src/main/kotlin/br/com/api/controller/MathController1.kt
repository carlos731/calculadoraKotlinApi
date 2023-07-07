package br.com.api.controller

import br.com.api.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

//@RestController
class MathController1 {

    val counter: AtomicLong = AtomicLong()

    // Soma: http://localhost:8080/sum/5/5
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    // Subtração: http://localhost:8080/subtraction/5/5
    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }

    // Multiplicação: http://localhost:8080/multiplication/5/5
    @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }

    // Divisão: http://localhost:8080/division/5/5
    @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
    fun division(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }

    // Média: http://localhost:8080/mean/5/5
    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(
        @PathVariable(value = "numberOne") numberOne: String?,
        @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2
    }

    // Média: http://localhost:8080/squareRoot/5/5
    @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(
        @PathVariable(value = "number") number: String?
    ): Double {
        if (!isNumeric(number))
            throw UnsupportedMathOperationException("Please set a numeric value!")
        return Math.sqrt(convertToDouble(number))
    }

    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank()) return 0.0
        // BR 10,20 US 10.20
        val number = strNumber.replace(",".toRegex(), ".") // Convertendo valores com "," para ".".
        return if (isNumeric(number)) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if(strNumber.isNullOrBlank()) return false;
        val number = strNumber.replace(",".toRegex(), ".")
        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex()) // Verificar se é numérico.
    }

}