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
@Table(name="reactions")
public class Reaction {
	private int idReaction;
    private Movie movies;
    private User users;
    private boolean reaction;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id_reaction", unique=true, nullable=false)
    public int getIdReaction() {
        return this.idReaction;
    }
    
    public void setIdReaction(int idReaction) {
        this.idReaction = idReaction;
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
    @JoinColumn(name="id_users", nullable=false)
    public User getUsers() {
        return this.users;
    }
    
    public void setUsers(User users) {
        this.users = users;
    }

    
    @Column(name="reaction", nullable=false)
    public boolean isReaction() {
        return this.reaction;
    }
    
    public void setReaction(boolean reaction) {
        this.reaction = reaction;
    }

}
