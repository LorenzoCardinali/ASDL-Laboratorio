/**
 *
 */
package it.unicam.cs.asdl2021.slides.equazioni2g;

import javax.swing.JOptionPane;

/**
 * Graphical User Interface (GUI) in Swing per permettere l'uso interattivo
 * delle classi di <i>core business logic</i> che risolvono le equazioni di
 * secondo grado, in particolare le classi EquazioneSecondoGrado,
 * SoluzioneEquazioneSecondoGrado e RisolutoreEquazioniSecondoGrado.
 *
 * Le componenti della GUI sono state generate automaticamente con Apache
 * NetBeans IDE 12.0 - https://netbeans.org/
 *
 * @author Luca Tesei
 */
public class EquazioniGUIFrontEnd extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    /*
     * Costante piccola per il confronto di due numeri double
     */
    private static final double EPSILON = 1.0E-15;

    /*
     * Risolutore per tutte le equazioni generate con la GUI
     */
    private static final RisolutoreEquazioniSecondoGrado solver = new RisolutoreEquazioniSecondoGrado();

    /**
     * Crea un EquazioniGUIFrontEnd
     */
    public EquazioniGUIFrontEnd() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        solveButton = new javax.swing.JButton();
        aInput = new javax.swing.JTextField();
        bInput = new javax.swing.JTextField();
        cInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sol1Output = new javax.swing.JLabel();
        sol2Output = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Risolutore Equazioni di Secondo Grado");
        setMinimumSize(new java.awt.Dimension(425, 207));

        solveButton.setText("Risolvi");
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        aInput.setText("1.0");

        bInput.setText("-3.0");

        cInput.setText("2.0");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Parametro a");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Parametro b");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Parametro c");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Soluzione 1");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Soluzione 2");

        sol1Output.setText("2.0");

        sol2Output.setText("1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(4, 4, 4))
                                        .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGroup(layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(
                                                                        jLabel4)
                                                                .addComponent(
                                                                        jLabel3))
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cInput)
                                        .addComponent(bInput,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                78, Short.MAX_VALUE)
                                        .addComponent(aInput)))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45).addComponent(solveButton)))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(33, 33, 33)
                                        .addComponent(sol1Output,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(33, 33, 33)
                                        .addComponent(sol2Output,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)))
                        .addGap(73, 73, 73)));
        layout.setVerticalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5).addComponent(sol1Output))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(
                                javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6).addComponent(sol2Output))
                        .addGap(84, 84, 84))
                .addGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(aInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(
                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cInput,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(18, 18, 18).addComponent(solveButton))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap().addComponent(jSeparator1))));

        pack();
    }// </editor-fold>

    /*
     * Metodo che chiama le classi di <i>core business logic</i> quando viene
     * premuto il tasto "Risolvi" dell'interfaccia.
     */
    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // dichiaro e inizializzo i double per i parametri
        double a = 0;
        double b = 0;
        double c = 0;
        // leggo il valore corrente di a nella GUI
        try {
            a = Double.parseDouble(aInput.getText());
        } catch (NumberFormatException e) {
            // Messaggio di errore
            JOptionPane.showMessageDialog(this,
                    "Il parametro a deve essere un numero");
            return;
        }
        // controllo se a è zero
        if (Math.abs(a) < EPSILON) {
            // Messaggio di errore
            JOptionPane.showMessageDialog(this,
                    "Il parametro a non può essere zero");
            return;
        }
        // leggo il valore corrente di b nella GUI
        try {
            b = Double.parseDouble(bInput.getText());
        } catch (NumberFormatException e) {
            // Messaggio di errore
            JOptionPane.showMessageDialog(this,
                    "Il parametro b deve essere un numero");
            return;
        }
        // leggo il valore corrente di c nella GUI
        try {
            c = Double.parseDouble(cInput.getText());
        } catch (NumberFormatException e) {
            // Messaggio di errore
            JOptionPane.showMessageDialog(this,
                    "Il parametro c deve essere un numero");
            return;
        }
        // Creo una nuova equazione con i parametri letti e la risolvo
        EquazioneSecondoGrado e = new EquazioneSecondoGrado(a, b, c);
        SoluzioneEquazioneSecondoGrado s = solver.solve(e);
        // Mostro i risultati
        // caso senza soluzioni
        if (s.isEmptySolution()) {
            sol1Output.setText("undef");
            sol2Output.setText("undef");
            return;
        }
        // caso con due soluzioni coincidenti
        if (s.isOneSolution()) {
            Double s1 = new Double(s.getS1());
            sol1Output.setText(s1.toString());
            sol2Output.setText("undef");
            return;
        }
        // caso con due soluzioni distinte
        Double s1 = new Double(s.getS1());
        sol1Output.setText(s1.toString());
        Double s2 = new Double(s.getS2());
        sol2Output.setText(s2.toString());
    }

    /**
     * Main della classe GUI
     *
     * @param args
     *                 the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting
        // code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
         * html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EquazioniGUIFrontEnd.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EquazioniGUIFrontEnd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField aInput;

    private javax.swing.JTextField bInput;

    private javax.swing.JTextField cInput;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JSeparator jSeparator1;

    private javax.swing.JLabel sol1Output;

    private javax.swing.JLabel sol2Output;

    private javax.swing.JButton solveButton;
    // End of variables declaration
}
