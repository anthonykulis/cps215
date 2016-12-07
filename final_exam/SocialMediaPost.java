package final_exam;

abstract class SocialMediaPost {
	private final String author;
	private final String message;
	private final Double aiRating;
	private final String gender;

	SocialMediaPost(String author, String message, double aiRating, String gender){
		this.author = author;
		this.message = message;
		this.aiRating = aiRating;
		this.gender = gender;
	}

	public String getAuthor(){ return this.author; }
	public String getMessage(){ return this.message; }
	public Double getAIRating(){ return this.aiRating; }
	public String getGender(){ return this.gender; }
	public String getType(){ return ""; }
	public String toString(){ return "<< " + this.getType() + " >> " + this.author + " >>> " + this.message + " | " +
			this.getAIRating(); }
}