package domain.entities.Models.Direccion;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Proveedor;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDireccion")
@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue
    private int idDireccion;

    @Column (name = "calle")
    private String calle;

    @Column (name = "altura")
    private int altura;

    @Column (name = "piso")
    private int piso;

    @Column (name = "depto")
    private String depto;

    /*@Column (name = "codPostal")
    private int codPostal;*/

    @Column (name = "codPostal")
    private boolean estado = true;
    
    @ManyToOne
    @JoinColumn(name = "idCiudad", referencedColumnName = "idCiudad")
    private Ciudad ciudad;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="idProveedor")
    private Proveedor proveedor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    public void darBaja(){
        this.estado = false;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    /*
    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

     */

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
}
