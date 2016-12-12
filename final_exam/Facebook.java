package final_exam;

public class Facebook extends SocialMediaPost implements SocialMediaInterface {

	public Facebook(String author, String message, double aiRating, String gender){
		super(author, message, aiRating, gender);
	}

	public String getType(){ return "Facebook"; }
}