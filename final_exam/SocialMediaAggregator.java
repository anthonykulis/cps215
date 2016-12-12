package final_exam;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.Comparator;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;


public class SocialMediaAggregator {

	private final List<SocialMediaInterface> social = new ArrayList<>();

	public void addMedia(SocialMediaInterface item){
		this.social.add(item);
	}

	/* Transform methods */

	public ArrayList toArrayList(){
		return (ArrayList)this.social;
	}

	public TreeSet toTreeSet(){
		return new TreeSet(this.social);
	}

	/* Sorting */
	public List sortByAuthor(String direction){
		switch(direction){
			case "ASC":
				this.social.sort((s1, s2) -> s1.getAuthor().compareTo(s2.getAuthor()));
				break;
			case "DESC":
				this.social.sort((s1, s2) -> s2.getAuthor().compareTo(s1.getAuthor()));
				break;
			default:
		}

		return this.social;
	}

	public List sortByAIRating(String direction, List<SocialMediaInterface> optional){
		if(optional == null) optional = this.social;
		switch(direction){
			case "ASC":
				optional.sort((s1, s2) -> s1.getAIRating().compareTo(s2.getAIRating()));
				break;
			case "DESC":
				optional.sort((s1, s2) -> s2.getAIRating().compareTo(s1.getAIRating()));
				break;
			default:
		}
		return optional;
	}

	public List sortByType(String direction){
		switch(direction){
			case "ASC":
				this.social.sort((s1, s2) -> s1.getType().compareTo(s2.getType()));
				break;
			case "DESC":
				this.social.sort((s1, s2) -> s2.getType().compareTo(s1.getType()));
				break;
			default:
		}
		return this.social;
	}


	/* Filters */

	public List filterByType(String type){
		return this.social.parallelStream()
				.filter((s) -> type.equals(s.getType()))
				.collect(Collectors.toList());
	}

	public List filterByGender(String gender){
		return this.social.parallelStream()
				.filter((s) -> gender.equals(s.getGender()))
				.collect(Collectors.toList());
	}

	public List filterByAuthor(String author){
		return this.social.parallelStream()
				.filter((s) -> author.equals(s.getAuthor()))
				.collect(Collectors.toList());
	}

	public List filterByAIRatingRange(double low, double high){
		return this.social.parallelStream()
				.filter((s) -> {
					double rating = s.getAIRating();
					return rating <= high && rating >= low;
				})
				.collect(Collectors.toList());
	}


	/* Aggregations */

	public Map aggregateByAuthor(){
		return this.social.stream().collect(Collectors.groupingBy(SocialMediaInterface::getAuthor));
	}

	public Map aggregateByMediaType(){
		return this.social.stream().collect(Collectors.groupingBy(SocialMediaInterface::getType));
	}

	public Map aggregateByGender(List<SocialMediaInterface> optional){
		if(optional == null) optional = this.social;
		return optional.stream().collect(Collectors.groupingBy(SocialMediaInterface::getGender));
	}

	public Map aggregateByNegativeAndPositiveComments(){
		return this.social.stream().collect(Collectors.partitioningBy((s) -> s.getAIRating() >= 0));
	}

	public Map aggregateByAuthorsAverageRating(){
		return this.social.stream()
				.collect(Collectors.groupingBy(SocialMediaInterface::getAuthor,
												Collectors.averagingDouble(SocialMediaInterface::getAIRating)));
	}

	/* EC 1 */
	public Map aggregateFavorableRatedFacebookAndTwitter(){
		return this.social.stream()
				.filter(s -> (s.getType().equals("Facebook") || s.getType().equals("Twitter")) && s.getAIRating() > 0)
				.collect(Collectors.groupingBy(SocialMediaInterface::getType));

	}

	/* EC 2 */
	public Map filterFavorableRatedAscendingForTwitterAndAggreateForGender(){
		List l = this.social.stream()
					.filter(s -> (s.getType().equals("Twitter")) && s.getAIRating() > 0)
					.collect(Collectors.toList());
		l = this.sortByAIRating("ASC", l);
		return this.aggregateByGender(l);

	}

	/* EC 3 */
	public Map aggregateBestRatedFromEachMediaType(List<SocialMediaInterface> other){
		Comparator<SocialMediaInterface> byRating = Comparator.comparing(SocialMediaInterface::getAIRating);
		if(other == null) other = this.social;
		return other.stream()
				.collect(Collectors.groupingBy(SocialMediaInterface::getType, Collectors.reducing(BinaryOperator.maxBy
						(byRating))));
	}

	/* EC 4 */
	public HashMap aggregateBestRatedFromEachMediaTypeForEachGender(){
		Map<Object, List<SocialMediaInterface>> m = this.aggregateByGender(null);
		Set<Object> genders = m.keySet();
		Iterator it = genders.iterator();
		HashMap<Object, Map<Object, SocialMediaInterface>> aggr = new HashMap<>();
		while(it.hasNext()){
			Object gender = it.next();
			Map<Object, SocialMediaInterface> map = this.aggregateBestRatedFromEachMediaType(m.get(gender));
			System.out.println(map);
			aggr.put(gender, map);
		}

		return aggr;

	}




	public static void main(String[] args){
		SocialMediaAggregator sma = new SocialMediaAggregator();
		sma.setup();

		System.out.println(sma.aggregateByAuthorsAverageRating());


	}

	private void setup(){
		String[] names = {"Mary", "Sue", "Lady Gaga", "Anthony", "George", "Jeremy"};

		for(int i = 0; i < 50000; i++){
			int random = (int)(Math.random() * 3);
			int name = (int)(Math.random() * 6);
			double rating = Math.random() * 10 - 5;
			this.createMedia(random, names[name], rating, name);
		}
	}

	private void createMedia(int media, String name, double rating, int gender){
		switch (media){
			case 0:
				this.addMedia(new Facebook(name, "blah blah blah", rating, gender < 3 ? "female" : "male"));
				break;
			case 1:
				this.addMedia(new GooglePlus(name, "blah blah blah", rating, gender < 3 ? "female" : "male"));
				break;
			case 2:
				this.addMedia(new Twitter(name, "blah blah blah", rating, gender < 3 ? "female" : "male"));
		}
	}


}
