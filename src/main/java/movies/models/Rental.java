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
@Table(name="rental")
public class Rental {
	private int idRental;
    private User users;
    private Date rentalDate;
    private Date returnDate;
    private boolean state;
    
 @Id 
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_rental", unique=true, nullable=false)
    public int getIdRental() {
        return this.idRental;
    }
    
    public void setIdRental(int idRental) {
        this.idRental = idRental;
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
    @Column(name="rental_date", length=29)
    @CreationTimestamp
    public Date getRentalDate() {
        return this.rentalDate;
    }
    
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="return_date", length=29)
  //  @CreationTimestamp
    public Date getReturnDate() {
        return this.returnDate;
    }
 
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    @Column(name="state", nullable=false)
    public boolean isState() {
        return this.state;
    }
    
    public void setState(boolean state) {
        this.state = state;
    }
    
}
