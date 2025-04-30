import java.awt.BorderLayout; // Importa o layout para organizar os componentes na tela
import java.awt.Font; // Importa as configurações de fonte
import java.awt.GridLayout; // Importa o layout para organizar os botões em grade
import java.awt.event.ActionEvent; // Importa eventos de ação (como cliques nos botões)
import java.awt.event.ActionListener; // Importa o ouvinte de ações (para reagir aos eventos)
import javax.swing.JButton; // Importa os botões
import javax.swing.JFrame; // Importa a janela principal
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel; // Importa um painel para organizar componentes
import javax.swing.JTextField; // Importa o campo de texto para exibição

public class CalculadoraGUI extends JFrame implements ActionListener { // Define a classe da calculadora e que ela reage a eventos
    private final JTextField display; // Declara um campo de texto para mostrar os números e resultados
    private String operacao = ""; // Declara uma string para armazenar a operação selecionada
    private double num1, num2; // Declara variáveis para armazenar os números da operação
    private boolean novoNumero = true; // Declara um controle para saber se vai começar um novo número

    public CalculadoraGUI() { // Construtor da calculadora (inicializa os componentes)
        setTitle("Calculadora"); // Define o título da janela
        setSize(300, 400); // Define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura para fechar o programa ao fechar a janela
        setLayout(new BorderLayout()); // Define o layout como "BorderLayout" (organização por bordas)

        //Barra de Menu
        JMenuBar menuBar = new JMenuBar(); //Criar barra de menu
        JMenu menu = new JMenu("Outros"); //Criar o menu outros
        JMenu sair = new JMenu("Sair"); //Criar o menu sair
        JMenuItem itemConversor = new JMenuItem("Conversor Distância"); //Criando o item de menu "Conversor Distância"
        itemConversor.addActionListener(e -> new ConversorMedidasGUI()); //Adicionando ação ao item do menu
        menu.add(itemConversor); //Adicionando o item ao menu
        menuBar.add(menu); //Adicionando o menu à barra de menus
        menuBar.add(sair); //Adicionando o sair a barra de menus
        setJMenuBar(menuBar);//Definindo a barra de menus na janela principal

        // Campo de exibição
        display = new JTextField(); // Cria o campo de texto para exibir números e resultados
        display.setFont(new Font("Arial", Font.BOLD, 24)); // Define a fonte como Arial, negrito e tamanho 24
        display.setHorizontalAlignment(JTextField.RIGHT); // Alinha o texto à direita
        display.setEditable(false); // Impede que o campo de texto seja editado diretamente
        add(display, BorderLayout.NORTH); // Adiciona o campo de texto na parte superior da janela

        // Painel para Botões
        JPanel painel = new JPanel(); // Cria um painel para os botões
        painel.setLayout(new GridLayout(4, 4, 5, 5)); // Define o layout do painel como uma grade 4x4 com espaçamento

        // Botões da Calculadora
        String[] botoes = { // Lista de textos dos botões
                "7", "8", "9", "/", // Primeira linha de botões
                "4", "5", "6", "*", // Segunda linha de botões
                "1", "2", "3", "-", // Terceira linha de botões
                "0", "C", "=", "+", // Quarta linha de botões
        };

        // Criando os botões
        for (String texto : botoes) { // Para cada texto na lista de botões
            JButton botao = new JButton(texto); // Cria um botão com o texto correspondente
            botao.setFont(new Font("Arial", Font.BOLD, 20)); // Define a fonte do botão como Arial, negrito e tamanho 20
            botao.addActionListener(this); // Adiciona o ouvinte de eventos para o botão
            painel.add(botao); // Adiciona o botão ao painel
        }
        add(painel, BorderLayout.CENTER); // Adiciona o painel de botões ao centro da janela

        setVisible(true); // Torna a janela visível
    }

    @Override
    public void actionPerformed(ActionEvent e) { // Método chamado quando um botão é clicado
        String comando = e.getActionCommand(); // Obtém o texto do botão clicado

        if ("0123456789".contains(comando)) { // Verifica se o botão clicado é um número
            if (novoNumero) { // Se for para começar um novo número
                display.setText(comando); // Mostra o número no display
                novoNumero = false; // Indica que não é mais um novo número
            } else {
                display.setText(display.getText() + comando); // Adiciona o número ao que já está no display
            }
        } else if ("+-*/".contains(comando)) { // Verifica se o botão clicado é uma operação
            num1 = Double.parseDouble(display.getText()); // Salva o número atual como o primeiro número
            operacao = comando; // Salva a operação selecionada
            novoNumero = true; // Prepara para um novo número
        } else if (comando.equals("=")) { // Verifica se o botão clicado é "="
            num2 = Double.parseDouble(display.getText()); // Salva o número atual como o segundo número
            double resultado = calcular(num1, num2, operacao); // Calcula o resultado
            display.setText(String.valueOf(resultado)); // Mostra o resultado no display
            operacao = ""; // Limpa a operação
            novoNumero = true; // Prepara para um novo número
        } else if (comando.equals("C")) { // Verifica se o botão clicado é "C"
            display.setText(""); // Limpa o display
            num1 = num2 = 0; // Reseta os números
            operacao = ""; // Limpa a operação
            novoNumero = true; // Prepara para um novo número
        }
    }

    private double calcular(double valor1, double valor2, String ope) { // Método para realizar o cálculo
        return switch (ope) { // Escolhe a operação usando "switch"
            case "+" -> valor1 + valor2; // Soma os números
            case "-" -> valor1 - valor2; // Subtrai os números
            case "*" -> valor1 * valor2; // Multiplica os números
            case "/" -> (valor2 != 0) ? valor1 / valor2 : 0; // Divide os números, mas evita divisão por zero
            default -> 0; // Retorna 0 se a operação for inválida
        };
    }

    public static void main(String[] args) throws Exception { // Método principal para iniciar o programa
        new CalculadoraGUI(); // Cria uma nova instância da calculadora
    }
}