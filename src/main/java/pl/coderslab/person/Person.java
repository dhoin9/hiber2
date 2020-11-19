package pl.coderslab.person;


import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String email;
//    @OneToOne
//    @JoinColumn(name = "personDetails_id", unique = true)
//    private PersonDetails personDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public PersonDetails getPersonDetails() {
//        return personDetails;
//    }
//
//    public void setPersonDetails(PersonDetails personDetails) {
//        this.personDetails = personDetails;
//    }
}
