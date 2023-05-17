package pers.lige.sms.entity;
public class Clazz {
    private Integer id;
    private String name;
    private String number;
    private String introducation;
    private String coordinator;
    private String email;
    private String telephone;
    private String grade_name;

    public Clazz() {
    }

    public Clazz(Integer id, String name, String number, String introducation, String coordinator, String email, String telephone, String grade_name) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.introducation = introducation;
        this.coordinator = coordinator;
        this.email = email;
        this.telephone = telephone;
        this.grade_name = grade_name;
    }

    public Clazz(String name, String grade_name) {
       this.name = name;
       this.grade_name = grade_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", introducation='" + introducation + '\'' +
                ", coordinator='" + coordinator + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", grade_name='" + grade_name + '\'' +
                '}';
    }
}
