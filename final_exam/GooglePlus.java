package final_exam;

public class GooglePlus extends SocialMediaPost implements SocialMediaInterface {

	public GooglePlus(String author, String message, double aiRating, String gender){
		super(author, message, aiRating, gender);
	}

	public String getType(){ return "Google+"; }
}