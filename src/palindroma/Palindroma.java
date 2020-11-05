package palindroma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Palindroma extends JFrame {

    //declaro elemetos gui
    public static JTextArea Palabras, SecuenT, ForkJoinT, ExecutorT;
    public JLabel timeSecuen, timeForkJoin, timeExecutor;
    public JButton btnStart;
    public Integer contadorPalabras=0;
    public String cadenaPalabras = "anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz anilina Las personas mayores son incapaces de comprender algo por sí solas es muy fastidioso para los niños darles explicaciones una arepera otra vez arepera zaz";
    public long inicio,total,resultado;

    Palindroma(String [] palabras) {
        super("DFGG 17310120");
        //text area palabras
        Palabras = new JTextArea();
        Palabras.setText(cadenaPalabras);
        Palabras.setBounds(20, 20, 1800, 20);
        JScrollPane bar = new JScrollPane(Palabras);
        this.add(Palabras);
        //text area secuancial
        SecuenT = new JTextArea();
        SecuenT.setBounds(20, 120, 500, 20);
        //SecuenT.setBackground(Color.decode("#609cee"));
        SecuenT.setEditable(false);
        JScrollPane bar1 = new JScrollPane(SecuenT);
        this.add(SecuenT);

        ForkJoinT = new JTextArea();
        ForkJoinT.setBounds(20, 220, 500, 20);
        //ForkJoinT.setBackground(Color.decode("#718cb2"));
        ForkJoinT.setEditable(false);
        JScrollPane bar2 = new JScrollPane(ForkJoinT);
        this.add(ForkJoinT);

        ExecutorT = new JTextArea();
        ExecutorT.setBounds(20, 320, 500, 20);        
        ExecutorT.setEditable(false);
        JScrollPane bar3 = new JScrollPane(ExecutorT);
        this.add(ExecutorT);

        timeSecuen = new JLabel("Tiempo en secuencial:");
        timeForkJoin = new JLabel("Tiempo en fork-join:");
        timeExecutor = new JLabel("Tiempo en executor:");

        btnStart = new JButton("iniciar.");
        btnStart.setBounds(100, 500, 700, 80);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                inicio = System.currentTimeMillis();
                separarCadena();
                 total = System.currentTimeMillis();
                 SecuenT.setText(total - inicio + " milisegundos // "+contadorPalabras+" palabras");
                 contadorPalabras = 0;
            }
        });
        this.add(btnStart);
        this.setLayout(null);
        this.setVisible(true);
        this.setBounds(50, 50, 1900, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Palindroma p = new Palindroma();

    }

    public void separarCadena() {
        String palabras = Palabras.getText();
        String[] ArregloPalabras = palabras.split(" ");
        contandoPalabras(ArregloPalabras);
    }

    public void contandoPalabras(String[] palabras) {
        for (int i = 0; i < palabras.length; i++) {
            int inc = 0;
            int des = palabras[i].length() - 1;
            boolean bError = false;

            while ((inc < des) && (!bError)) {

                if (palabras[i].charAt(inc) == palabras[i].charAt(des)) {
                    inc++;
                    des--;
                } else {
                    bError = true;
                }
            }

            if (!bError) {
                contadorPalabras++;
                //System.out.println(contadorPalabras);
                //System.out.println(palabras[i] + " es un PALINDROMO");
            } //else {
                //System.out.println(palabras[i] + " NO es un palindromo");
            //}
        }
    }
}
