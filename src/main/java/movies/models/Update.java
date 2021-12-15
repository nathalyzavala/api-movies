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
@Table(name="updates")
public class Update {
	   private int idUpdates;
	     private Movie movies;
	     private Date dateUpdates;
	     private String record;
	     private Integer kind;
	     
	     @Id 
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	     
	     @Column(name="id_updates", unique=true, nullable=false)
	     public int getIdUpdates() {
	         return this.idUpdates;
	     }
	     
	     public void setIdUpdates(int idUpdates) {
	         this.idUpdates = idUpdates;
	     }

	 @ManyToOne(fetch=FetchType.LAZY)
	     @JoinColumn(name="id_movies", nullable=false)
	     public Movie getMovies() {
	         return this.movies;
	     }
	     
	     public void setMovies(Movie movies) {
	         this.movies = movies;
	     }

	     @Temporal(TemporalType.TIMESTAMP)
	     @Column(name="dateUpdates", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", length=29)
	     @CreationTimestamp
	     public Date getDateUpdates() {
	         return this.dateUpdates;
	     }
	     
	     /*@PrePersist
	     public void setDateUpdates() {
	         this.dateUpdates = new Date();
	     }*/
	     
	     public void setDateUpdates(Date dateUpdates) {
	         this.dateUpdates = dateUpdates;
	     }

	     
	     @Column(name="record", length=300)
	     public String getRecord() {
	         return this.record;
	     }
	     
	     public void setRecord(String record) {
	         this.record = record;
	     }

	     
	     @Column(name="kind")
	     public Integer getKind() {
	         return this.kind;
	     }
	     
	     public void setKind(Integer kind) {
	         this.kind = kind;
	     }

}
