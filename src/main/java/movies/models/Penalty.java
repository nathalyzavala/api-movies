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

@Entity
@Table(name="penaltys")
public class Penalty {
	private int idPenaltys;
    private RentalDetail rentalDetail;
    private Date payDay;
    private boolean payment;
    private double penalty;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_penaltys", unique=true, nullable=false)
    public int getIdPenaltys() {
        return this.idPenaltys;
    }
    
    public void setIdPenaltys(int idPenaltys) {
        this.idPenaltys = idPenaltys;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_rental_detail", nullable=false)
    public RentalDetail getRentalDetail() {
        return this.rentalDetail;
    }
    
    public void setRentalDetail(RentalDetail rentalDetail) {
        this.rentalDetail = rentalDetail;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pay_day", length=29)
    public Date getPayDay() {
        return this.payDay;
    }
    
    /*@PrePersist
    public void setPayDay() {
        this.payDay = new Date();
    }*/
    
    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }
    
    @Column(name="payment", nullable=false)
    public boolean isPayment() {
        return this.payment;
    }
    
    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    
    @Column(name="penalty", precision=2)
    public double getPenalty() {
        return this.penalty;
    }
    
    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

}
