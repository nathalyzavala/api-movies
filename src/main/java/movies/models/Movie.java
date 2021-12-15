package movies.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {
	
	   private int idMovies;
	     private String title;
	     private String description;
	     private String image;
	     private String nameimage;
	     private int stock;
	     private double rentalPrice;
	     private double salePrice;
	     private boolean availability;

	   
	     @Id 
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	    
	    @Column(name="id_movies", unique=true, nullable=false)
	    public int getIdMovies() {
	        return this.idMovies;
	    }
	    
	    public void setIdMovies(int idMovies) {
	        this.idMovies = idMovies;
	    }

	    
	    @Column(name="title", nullable=false, length=100)
	    public String getTitle() {
	        return this.title;
	    }
	    
	    public void setTitle(String title) {
	        this.title = title;
	    }

	    
	    @Column(name="description", nullable=false, length=3000)
	    public String getDescription() {
	        return this.description;
	    }
	    
	    public void setDescription(String description) {
	        this.description = description;
	    }

	    
	    @Column(name="image", length=100)
	    public String getImage() {
	        return this.image;
	    }
	    
	    public void setImage(String image) {
	        this.image = image;
	    }

	    
	    @Column(name="nameimage", length=100)
	    public String getNameimage() {
	        return this.nameimage;
	    }
	    
	    public void setNameimage(String nameimage) {
	        this.nameimage = nameimage;
	    }

	    
	    @Column(name="stock", nullable=false)
	    public int getStock() {
	        return this.stock;
	    }
	    
	    public void setStock(int stock) {
	        this.stock = stock;
	    }

	    
	    @Column(name="rental_price", nullable=false, precision=10, scale =2)
	    public double getRentalPrice() {
	        return this.rentalPrice;
	    }
	    
	    public void setRentalPrice(double rentalPrice) {
	        this.rentalPrice = rentalPrice;
	    }

	    
	    @Column(name="sale_price", nullable=false, precision=10, scale =2)
	    public double getSalePrice() {
	        return this.salePrice;
	    }
	    
	    public void setSalePrice(double salePrice) {
	        this.salePrice = salePrice;
	    }

	    
	    @Column(name="availability", nullable=false)
	    public boolean isAvailability() {
	        return this.availability;
	    }
	    
	    public void setAvailability(boolean availability) {
	        this.availability = availability;
	    }



}