package movies.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="shopping")
public class Shopping {
	private int idShopping;
    private User users;
    private Date shoppingDate;
    private boolean state;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_shopping", unique=true, nullable=false)
    public int getIdShopping() {
        return this.idShopping;
    }
    
    public void setIdShopping(int idShopping) {
        this.idShopping = idShopping;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_users", nullable=false)
    public User getUsers() {
        return this.users;
    }
    
    public void setUsers(User users) {
        this.users = users;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="shopping_date", length=29)
    @CreationTimestamp
    public Date getShoppingDate() {
        return this.shoppingDate;
    }
    
    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }    
    
    @Column(name="state", nullable=false)
    public boolean isState() {
        return this.state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
    
}
