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
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstractInterface;

/**
 *
 * @author kevin
 */
public abstract class BeanGenerico<T> implements Serializable {

    List<T> lista;

      public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
}
    public void crear() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().create(getEntity());
                llenar();
                addMessage("Registro creado correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessage("Error al crear registro creado correctamente.");
            }
        }
    }

   
    public void modificar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().edit(getEntity());
                llenar();
                addMessage("Edicion realizada correctamente.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                addMessage("Error al editar registro.");
            }
        }
    }

    public void eliminar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().remove(getEntity());
                llenar();
               addMessage("Registro eliminado correctamente");
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
               addMessage("Error al eliminar registro");
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

 

   
    protected abstract AbstractInterface<T> getFacadeLocal();

    public abstract T getEntity();

}
