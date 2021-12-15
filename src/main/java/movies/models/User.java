 package movies.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
    private int idUsers;
    private String email;
    private String username;
    private String passwords;
    private boolean rol;
    private boolean state;
    
 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_users", unique=true, nullable=false)
    public int getIdUsers() {
        return this.idUsers;
    }
    
    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    @Column(name="email", nullable=false, length=150)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="username", nullable=false, length=100)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
    @Column(name="passwords", nullable=false, length=100)
    public String getPasswords() {
        return this.passwords;
    }
    
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    
    @Column(name="rol", nullable=false)
    public boolean isRol() {
        return this.rol;
    }
    
    public void setRol(boolean rol) {
        this.rol = rol;
    }
    
    @Column(name="state")
    public boolean isState() {
        return this.state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
}
