package alketon.kadastr.models;
/*
import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Statement {

    public Statement() {
    }

    public Statement(Integer idContract, ParamService idParamService) {
        this.idContract = idContract;
        this.idParamService = idParamService;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contract")
    private Integer idContract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_param_service")
    private ParamService idParamService;

    @Column(name = "date_geodesy")
    private Date dateGeodesy;

    @Column(name = "date_checks")
    private Date dateChecks;

    @Column(name = "date_xml")
    private Date dateXml;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
    }

    public ParamService getId_param_service() {
        return idParamService;
    }

    public void setId_param_service(ParamService id_param_service) {
        this.idParamService = id_param_service;
    }

    public Date getDateGeodesy() {
        return dateGeodesy;
    }

    public void setDateGeodesy(Date dateGeodesy) {
        this.dateGeodesy = dateGeodesy;
    }

    public Date getDateChecks() {
        return dateChecks;
    }

    public void setDateChecks(Date dateChecks) {
        this.dateChecks = dateChecks;
    }

    public Date getDateXml() {
        return dateXml;
    }

    public void setDateXml(Date dateXml) {
        this.dateXml = dateXml;
    }
}
*/