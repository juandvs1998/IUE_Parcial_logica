package controladores;

import servicios.EnvioServicio;
import vistas.LogisticaVista;

public class LogisticaControlador {
    private LogisticaVista vista;

    public LogisticaControlador(LogisticaVista vista){
        this.vista=vista;
        this.vista.setGuardarEnvioClick(e->agregarEnvio());
        this.vista.setEliminarEnvioClick(e->quitarEnvio());
        mostrarEnvios();
    }

    private void mostrarEnvios(){
        vista.mostrarEnvios(EnvioServicio.getDatos(),EnvioServicio.getEncabezados());
    }

    private void agregarEnvio(){
        try{
            var tipo=vista.getTipoEnvioSeleccionado();
            var codigo=vista.getCodigo();
            var cliente=vista.getCliente();
            var peso=vista.getPeso();
            var distancia=vista.getDistancia();

            if(codigo.isBlank()){ vista.mostrarMensaje("Debe ingresar el número/código del envío."); return; }
            if(cliente.isBlank()){ vista.mostrarMensaje("Debe ingresar el cliente."); return; }
            if(peso<=0){ vista.mostrarMensaje("El peso debe ser mayor que cero."); return; }
            if(distancia<=0){ vista.mostrarMensaje("La distancia debe ser mayor que cero."); return; }

            EnvioServicio.agregar(tipo,codigo,cliente,peso,distancia);
            mostrarEnvios();
            vista.limpiarCampos();
            vista.ocultarPanelEditarEnvio();
        }catch(NumberFormatException ex){
            vista.mostrarMensaje("Peso y distancia deben ser valores numéricos.");
        }
    }

    private void quitarEnvio(){
        int fila=vista.getFilaEnvioSeleccionado();
        if(fila>=0){
            if(vista.confirmar("¿Está seguro de eliminar el envío?")){
                EnvioServicio.eliminar(fila);
                mostrarEnvios();
            }
        }else vista.mostrarMensaje("Debe seleccionar un envío.");
    }
}
