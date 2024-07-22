public class Transito {
    private Multa[] multas;
    private int contador;

    public Transito() {
        multas = new Multa[100];
        contador = 0;
    }

    public Multa buscarMulta(int codigo) {
        for (int i = 0; i < contador; i++) {
            if (multas[i].getCodigo() == codigo) {
                return multas[i];
            }
        }
        return null;
    }

    public void agregarMulta(int codigo, String nombre, String tipo) {
        if (contador >= multas.length) {
            System.out.println("No hay espacio para más multas.");
            return;
        }
        if (buscarMulta(codigo) != null) {
            System.out.println("Código de multa ya existe.");
            return;
        }
        multas[contador] = new Multa(codigo, nombre, tipo);
        contador++;
    }

    public void pagarMulta(int codigo) {
        Multa multa = buscarMulta(codigo);
        if (multa != null) {
            multa.pay();
        } else {
            System.out.println("No existe la multa con el código " + codigo);
        }
    }

    public void imprimirMultas() {
        for (int i = 0; i < contador; i++) {
            multas[i].print();
            System.out.println("-----------------------");
        }
    }

    public int getContador() {
        return contador;
    }

    public Multa[] getMultas() {
        return multas;
    }

    public void informacionSistema() {
        int multasPagadas = 0;
        int multasPendientes = 0;
        double montoPagado = 0;
        double montoPendiente = 0;

        for (int i = 0; i < contador; i++) {
            if (multas[i].isPagada()) {
                multasPagadas++;
                montoPagado += multas[i].getMontoPagar();
            } else {
                multasPendientes++;
                montoPendiente += multas[i].getMontoPagar();
            }
        }

        System.out.println("Cantidad de Multas generadas: " + contador);
        System.out.println("Cantidad de Multas Pagadas: " + multasPagadas + " por un monto de Lps " + montoPagado);
        System.out.println("Cantidad de Multas pendientes: " + multasPendientes + " por un monto de Lps " + montoPendiente);
    }
}
