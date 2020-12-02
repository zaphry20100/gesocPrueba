package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDocumentoComercial")
@Entity
@Table (name = "documentocomercial")
public class DocumentoComercial {

    @Id
    @GeneratedValue
    private int idDocumentoComercial;

    @Column (name = "numeroDocCom")
    private int numeroDocCom;

    @Column (name = "path")
    private String path;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private boolean estado = true;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "idEgreso")
    private Egreso egreso;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "idPresupuesto")
    private Presupuesto presupuesto;

    public DocumentoComercial() {
    }

    public int getIdDocumentoComercial() {
        return idDocumentoComercial;
    }

    public void setIdDocumentoComercial(int idDocumentoComercial) {
        this.idDocumentoComercial = idDocumentoComercial;
    }

    public int getNumeroDocCom() {
        return numeroDocCom;
    }

    public void setNumeroDocCom(int numeroDocCom) {
        this.numeroDocCom = numeroDocCom;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void darBaja(){
        this.estado=false;
    }
}

