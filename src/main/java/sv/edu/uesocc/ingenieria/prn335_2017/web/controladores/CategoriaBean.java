/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Categoria;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.CategoriaFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstractInterface;

/**Declaracion de ManagedBean 
* @author kevin
* 
 */

@Named
@ViewScoped
public class CategoriaBean extends BeanGenerico<Categoria> implements Serializable {

   public CategoriaBean() {
    }
    
    boolean activo,visible=true,ver=false;

    public boolean isVisible() {
        return visible;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    /**
     * inyeccciones EJB
     */
    @EJB
    CategoriaFacadeLocal categoria;
    Categoria catEntity;
    List<Categoria> filtroCat= new ArrayList<>();
    
    
    public void limpiar(){
        catEntity.setIdCategoria(null);
        catEntity.setNombre(null);
        catEntity.setActivo(false);
        catEntity.setDescripcion(null);
    }
    
      @Override
    public Categoria getEntity() {
        return catEntity;
    }
    
    @Override
    protected AbstractInterface<Categoria> getFacadeLocal() {
        return categoria;
      
    }
    
    @Override
    public void modificar() {
        super.modificar();
        catEntity= new Categoria();
        ver=false;
       
    }

    @Override
    public void eliminar() {
        super.eliminar(); 
        catEntity= new Categoria();
        ver=false;
       
    }

    @Override
    public void crear() {
        super.crear();
        catEntity= new Categoria();
         
       
    }

/**metodo para seleccionar componentes de mi tabla
 * selecion  d objetos
 * @param event 
 */
     public void onRowSelect(SelectEvent event) {
       catEntity = (Categoria) event.getObject();
        visible=false;
        ver=true;
     }
  
  
     public void cancelar(){
         catEntity= new Categoria();
       visible=true;
         ver=false;
        
     }
     
    public void nuevo(){
      catEntity= new Categoria();
    visible=true;
    ver=true;
        
    }
     
     
    public List<Categoria> getFiltroCat() {
        return filtroCat;
    }
    public void setFiltroCat(List<Categoria> filtroCat) {
        this.filtroCat = filtroCat;
    }
    
    Categoria selectedCat;

    public Categoria getSelectedCat() {
        return selectedCat;
    }

    public void setSelectedCat(Categoria selectedCat) {
        this.selectedCat = selectedCat;
    }
   /**
    * controla la busqueda de las categorias no utilizadas
    * y utilizadas al ser seleccionado o no.
    */
    public void chkCambio(){
        if(activo == true){
            this.lista = obtenerUtilizados();
        }else{
            llenar();  
        }
    }
    /**
     * Sirve para filtrar las categorias que no han sido asignadas
     * en otras tablas
     * @return las categorias no asinadas
     */
        private List<Categoria> obtenerUtilizados() {
        List salida;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uesocc.edu.sv.ingenieria.prn335_webproject3_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query c = em.createNamedQuery("Categoria.asignados");
        salida = c.getResultList();
        
        if(salida != null){
        return salida;
        }else{
            return Collections.EMPTY_LIST;
        }
        }
        
      
   
    @PostConstruct
    public void init(){
     llenar();
    }
    
   
public boolean isActivo() {
        return activo;
    }

    public CategoriaFacadeLocal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaFacadeLocal categoria) {
        this.categoria = categoria;
    }

    public Categoria getCatEntity() {
        return catEntity;
    }

    public void setCatEntity(Categoria catEntity) {
        this.catEntity = catEntity;
    }

   

    public List<Categoria> getLista() {
        return lista;
    }

    public void setLista(List<Categoria> lista) {
        this.lista = lista;
    }
  
}
