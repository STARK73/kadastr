package alketon.kadastr.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Client {

    public Client() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_name")
    @JsonView(Views.ClientData.class)
    private String familyName;

    @Column(name = "first_name")
    @JsonView(Views.ClientData.class)
    private String firstName;

    @Column(name = "patronymic_name")
    @JsonView(Views.ClientData.class)
    private String patronymicName;

    @Column(name = "date_birth")
    @JsonView(Views.ClientData.class)
    private String dateBirth;

    @Column(name = "pas_serial_number")
    @JsonView(Views.ClientData.class)
    private String pasSerialNumber;

    @Column(name = "pas_issued")
    @JsonView(Views.ClientData.class)
    private String pasIssued;

    @Column(name = "pas_date")
    @JsonView(Views.ClientData.class)
    private String pasDate;

    @Column(name = "address_residence")
    @JsonView(Views.ClientData.class)
    private String addressResidence;

    @Column(name = "address_registration")
    @JsonView(Views.ClientData.class)
    private String addressRegistration;

    @Column(name = "snils")
    @JsonView(Views.ClientData.class)
    private String snils;

    @Column(name = "phone_mobile")
    @JsonView(Views.ClientData.class)
    private String phoneMobile;

    @Column(name = "phone_job")
    @JsonView(Views.ClientData.class)
    private String phoneJob;

    @Column(name = "phone_home")
    @JsonView(Views.ClientData.class)
    private String phoneHome;

    @Column(name = "email")
    @JsonView(Views.ClientData.class)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Contract> contracts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public void addContract(Contract contract) {
        contract.setClient(this);
        contracts.add(contract);
    }
}
