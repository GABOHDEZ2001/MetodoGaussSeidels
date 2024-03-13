package MetodoGaussSeidel;
public class GaussSeidel {

    public static void main(String[] args) {
        double[][] sistemaEcuaciones = {
                {10, 2, -1, 27},
                {-3, -6, 2, -61},
                {1, 1, 5, -21}
        };

        double[] solucionesIniciales = {0, 0, 0};

        resolverSistema(sistemaEcuaciones, solucionesIniciales, 0.0001, 50);
    }

    public static void resolverSistema(double[][] matriz, double[] soluciones, double tolerancia, int iteracionesMax) {
        int filas = matriz.length;

        for (int iteracion = 1; iteracion <= iteracionesMax; iteracion++) {
            double[] nuevasSoluciones = new double[filas];

            for (int i = 0; i < filas; i++) {
                double suma = 0;
                for (int j = 0; j < filas; j++) {
                    if (j != i) {
                        suma += matriz[i][j] * soluciones[j];
                    }
                }
                nuevasSoluciones[i] = (matriz[i][filas] - suma) / matriz[i][i];
            }

            // Comprobar convergencia
            if (convergencia(soluciones, nuevasSoluciones, tolerancia)) {
                System.out.println("Soluciones convergen en la iteración " + iteracion + ":");
                mostrarSoluciones(nuevasSoluciones);
                return;
            }

            soluciones = nuevasSoluciones;
        }

        System.out.println("No se alcanzó convergencia después de " + iteracionesMax + " iteraciones.");
    }

    private static boolean convergencia(double[] anteriores, double[] actuales, double tolerancia) {
        for (int i = 0; i < anteriores.length; i++) {
            if (Math.abs(actuales[i] - anteriores[i]) > tolerancia) {
                return false;
            }
        }
        return true;
    }

    private static void mostrarSoluciones(double[] soluciones) {
        for (int i = 0; i < soluciones.length; i++) {
            System.out.println("x" + (i + 1) + " = " + soluciones[i]);
        }
    }
}

