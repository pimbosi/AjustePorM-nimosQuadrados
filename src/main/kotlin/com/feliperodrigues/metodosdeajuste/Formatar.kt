package com.feliperodrigues.metodosdeajuste

// Classe para formatação de strings
class Formatar {

    // Função para formatar coeficientes para saída no gnuplot
    fun formatarParaGnuPlot(doubleArray: DoubleArray?): String? {
        val termosFormatados = doubleArray?.mapIndexed { indice, valor ->
            val variavel = if (indice == 0) "" else "*x"
            val expoente = if (indice == 0) "" else "**$indice"
            "$valor$variavel$expoente"
        }
        return termosFormatados?.joinToString(" + ")
    }

    // Função para formatar coeficientes de ajuste exponencial para saída no gnuplot
    fun formatarParaGnuPlotExp(doubleArray: DoubleArray?): String? {
        val termosFormatados = doubleArray?.mapIndexed { indice, valor ->
            when (indice) {
                0 -> "$valor"  // Coeficiente linear
                1 -> "* exp(x)**$valor" // Termo exponencial
                else -> ""
            }
        }
        return termosFormatados?.joinToString(" ")
    }

    // Função para formatar coeficientes de ajuste por lei da potência para saída no gnuplot
    fun formatarParaGnuPlotPowerLaw(doubleArray: DoubleArray?): String? {
        val termosFormatados = doubleArray?.mapIndexed { indice, valor ->
            when (indice) {
                0 -> "$valor"  // Coeficiente linear
                1 -> "* x**$valor" // Termo exponencial
                else -> ""
            }
        }
        return termosFormatados?.joinToString(" ")
    }
}