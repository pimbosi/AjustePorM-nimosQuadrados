package com.feliperodrigues.metodosdeajuste

// Importando bibliotecas necessárias para ajuste e leitura de arquivos
import org.apache.commons.math3.analysis.ParametricUnivariateFunction
import org.apache.commons.math3.fitting.WeightedObservedPoints
import org.apache.commons.math3.fitting.SimpleCurveFitter

// Classe para ajuste exponencial
class AjusteExp() {

    // Instância da classe responsável pela leitura de arquivos
    val lerArquivo = LerArquivo()

    // Função para realizar o ajuste
    fun funcaoDeAjuste(nomeArquivo: String, tipoAjuste: String) {

        // Inicialização de pontos observados
        val pontosObservados = WeightedObservedPoints()
        val pontos: Pair<DoubleArray, DoubleArray>?

        // Realiza a leitura do arquivo
        pontos = lerArquivo.leituraArquivo(nomeArquivo)

        // Verifica se a leitura foi bem-sucedida
        if (pontos != null) {
            val (x, y) = pontos
            val par = x.zip(y)
            for ((x, y) in par)
                pontosObservados.add(x, y)

            // Definição da função exponencial e sua derivada
            val funcaoExponencial = object : ParametricUnivariateFunction {
                override fun value(t: Double, parameters: DoubleArray): Double {
                    val a = parameters[0]
                    val b = parameters[1]
                    if(tipoAjuste == "exp")
                        return a * Math.exp(b * t)
                    else return a * Math.pow(t, b)
                }

                override fun gradient(t: Double, parameters: DoubleArray): DoubleArray {
                    val a = parameters[0]
                    val b = parameters[1]
                    if(tipoAjuste == "exp") {
                        val valorExponencial = Math.exp(b * t)
                        return doubleArrayOf(valorExponencial, a * t * valorExponencial)
                    } else {
                        val valorExponencial = Math.pow(t, b)
                        return doubleArrayOf(valorExponencial, a * valorExponencial)
                    }
                }
            }

            // Inicializa os parâmetros iniciais
            val parametrosIniciais = doubleArrayOf(1.0, 0.0)

            // Realiza o ajuste
            val parametrosAjustados = SimpleCurveFitter.create(funcaoExponencial, parametrosIniciais)
                .withMaxIterations(1000)
                .fit(pontosObservados.toList())

            // Formata os parâmetros ajustados para saída
            val formatarString = Formatar()
            val stringFormatada = if(tipoAjuste=="exp")
                formatarString.formatarParaGnuPlotExp(parametrosAjustados)
            else formatarString.formatarParaGnuPlotPowerLaw(parametrosAjustados)

            // Imprime a função ajustada
            println("\nf(x) = "+stringFormatada+"\n\n")

            // Configuração e chamada do gnuplot para plotar o gráfico
            gnuplot {
                invoke(
                    """
                f(x) = ${stringFormatada}
                set xzeroaxis 
                set yzeroaxis
                set grid
                plot [${x.minOrNull()}:${x.maxOrNull()}][${y.minOrNull()}:${y.maxOrNull()}]\
                 f(x) w l lw 2, '${nomeArquivo}' u 1:2 w p pt 7
            """.trimIndent()
                )
            }
        } else println("Nao foi possível definir os pontos.")
    }
}
