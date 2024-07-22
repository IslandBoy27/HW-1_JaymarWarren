public class Multa {
    private int codigo;
    private String nombreInfractor;
    private String tipo;
    private double montoPagar;
    private boolean pagada;
    
    public static final double TIPO_GRAVE = 3000.0;
    public static final double TIPO_MEDIO = 1500.0;
    public static final double TIPO_LEVE = 500.0;

    public Multa(int codigo, String nombreInfractor, String tipo) {
        this.codigo = codigo;
        this.nombreInfractor = nombreInfractor;
        this.tipo = tipo.toUpperCase();
        this.pagada = false;
        switch (this.tipo) {
            case "GRAVE":
                this.montoPagar = TIPO_GRAVE;
                break;
            case "MEDIO":
                this.montoPagar = TIPO_MEDIO;
                break;
            case "LEVE":
                this.montoPagar = TIPO_LEVE;
                break;
            default:
                this.tipo = "LEVE";
                this.montoPagar = TIPO_LEVE;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public double getMontoPagar() {
        return montoPagar;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void print() {
        System.out.println("Código: " + codigo);
        System.out.println("Nombre del Infractor: " + nombreInfractor);
        System.out.println("Tipo de Multa: " + tipo);
        System.out.println("Monto a Pagar: Lps " + montoPagar);
        System.out.println(pagada ? "Pagada por un monto de Lps " + montoPagar : "Multa pendiente");
    }

    public void pay() {
        if (!pagada) {
            pagada = true;
            System.out.println("Multa con Código " + codigo + " fue pagada por un monto de Lps " + montoPagar);
        } else {
            System.out.println("Multa con Código " + codigo + " ya fue pagada.");
        }
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nombre del Infractor: " + nombreInfractor + ", Tipo de Multa: " + tipo + ", Monto a Pagar: Lps " + montoPagar + ", Estado: " + (pagada ? "Pagada" : "Pendiente");
    }
}
