package primerproyecto_angelvaquedano;

public class Torre extends Piezas {

    public Torre(int x, int y, boolean color) {
        super(x, y, "Torre", color);
    }

    @Override
    public boolean movimiento(int nX, int nY, Piezas[][] tablero) {
        if (x == nX || y == nY) {
            if (tablero[nX][nY] == null || tablero[nX][nY].getColor() != this.color) {
                return isPathClear(nX, nY, tablero);
            }
        }
        return false; 
    }

    private boolean isPathClear(int targetX, int targetY, Piezas[][] tablero) {
        if (x == targetX) {
            int direction = (targetY > y) ? 1 : -1;
            for (int casillaY = y + direction; casillaY != targetY; casillaY += direction) {
                if (tablero[targetX][casillaY] != null) {
                    return false; 
                }
            }
        } else if (y == targetY) {
            int direction = (targetX > x) ? 1 : -1;
            for (int casillaX = x + direction; casillaX != targetX; casillaX += direction) {
                if (tablero[casillaX][targetY] != null) {
                    return false; 
                }
            }
        }
        return true;
    }
}