package domain.entities.Models.Entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idConfig")
@Entity
@Table (name="configuracionentidadjuridica")
public class ConfiguracionEntidadJuridica {

    @Id
    @GeneratedValue
    private int idConfig;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name="keyValue")
    @Column(name="value")
    @CollectionTable(name="config_properties", joinColumns=@JoinColumn(name="idConfig"))
    private Map<String,String> configEntidadJuridica;

//    @Column (name="mapscheduler")
//    @Transient
//   private Map<String,Integer> mapScheduler;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "idEntidadjuridica")
    private EntidadJuridica entidadjuridica;


    public ConfiguracionEntidadJuridica() {
        configEntidadJuridica = new HashMap<>();
        //mapScheduler = new HashMap<>();
    }

    public int getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(int idConfig) {
        this.idConfig = idConfig;
    }

    public Map<String, String> getConfigEntidadJuridica() {
        return configEntidadJuridica;
    }

    public void setConfigEntidadJuridica(Map<String, String> configEntidadJuridica) {
        this.configEntidadJuridica = configEntidadJuridica;
    }

    public EntidadJuridica getEntidadjuridica() {
        return entidadjuridica;
    }

    public ConfiguracionEntidadJuridica setEntidadjuridica(EntidadJuridica entidadjuridica) {
        this.entidadjuridica = entidadjuridica;
        return this;
    }
}
