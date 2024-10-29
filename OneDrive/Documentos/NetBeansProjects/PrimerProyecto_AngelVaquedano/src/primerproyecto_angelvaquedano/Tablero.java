package primerproyecto_angelvaquedano;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JFrame {

    private JButton[][] botones = new JButton[10][9];
    private Piezas[][] piezas = new Piezas[10][9];
    private JButton selectedButton = null;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private boolean turno = true; // turno Rojo true, turno Negro false
    private JLabel turnoLabel=new JLabel();
    private int logIndex = 0;
    private String turnoJugador;
    

    Jugador jC = new Jugador();
    SistemaJugadores sJ = new SistemaJugadores();
    MenuPrincipal mP = new MenuPrincipal();

    public Tablero() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelTablero = new JPanel(new GridLayout(10, 9));
        turnoLabel = new JLabel("Turno: " + sJ.getJugador1().getUsername()  , SwingConstants.CENTER);
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inicializarPiezas();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.LIGHT_GRAY);
                setIconFromPieza(i, j);
                final int row = i;
                final int col = j;

                botones[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });

                panelTablero.add(botones[i][j]);
            }
        }

        JButton botonRendirse = new JButton("Rendirse");
        botonRendirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turno) {
                    JOptionPane.showMessageDialog(null, "Jugador Rojo se ha rendido. Jugador Negro gana.");
                    agregarLog(sJ.getJugador2(), sJ.getJugador1());
                    sJ.asignarPuntos(sJ.getJugador2(), 3);
                    sJ.asignarJugador1(null);
                    sJ.asignarJugador2(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Jugador Negro se ha rendido. Jugador Rojo gana.");
                    agregarLog(sJ.getJugador1(), sJ.getJugador2());
                    sJ.asignarPuntos(sJ.getJugador1(), 3);
                    sJ.asignarJugador1(null);
                    sJ.asignarJugador2(null);
                }
                Tablero.this.dispose();
                mP.setVisible(true);
            }
        });

        this.dispose();
        add(turnoLabel, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(botonRendirse, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void inicializarPiezas() {
 piezas[0][0] = new Torre(0, 0, false);
        piezas[0][1] = new Caballo(0, 1, false);
        piezas[0][2] = new Elefante(0, 2, false);
        piezas[0][3] = new Consejero(0, 3, false);
        piezas[0][4] = new Rey(0, 4, false);
        piezas[0][5] = new Consejero(0, 5, false);
        piezas[0][6] = new Elefante(0, 6, false);
        piezas[0][7] = new Caballo(0, 7, false);
        piezas[0][8] = new Torre(0, 8, false);
        piezas[2][1] = new Cañon(2, 1, false);
        piezas[2][7] = new Cañon(2, 7, false);
        piezas[3][0] = new Peon(3, 0, false);
        piezas[3][2] = new Peon(3, 2, false);
        piezas[3][4] = new Peon(3, 4, false);
        piezas[3][6] = new Peon(3, 6, false);
        piezas[3][8] = new Peon(3, 8, false);

        piezas[9][0] = new Torre(9, 0, true);
        piezas[9][1] = new Caballo(9, 1, true);
        piezas[9][2] = new Elefante(9, 2, true);
        piezas[9][3] = new Consejero(9, 3, true);
        piezas[9][4] = new Rey(9, 4, true);
        piezas[9][5] = new Consejero(9, 5, true);
        piezas[9][6] = new Elefante(9, 6, true);
        piezas[9][7] = new Caballo(9, 7, true);
        piezas[9][8] = new Torre(9, 8, true);
        piezas[7][1] = new Cañon(7, 1, true);
        piezas[7][7] = new Cañon(7, 7, true);
        piezas[6][0] = new Peon(6, 0, true);
        piezas[6][2] = new Peon(6, 2, true);
        piezas[6][4] = new Peon(6, 4, true);
        piezas[6][6] = new Peon(6, 6, true);
        piezas[6][8] = new Peon(6, 8, true);    }

    private void setIconFromPieza(int i, int j) {
        if (piezas[i][j] != null) {
            String path = "/Imagenes/" + piezas[i][j].getNombre() + (piezas[i][j].getColor() ? " Rojo" : " Negro") + ".png";
            setIconButton(botones[i][j], path);
        }
    }

    private void setIconButton(JButton button, String path) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(path));
            if (icon.getImage() != null) {
                Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            }
        } catch (Exception e) {
        }
    }

    public boolean reyEnTablero(boolean color) {
        for (int i = 0; i < piezas.length; i++) {
            for (int j = 0; j < piezas[i].length; j++) {
                if (piezas[i][j] instanceof Rey && piezas[i][j].getColor() == color) {
                    return true;
                }
            }
        }
        return false;
    }

    private void handleButtonClick(int row, int col) {
        JButton button = botones[row][col];

        if (selectedButton == null) {
            if (button.getIcon() != null) {
                Piezas piezaSeleccionada = piezas[row][col];

                if (piezaSeleccionada.getColor() == turno) {
                    selectedButton = button;
                    selectedRow = row;
                    selectedCol = col;
                    button.setBackground(Color.BLUE);
                    System.out.println("Pieza seleccionada en [" + selectedRow + "][" + selectedCol + "]");
                } else {
                    System.out.println("Es el turno del otro jugador.");
                }
            }
        } else {
            Piezas piezaSeleccionada = piezas[selectedRow][selectedCol];
            Piezas piezaDestino = piezas[row][col];

            if (piezaSeleccionada.movimiento(row, col, piezas)) {
                if (piezaDestino == null || piezaDestino.getColor() != piezaSeleccionada.getColor()) {
                    piezas[row][col] = piezaSeleccionada;
                    piezas[selectedRow][selectedCol] = null;
                    piezaSeleccionada.setX(row);
                    piezaSeleccionada.setY(col);
                    button.setIcon(selectedButton.getIcon());
                    selectedButton.setIcon(null);

                    if (turno) {
                        if (!reyEnTablero(false)) {
                            JOptionPane.showMessageDialog(null, "JUGADOR " + sJ.getJugador1().getUsername() + " VENCIO A JUGADOR " + sJ.getJugador2().getUsername() + ", FELICIDADES HAS GANADO 3 PUNTOS");
                            sJ.asignarPuntos(sJ.getJugador1(), 3);
                            agregarLog(sJ.getJugador1(), sJ.getJugador2());
                            sJ.asignarJugador1(null);
                            sJ.asignarJugador2(null);
                            mP.setVisible(true);
                            this.dispose();
                        }
                        turno = false;
                        cambiarTurno();
                    } else {
                        if (!reyEnTablero(true)) {
                            JOptionPane.showMessageDialog(null, "JUGADOR " + sJ.getJugador2().getUsername() + " VENCIO A JUGADOR " + sJ.getJugador1().getUsername() + ", FELICIDADES HAS GANADO 3 PUNTOS");
                            sJ.asignarPuntos(sJ.getJugador2(), 3);
                            agregarLog(sJ.getJugador2(), sJ.getJugador1());
                            sJ.asignarJugador1(null);
                            sJ.asignarJugador2(null);
                            mP.setVisible(true);
                            this.dispose();
                        }
                        turno = true;
                        cambiarTurno();
                    }
                } else {
                    System.out.println("Movimiento inválido.");
                }

                selectedButton.setBackground(Color.LIGHT_GRAY);
                selectedButton = null;
                selectedRow = -1;
                selectedCol = -1;
            }
        }
    }

    private void agregarLog(Jugador ganador, Jugador perdedor) {
        if (jC.getLogs() != null && logIndex < jC.getLogs().length) {
            jC.getLogs()[logIndex] = "El jugador " + ganador.getUsername() + " ha vencido a " + perdedor.getUsername() + ".";
            logIndex++;
        }
    }

    public void cambiarTurno() {
        if (turno) {
            turnoJugador = sJ.getJugador1().getUsername();
            turnoLabel.setText("Turno: " + turnoJugador);
        } else {
            turnoJugador = sJ.getJugador2().getUsername();
            turnoLabel.setText("Turno: " + turnoJugador);
        }
    }

    public static void main(String[] args) {
        new Tablero();
    }
}
