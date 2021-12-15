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
@Table(name="shopping_detail")
public class ShoppingDetail {
	private int idShoppingDetail;
    private Movie movies;
    private Shopping shopping;
    private Integer quantity;
    private double price;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_shopping_detail", unique=true, nullable=false)
    public int getIdShoppingDetail() {
        return this.idShoppingDetail;
    }
    
    public void setIdShoppingDetail(int idShoppingDetail) {
        this.idShoppingDetail = idShoppingDetail;
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
    @JoinColumn(name="id_shopping", nullable=false)
    public Shopping getShopping() {
        return this.shopping;
    }
    
    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
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
