import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class App {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Calculadora Simples"); // Criar o frame com o título calculadora simples
        frame.setSize(300, 400); // Definir tamanho da tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fechar aplicação quando finalizar
        frame.setLayout(new GridLayout(5, 1)); // Layout estilo grid

        // Criando os componentes
        JTextField campo1 = new JTextField(); // Primeiro campo de texto
        JTextField campo2 = new JTextField(); // Segundo campo de texto
        JComboBox<String> operacoes = new JComboBox<>(new String[]{"+", "-", "*", "/"}); // Menu DropDown com as opções de escolha
        JButton calcular = new JButton("Calcular"); // Botão para calcular
        JLabel resultado = new JLabel("Resultado: "); // Mostra resultado

        // Adicionando os componentes no frame
        frame.add(campo1); // Adicionar campo de texto 1 "TextField"
        frame.add(operacoes); // Adicionar campo seleção da operação aritmética "ComboBox"
        frame.add(campo2); // Adicionar campo de texto 2 "TextField"
        frame.add(calcular); // Adicionar botão para pressionar o calcular "Button"
        frame.add(resultado); // Adicionar campo que mostra o resultado "Label"
        frame.setVisible(true); // Exibe todos os componentes no frame

        // Criando evento para calcular
        calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Obtendo os valores dos campos
                    double valor1 = Double.parseDouble(campo1.getText());
                    double valor2 = Double.parseDouble(campo2.getText());
                    double res = 0;

                    //Obtendo a operação selecionada do JComboBox
                    String operacao = (String) operacoes.getSelectedItem();

                    //Realizando o cálculo com base na operação selecionada
                    switch (operacao) {
                        case "+":
                            res = valor1 + valor2;
                            break;
                        case "-":
                            res = valor1 - valor2;
                            break;
                        case "*":
                            res = valor1 * valor2;
                            break;
                        case "/":
                            if (valor2 != 0) {
                                res = valor1 / valor2;
                            } else {
                                throw new ArithmeticException("Divisão por zero!");
                            }
                            break;
                        default:
                            throw new AssertionError("Operação inválida");
                    }

                    //Exibir o resultado
                    resultado.setText("Resultado: " + res);
                } catch (NumberFormatException ex) {
                    resultado.setText("Erro: Entrada inválida");
                } catch (ArithmeticException ex) {
                    resultado.setText("Erro: " + ex.getMessage());
                }
            }
        });
    }
}