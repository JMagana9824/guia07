/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstractGen;

/**
 *
 * @author kevin
 */
public abstract class BeanGenerico<T> implements Serializable {

    List<T> lista;

    public void crear() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().create(getEntity());
                llenar();
                enviarMensaje(false,"Registro creado correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                enviarMensaje(true,"Error al crear registro creado correctamente.");
            }
        }
    }

   
    public void modificar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().edit(getEntity());
                llenar();
                enviarMensaje(false,"Edicion realizada correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                enviarMensaje(true,"Error al editar registro.");
            }
        }
    }

    public void eliminar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().remove(getEntity());
                llenar();
                enviarMensaje(false,"Registro eliminado correctamente");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                enviarMensaje(true,"Error al eliminar registro");
            }
        }
    }

    public void llenar() {
        if (getFacadeLocal().findAll() != null) {
            this.lista = getFacadeLocal().findAll();
        } else {
            this.lista = Collections.EMPTY_LIST;
        }
    }

    public void enviarMensaje(boolean error, String mensaje) {
        if (error=false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", mensaje));
        }
    }

   
    protected abstract AbstractGen<T> getFacadeLocal();

    public abstract T getEntity();

}
