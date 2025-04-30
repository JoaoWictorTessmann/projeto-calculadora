
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class ConversorMedidasGUI extends JFrame {
    private JTextField campoValor;
    private JComboBox<String> comboConversao;
    private JCheckBox checkArredondar;
    private JRadioButton radioIda, radioVolta;
    private ButtonGroup grupoDirecao;
    private JLabel labelResultado;

    public ConversorMedidasGUI(){
        setTitle("Conversor de Medidas"); //Criar o titulo da tela
        setSize(400,300); //Definir tamanho da tela
        setLocationRelativeTo(null); //Local onde aparece a aba.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Ocultar a tela do Conversor
        setLayout(new GridLayout(6,1)); //Definir o layout

        //Campo de entrada
        JPanel painelEntrada = new JPanel(); //Criando 
        painelEntrada.add(new JLabel("Valor:"));
        campoValor = new JTextField(10);
        painelEntrada.add(campoValor);
        add(painelEntrada);

    }
}
