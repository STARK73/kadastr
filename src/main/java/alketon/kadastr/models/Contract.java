package alketon.kadastr.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Contract {

    public Contract() {
    }

    public Contract(Client client, Date dateOrder, String addressObject,
                    String typeProperty, String targetPlacing,
                    String entitlingType, String entitlingNum, Integer status) {
        this.client = client;
        this.dateOrder = dateOrder;
        this.addressObject = addressObject;
        this.typeProperty = typeProperty;
        this.targetPlacing = targetPlacing;
        this.entitlingType = entitlingType;
        this.entitlingNum = entitlingNum;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "date_closing")
    private Date dateClosing;

    @Column(name = "cadastral_num")
    private Integer cadastralNum;

    @Column(name = "address_object")
    private String addressObject;

    @Column(name = "type_property")
    private String typeProperty;

    @Column(name = "target_placing")
    private String targetPlacing;

    @Column(name = "entitling_type")
    private String entitlingType;

    @Column(name = "entitling_num")
    private String entitlingNum;

    @Column(name = "status")
    private Integer status;

    /*@OneToMany(mappedBy = "id_contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Statement> statements;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDateClosing() {
        return dateClosing;
    }

    public void setDateClosing(Date dateClosing) {
        this.dateClosing = dateClosing;
    }

    public Integer getCadastralNum() {
        return cadastralNum;
    }

    public void setCadastralNum(Integer cadastralNum) {
        this.cadastralNum = cadastralNum;
    }

    public String getAddressObject() {
        return addressObject;
    }

    public void setAddressObject(String addressObject) {
        this.addressObject = addressObject;
    }

    public String getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }

    public String getTargetPlacing() {
        return targetPlacing;
    }

    public void setTargetPlacing(String targetPlacing) {
        this.targetPlacing = targetPlacing;
    }

    public String getEntitlingType() {
        return entitlingType;
    }

    public void setEntitlingType(String entitlingType) {
        this.entitlingType = entitlingType;
    }

    public String getEntitlingNum() {
        return entitlingNum;
    }

    public void setEntitlingNum(String entitlingNum) {
        this.entitlingNum = entitlingNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

   /* public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }*/
}
