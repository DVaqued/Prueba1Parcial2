package primerproyecto_angelvaquedano;

public class Cañon extends Piezas {

    public Cañon(int x, int y, boolean color) {
        super(x, y, "Cañon", color);
    }

    @Override
    public boolean movimiento(int destinoX, int destinoY, Piezas[][] tablero) {
        if (x == destinoX || y == destinoY) {
            int contadorPiezas = 0;
            int pasoX = (destinoX > x) ? 1 : (destinoX < x) ? -1 : 0;
            int pasoY = (destinoY > y) ? 1 : (destinoY < y) ? -1 : 0;

            int posX = x + pasoX;
            int posY = y + pasoY;

            while (posX != destinoX || posY != destinoY) {
                if (tablero[posX][posY] != null) {
                    contadorPiezas++;
                }
                posX += pasoX;
                posY += pasoY;
            }

            return (contadorPiezas == 1 && tablero[destinoX][destinoY] != null) || (contadorPiezas == 0 && tablero[destinoX][destinoY] == null);
        }

        return false;
    }
}
