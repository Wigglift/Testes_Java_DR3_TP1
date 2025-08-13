package br.edu.infnet.DR3_TP1;

import br.edu.infnet.DR3_TP1.model.ScientificCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScientificCalculatorTest {

	private ScientificCalculator calculadora;

	@BeforeEach
	void CalculatorInstanciation(){
		calculadora = new ScientificCalculator();
	}

	//Testes de cálculo correto

	@Test
	void deveSomarCorretamente() {
		//Dado - Setup:
		double num1 = 4;
		double num2 = 5;
		double numeroEsperado = 9;
		//Quando - Execution:
		double resposta = calculadora.add(num1,num2);
		//Então - Assertion:
		assertEquals(numeroEsperado,resposta,"A soma de 4 e 5 deveria dar 9");
	}

	@Test
	void deveSubtrairCorretamente() {
		//Dado - Setup:
		double num1 = 4;
		double num2 = 5;
		double numeroEsperado = -1;
		//Quando - Execution:
		double resposta = calculadora.subtract(num1,num2);
		//Então - Assertion:
		assertEquals(numeroEsperado,resposta,"A subtração de 4 e 5 deveria dar -1");
		//Sem necessidade de Teardown
	}

	@Test
	void deveMultiplicarCorretamente() {
		//Dado - Setup:
		double num1 = 4;
		double num2 = 5;
		double numeroEsperado = 20;
		//Quando - Execution:
		double resposta = calculadora.multiply(num1,num2);
		//Então - Assertion:
		assertEquals(numeroEsperado,resposta,"A multiplicação de 4 e 5 deveria dar 20");
		//Sem necessidade de Teardown
	}

	@Test
	void deveDividirCorretamente() {
		//Dado - Setup:
		double num1 = 20;
		double num2 = 5;
		double numeroEsperado = 4;
		//Quando - Execution:
		double resposta = calculadora.divide(num1,num2);
		//Então - Assertion:
		assertEquals(numeroEsperado,resposta,"A divisão de 20 por 5 deveria dar 4");
		//Sem necessidade de Teardown
	}

	@Test
	void deveFazerRaizQuadradaCorretamenteQuandoEntradaEhPositiva(){
		//Setup:
		double num1 = 49;
		double numeroEsperado = 7;
		//Execution:
		double resposta = calculadora.squareRoot(num1);
		//Assertion:
		assertEquals(numeroEsperado,resposta,"A raiz quadrada de 49 deveria dar 7");
	}

	//Testes parametrizados de cálculo correto

	@ParameterizedTest(name = "Entrada: {0}, Resposta: {1}")
	@CsvSource({"10,2.302585092994046",
	"100,4.605170185988092",
	"1000,6.907755278982137"})
	void deveFazerLogaritmoCorretamenteComVariasEntradas(double num, double numEsperado){
		//Setup;
		//Execution:
		double resposta = calculadora.log(num);
		//Assertion:
		assertEquals(numEsperado,resposta,"Log de "+num+" Deveria ser "+numEsperado);
	}

	@ParameterizedTest(name = "Entrada: {0}, Resposta: {1}")
	@CsvSource({"45,0.7071067811865475",
			"90,1",
			"150,0.49999999999999994"})
	void deveCalcularSenoCorretamenteComVariasEntradas(double num, double numEsperado){
		//Setup;
		//Execution:
		double resposta = calculadora.sin(num);
		//Assertion:
		assertEquals(numEsperado,resposta,"Seno de "+num+"° Deveria ser "+numEsperado);
	}

	@ParameterizedTest(name = "Entrada: {0}, Resposta: {1}")
	@CsvSource({"45,0.7071067811865476",
			"60,0.5000000000000001",
			"150,-0.8660254037844387"})
	void deveCalcularCossenoCorretamenteComVariasEntradas(double num, double numEsperado){
		//Setup;
		//Execution:
		double resposta = calculadora.cos(num);
		//Assertion:
		assertEquals(numEsperado,resposta,"Cosseno de "+num+"° Deveria ser "+numEsperado);
	}

	@ParameterizedTest(name = "Entrada: {0}, Resposta: {1}")
	@CsvSource({"2,3, 8",
			"5,2,25",
			"7,3,343"})
	void deveCalcularPOtenciaCorretamenteComVariasEntradas(double num1, double num2, double numEsperado){
		//Setup;
		//Execution:
		double resposta = calculadora.power(num1,num2);
		//Assertion:
		assertEquals(numEsperado,resposta,"Potencia de "+num1+" elevado a"+num2+" Deveria ser "+numEsperado);
	}

	//Testes de erro

	@Test
	void deveLancarErroAoCalcularRaizQuadradaQuandoEntradaEhNegativa(){
		//Setup:
		double num1 = -49;
		String mensagemEsperada = "Negative number";
		//Execution:
		IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,() -> calculadora.squareRoot(num1),"Deveria ter lançado um erro ao tirar a raiz quadrada de um número negativo");
		//Assertion:
		assertEquals(mensagemEsperada, erro.getMessage(), "Deveria ter lançado um IllegalArgumentException ao tirar a raiz quadrada de um número negativo");
	}

	@Test
	void deveLancarErroAoCalcularDivisaoQuandoEntradaEhIgualAZero(){
		//Setup:
		double num1 = 10;
		double num2 = 0;
		String mensagemEsperada = "Division by zero";
		//Execution:
		IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,() -> calculadora.divide(num1,num2),"Deveria ter lançado um erro ao dividir por zero");
		//Assertion:
		assertEquals(mensagemEsperada, erro.getMessage(), "Deveria ter lançado um IllegalArgumentException ao dividir um número por 0");
	}

	@Test
	void deveLancarErroAoCalcularLogQuandoEntradaEhIgualAZero(){
		//Setup:
		double num1 = -10;
		String mensagemEsperada = "Log of non-positive number";
		//Execution:
		IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,() -> calculadora.log(num1),"Deveria ter lançado um erro ao fazer logarítmo com número negativo");
		//Assertion:
		assertEquals(mensagemEsperada, erro.getMessage(), "Deveria ter lançado um IllegalArgumentException ao fazer logarítmo de um número negativo");
	}
}
