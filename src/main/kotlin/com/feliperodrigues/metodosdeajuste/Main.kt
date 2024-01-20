package com.feliperodrigues.metodosdeajuste

import java.util.*

// Função para limpar o terminal
fun limparTerminal() {
    ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor()
}


// Função para exibir o menu principal
fun menu() {
    limparTerminal()

    val scanner = Scanner(System.`in`)
    var input = 0

    while (input != 6) {
        println("\t\tAjustes de funções por mínimos quadrados".uppercase())
        println("\n\nEscolha uma opção de ajuste:")
        println("1. Ajuste Quadrático")
        println("2. Ajuste Linear")
        println("3. Ajuste Exponencial")
        println("4. Ajuste Polinomial")
        println("5. Ajuste Lei da Potência")
        println("6. Encerrar")

        try {
            input = scanner.nextInt()

            when (input) {
                1 -> {
                    limparTerminal()
                    println("\t\t\t\tAJUSTE QUADRÁTICO\n\n")
                    println("Digite o caminho do seu arquivo de dados ou arraste.")
                    val nomeArquivo = scanner.next()
                    val ajusteMinSquare = AjusteMinSquare()
                    ajusteMinSquare.funcaoDeAjuste(nomeArquivo, grauPolinomial = 2)
                }
                2 -> {
                    limparTerminal()
                    println("\t\t\t\tAJUSTE LINEAR\n\n")
                    println("Digite o caminho do seu arquivo de dados ou arraste.")
                    val nomeArquivo = scanner.next()
                    val ajusteLinear = AjusteMinSquare()
                    ajusteLinear.funcaoDeAjuste(nomeArquivo, grauPolinomial = 1)
                }
                3 -> {
                    limparTerminal()
                    println("\t\t\t\tAJUSTE EXPONENCIAL\n\n")
                    println("Digite o caminho do seu arquivo de dados ou arraste.")
                    val nomeArquivo = scanner.next()
                    val ajusteExp = AjusteExp()
                    ajusteExp.funcaoDeAjuste(nomeArquivo, tipoAjuste = "exp")
                }

                4 -> {
                    limparTerminal()
                    println("\t\t\t\tAJUSTE POLINOMIAL\n\n")
                    println("Digite o caminho do seu arquivo de dados ou arraste.")
                    val nomeArquivo = scanner.next()
                    println("Digite o grau do polinômio: ")
                    val grauPolinomial = scanner.nextInt()
                    val ajusteQuadratico = AjusteMinSquare()
                    ajusteQuadratico.funcaoDeAjuste(nomeArquivo, grauPolinomial)
                }
                5 -> {
                    limparTerminal()
                    println("\t\t\t\tAJUSTE LEI DA POTÊNCIA\n\n")
                    println("Digite o caminho do seu arquivo de dados ou arraste.")
                    val nomeArquivo = scanner.next()
                    val ajustePL = AjusteExp()
                    ajustePL.funcaoDeAjuste(nomeArquivo, tipoAjuste = "powerlaw")
                }
                6 ->  {
                    limparTerminal()
                    println("Encerrando o programa...")
                }
                else -> println("Opção inválida. Por favor, escolha uma opção válida.")
            }
        } catch (e: InputMismatchException) {
            println("Erro: Entrada inválida. Certifique-se de inserir um número inteiro.")
            scanner.nextLine() // Limpar o buffer do scanner para evitar loop infinito
        } catch (e: Exception) {
            println("Erro inesperado: ${e.message}")
            scanner.nextLine() // Limpar o buffer do scanner para evitar loop infinito
        }
    }

    scanner.close()
}

// Função principal que inicia o programa
fun main() {

    menu()

}