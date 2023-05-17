package pers.lige.sms.entity;
public class Grade {
    private Integer id;
    private String name;
    private String manager;
    private String email;
    private String telephone;
    private String introducation;

    public Grade() {
    }

    public Grade(Integer id, String name, String manager, String email, String telephone, String introducation) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.email = email;
        this.telephone = telephone;
        this.introducation = introducation;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public String getIntroducation() {
        return introducation;
    }

    public void setIntroducation(String introducation) {
        this.introducation = introducation;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", introducation='" + introducation + '\'' +
                '}';
    }
}
