# Ajuste por Mínimos Quadrados

Este é um projeto em Kotlin que realiza o ajuste por mínimos quadrados de diferentes funções a partir de dados contidos em um arquivo. O programa oferece opções para ajuste quadrático, linear, exponencial, polinomial e por lei da potência. O resultado do ajuste é apresentado em um gráfico gerado pelo Gnuplot, além de exibir a função ajustada.

## Como Usar

1. **Baixe o arquivo JAR que se encontra em na seção de Releases**
   
2. **Execute o JAR:**
   ```bash
   java -jar TrabalhoMNEP4.jar
   ```
3. **Siga as Instruções:**
   - Escolha a opção de ajuste desejada.
   - Insira o caminho do arquivo de dados contendo os pontos x e y separados por espaço.
   - Visualize a função ajustada no terminal e o gráfico gerado pelo Gnuplot.

## Dependências

O projeto utiliza as seguintes bibliotecas externas:

- Apache Commons Math 3.6.1: [Commons Math](http://commons.apache.org/proper/commons-math/)
- Apache Commons Exec 1.3: [Commons Exec](http://commons.apache.org/proper/commons-exec/)

Certifique-se de que essas dependências estão presentes no classpath ao compilar e executar o projeto.

## Requisitos

- Java Runtime Environment (JRE)
- Gnuplot

## Estrutura do Arquivo de Dados

O arquivo de dados deve conter duas colunas, onde a primeira coluna representa os valores de x e a segunda coluna representa os valores de y. Cada linha representa um par de pontos.

```plaintext
1.0  2.3
2.0  3.5
3.0  4.1
4.0  5.0
5.0  6.2
```

## Observações

- Certifique-se de ter o Gnuplot instalado e disponível no PATH do sistema.
- Os comandos Gnuplot são gerados automaticamente para criar os gráficos.

## Trabalho Acadêmico

Este projeto foi desenvolvido como parte de um trabalho acadêmico para a disciplina de Métodos Numéricos Aplicados à Engenharia, oferecida pela Universidade de Brasília, Campus Gama.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).

O código fonte contido no arquivo `Gnuplot.kt` é utilizado com base na licença Apache License 2.0 do repositório [danftang/KotlinGnuplot](https://github.com/danftang/KotlinGnuplot). Consulte o arquivo [LICENSE](https://github.com/danftang/KotlinGnuplot/blob/master/LICENSE) desse repositório para obter detalhes sobre os termos e condições da licença.

