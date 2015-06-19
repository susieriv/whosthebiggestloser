package whosthebiggestloser;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
@PersistenceCapable(identityType=IdentityType.APPLICATION)

public class Question {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	Long id;
	@Persistent
	String titre;
	@Persistent
	String reponse;
	@Persistent
	int question;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	/* NECESSAIRE ? >>> */
	public String getReponse() {return reponse;}
	public void setReponse(String reponse) {this.reponse = reponse;}
	/* NECESSAIRE ? <<< */
	public int getQuestion() {return question;}
	public void setQuestion(int question) {this.question = question;}
}