import controladores.LogisticaControlador;
import vistas.LogisticaVista;

public class App {
    public static void main(String[] args){
        var vista=new LogisticaVista();
        new LogisticaControlador(vista);
        vista.setVisible(true);
    }
}
