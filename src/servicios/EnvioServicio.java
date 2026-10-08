package servicios;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import modelos.Aereo;
import modelos.Envio;
import modelos.Maritimo;
import modelos.Terrestre;

public class EnvioServicio {

    private static final List<Envio> envios = new ArrayList<>();
    private static final DecimalFormat formato = new DecimalFormat("0.0");

    public static Envio agregar(String tipo, String codigo, String cliente,
                                 double peso, double distancia) {

        Envio envio;

        if (tipo.equals("Terrestre")) {
            envio = new Terrestre(codigo, cliente, peso, distancia);
        } else if (tipo.equals("Aéreo")) {
            envio = new Aereo(codigo, cliente, peso, distancia);
        } else {
            envio = new Maritimo(codigo, cliente, peso, distancia);
        }

        envios.add(envio);
        return envio;
    }

    public static void eliminar(int fila) {
        if (fila >= 0 && fila < envios.size()) {
            envios.remove(fila);
        }
    }

    public static Object[][] getDatos() {
        Object[][] datos = new Object[envios.size()][6];

        for (int i = 0; i < envios.size(); i++) {
            Envio envio = envios.get(i);

            // Orden igual al ejemplo del parcial:
            // Tipo, Código, Cliente, Peso, Distancia, Costo
            datos[i][0] = obtenerTipo(envio);
            datos[i][1] = envio.getCodigo();
            datos[i][2] = envio.getCliente();
            datos[i][3] = envio.getPeso();
            datos[i][4] = envio.getDistancia();
            datos[i][5] = formato.format(envio.calcularTarifa());
        }

        return datos;
    }

    private static String obtenerTipo(Envio envio) {
        if (envio instanceof Terrestre) {
            return "Terrestre";
        } else if (envio instanceof Aereo) {
            return "Aéreo";
        }

        return "Marítimo";
    }

    public static String[] getEncabezados() {
        return new String[] {
            "Tipo", "Código", "Cliente",
            "Peso", "Distancia", "Costo"
        };
    }
}
