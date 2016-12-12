package final_exam;

public class Twitter extends SocialMediaPost implements SocialMediaInterface {

	public Twitter(String author, String message, double aiRating, String gender){
		super(author, message, aiRating, gender);
	}

	public String getType(){ return "Twitter"; }
}