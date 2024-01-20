package com.feliperodrigues.metodosdeajuste

// Importação de bibliotecas necessárias para ajuste polinomial
import org.apache.commons.math3.fitting.PolynomialCurveFitter
import org.apache.commons.math3.fitting.WeightedObservedPoints

// Classe para ajuste por mínimos quadrados
class AjusteMinSquare {

    // Array para armazenar os coeficientes do ajuste
    var coeficientes: DoubleArray? = null
    // Instância da classe responsável pela leitura de arquivos
    val lerArquivo = LerArquivo()

    // Função para realizar o ajuste polinomial
    fun funcaoDeAjuste(nomeArquivo: String?, grauPolinomial: Int) {

        // Inicialização de pontos observados
        val pontosObservados = WeightedObservedPoints()
        val pontos: Pair<DoubleArray, DoubleArray>?

        // Realiza a leitura do arquivo
        pontos = lerArquivo.leituraArquivo(nomeArquivo)

        // Verifica se a leitura foi bem-sucedida
        if (pontos != null) {
            val (x,y) = pontos
            val par = x.zip(y)
            for((x,y) in par)
                pontosObservados.add(x, y)

            // Criação do ajuste polinomial
            val ajuste = PolynomialCurveFitter.create(grauPolinomial)
            // Realiza o ajuste e obtém os coeficientes
            coeficientes = ajuste.fit(pontosObservados.toList())

            // Formata os coeficientes para saída
            val formatarString = Formatar()
            val stringFormatada = formatarString.formatarParaGnuPlot(coeficientes)

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