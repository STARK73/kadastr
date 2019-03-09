package alketon.kadastr.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ShowList.class)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Column(name = "date_order")
    @JsonView(Views.ShowList.class)
    private Date dateOrder;

    @Column(name = "date_closing")
    @JsonView(Views.FullContract.class)
    private Date dateClosing;

    @Column(name = "cadastral_num")
    @JsonView(Views.FullContract.class)
    private String cadastralNum;

    @Column(name = "address_object")
    @JsonView(Views.ShowList.class)
    private String addressObject;

    @Column(name = "type_property")
    @JsonView(Views.FullContract.class)
    private String typeProperty;

    @Column(name = "target_placing")
    @JsonView(Views.FullContract.class)
    private String targetPlacing;

    @Column(name = "entitling_type")
    @JsonView(Views.FullContract.class)
    private String entitlingType;

    @Column(name = "entitling_num")
    @JsonView(Views.FullContract.class)
    private String entitlingNum;

    @Column(name = "status")
    @JsonView(Views.ShowList.class)
    private Integer status;

    @Column(name = "date_geodesy")
    @JsonView(Views.FullContract.class)
    private String dateGeodesy;

    @Column(name = "date_checks")
    @JsonView(Views.FullContract.class)
    private String dateChecks;

    @Column(name = "date_xml")
    @JsonView(Views.FullContract.class)
    private String dateXml;

    @Column(name = "family_name")
    @JsonView(Views.ShowList.class)
    private String familyName;

    @Column(name = "first_name")
    @JsonView(Views.ShowList.class)
    private String firstName;

    @Column(name = "patronymic_name")
    @JsonView(Views.ShowList.class)
    private String patronymicName;

    @Column(name = "date_birth")
    @JsonView(Views.FullContract.class)
    private String dateBirth;

    @Column(name = "pas_serial_number")
    @JsonView(Views.FullContract.class)
    private String pasSerialNumber;

    @Column(name = "pas_issued")
    @JsonView(Views.FullContract.class)
    private String pasIssued;

    @Column(name = "pas_date")
    @JsonView(Views.FullContract.class)
    private String pasDate;

    @Column(name = "address_residence")
    @JsonView(Views.FullContract.class)
    private String addressResidence;

    @Column(name = "address_registration")
    @JsonView(Views.FullContract.class)
    private String addressRegistration;

    @Column(name = "snils")
    @JsonView(Views.FullContract.class)
    private String snils;

    @Column(name = "phone_mobile")
    @JsonView(Views.FullContract.class)
    private String phoneMobile;

    @Column(name = "phone_job")
    @JsonView(Views.FullContract.class)
    private String phoneJob;

    @Column(name = "phone_home")
    @JsonView(Views.FullContract.class)
    private String phoneHome;

    @Column(name = "email")
    @JsonView(Views.FullContract.class)
    private String email;

    @Column(name = "proxy")
    @JsonView(Views.FullContract.class)
    private String proxy;

    @Column(name = "order_mej")
    @JsonView(Views.ShowList.class)
    private String orderMej;

    @Column(name = "order_mej_obr")
    @JsonView(Views.ShowList.class)
    private String orderMejObr;

    @Column(name = "order_shem_rasp")
    @JsonView(Views.ShowList.class)
    private String orderShemRasp;

    @Column(name = "order_tex_plan")
    @JsonView(Views.ShowList.class)
    private String orderTexPlan;

    @Column(name = "order_sogl_opr")
    @JsonView(Views.ShowList.class)
    private String orderSoglOpr;

    @Column(name = "order_vun")
    @JsonView(Views.ShowList.class)
    private String orderVun;

    @Column(name = "order_mez_sx")
    @JsonView(Views.ShowList.class)
    private String orderMezSX;

    @Column(name = "order_akt_obs")
    @JsonView(Views.ShowList.class)
    private String orderAktObs;

    @Column(name = "number")
    @JsonView(Views.FullContract.class)
    private String number;

    @Column(name = "sum")
    @JsonView(Views.FullContract.class)
    private String sum;

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getOrderMejObr() {
        return orderMejObr;
    }

    public void setOrderMejObr(String orderMejObr) {
        this.orderMejObr = orderMejObr;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderMej() {
        return orderMej;
    }

    public void setOrderMej(String orderMej) {
        this.orderMej = orderMej;
    }

    public String getOrderShemRasp() {
        return orderShemRasp;
    }

    public void setOrderShemRasp(String orderShemRasp) {
        this.orderShemRasp = orderShemRasp;
    }

    public String getOrderTexPlan() {
        return orderTexPlan;
    }

    public void setOrderTexPlan(String orderTexPlan) {
        this.orderTexPlan = orderTexPlan;
    }

    public String getOrderSoglOpr() {
        return orderSoglOpr;
    }

    public void setOrderSoglOpr(String orderSoglOpr) {
        this.orderSoglOpr = orderSoglOpr;
    }

    public String getOrderVun() {
        return orderVun;
    }

    public void setOrderVun(String orderVun) {
        this.orderVun = orderVun;
    }

    public String getOrderMezSX() {
        return orderMezSX;
    }

    public void setOrderMezSX(String orderMezSX) {
        this.orderMezSX = orderMezSX;
    }

    public String getOrderAktObs() {
        return orderAktObs;
    }

    public void setOrderAktObs(String orderAktObs) {
        this.orderAktObs = orderAktObs;
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCadastralNum() {
        return cadastralNum;
    }

    public void setCadastralNum(String cadastralNum) {
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

    public String getDateGeodesy() {
        return dateGeodesy;
    }

    public void setDateGeodesy(String dateGeodesy) {
        this.dateGeodesy = dateGeodesy;
    }

    public String getDateChecks() {
        return dateChecks;
    }

    public void setDateChecks(String dateChecks) {
        this.dateChecks = dateChecks;
    }

    public String getDateXml() {
        return dateXml;
    }

    public void setDateXml(String dateXml) {
        this.dateXml = dateXml;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPasSerialNumber() {
        return pasSerialNumber;
    }

    public void setPasSerialNumber(String pasSerialNumber) {
        this.pasSerialNumber = pasSerialNumber;
    }

    public String getPasIssued() {
        return pasIssued;
    }

    public void setPasIssued(String pasIssued) {
        this.pasIssued = pasIssued;
    }

    public String getPasDate() {
        return pasDate;
    }

    public void setPasDate(String pasDate) {
        this.pasDate = pasDate;
    }

    public String getAddressResidence() {
        return addressResidence;
    }

    public void setAddressResidence(String addressResidence) {
        this.addressResidence = addressResidence;
    }

    public String getAddressRegistration() {
        return addressRegistration;
    }

    public void setAddressRegistration(String addressRegistration) {
        this.addressRegistration = addressRegistration;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneJob() {
        return phoneJob;
    }

    public void setPhoneJob(String phoneJob) {
        this.phoneJob = phoneJob;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
