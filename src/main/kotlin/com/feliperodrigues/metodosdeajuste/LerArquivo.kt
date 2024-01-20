package com.feliperodrigues.metodosdeajuste

// Importação de bibliotecas para manipulação de arquivos
import java.io.File

// Classe para leitura de arquivo
class LerArquivo {
    var pointsX: ArrayList<Double> = arrayListOf()
    var pointsY: ArrayList<Double> = arrayListOf()

    // Função para leitura de arquivo e retorno dos pontos
    fun leituraArquivo(nomeArquivo: String?): Pair<DoubleArray, DoubleArray>?{
        try {
            val arquivo = File(nomeArquivo)
            // Verifica se o arquivo existe
            if (!arquivo.exists()) {
                println("O arquivo não existe.")
                return null
            }
            val leitor = arquivo.bufferedReader()

            // Lê o arquivo linha por linha
            leitor.useLines { linhas ->
                linhas.forEach { linha ->
                    val partes = linha.split(" ")

                    // Extrai os valores de x e y
                    if (partes.size >= 2) {
                        val x = partes[0].toDouble()
                        val y = partes[1].toDouble()
                        pointsX.add(x)
                        pointsY.add(y)
                    }
                }
            }
            val pointsDoubleX: DoubleArray = pointsX.toDoubleArray()
            val pointsDoubleY: DoubleArray = pointsY.toDoubleArray()
            return Pair(pointsDoubleX, pointsDoubleY)
        } catch (ex: Exception) {
            // Trata exceções, se ocorrerem
            println("Ocorreu um erro ao ler o arquivo: ${ex.message}")
        }
        return null
    }
}