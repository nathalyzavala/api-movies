package movies.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rental_detail")
public class RentalDetail {
	private int idRentalDetail;
    private Movie movies;
    private Rental rental;
    private Integer quantity;
    private double price;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_rental_detail", unique=true, nullable=false)
    public int getIdRentalDetail() {
        return this.idRentalDetail;
    }
    
    public void setIdRentalDetail(int idRentalDetail) {
        this.idRentalDetail = idRentalDetail;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_movies", nullable=false)
    public Movie getMovies() {
        return this.movies;
    }
    
    public void setMovies(Movie movies) {
        this.movies = movies;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_rental", nullable=false)
    public Rental getRental() {
        return this.rental;
    }
    
    public void setRental(Rental rental) {
        this.rental = rental;
    }

    
    @Column(name="quantity")
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="price", precision=10)
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
}
