# Create `SocialMediaAggregator` - 155 Points
The goal is to write a class to aggregate data. This class will be able to sort 6 different ways, filter by 4 categories, and aggregate in progressively complex ways. You will also be required to provide two transform methods.

The test will be out of 155 Points, but extra credit is given as percentage point. This means they count towards your overall test percentage (towards best 3 out of 4 exams total percentage).

## Supportive Classes (40 Points)

* There are 3 social media types that we will consider for the exam, `Facebook`, `Twitter`, and `GooglePlus` - 30 points
  * All three types should be able to provide their type, author of post, the post, gender of author, and an AI rating.

* You need to guarantee that these methods are available for any social media type that may come in the future. This means you need some form of polymorphism via interfaces or generic bounding. - 10 points

### Supportive Class Properties

* Type: the type of the media, eg "Facebook"
* Author: the user who wrote the post
* Message: the post itself
* Gender: The gender of the author
* AI Rating: a decimal value between -5 and 5. For the purpose of this exam, this rating is randomly assigned.

## SocialMediaAggregator

This class will be required to do collection transforms, sorting, filtering, and aggregation.

### Collection Transforms - (10 Points)

1. `toArrayList` - converts Collection to ArrayList - 5 points

2. `toTreeSet` - converts Collection to TreeSet. - 5 points

### Sorting - (30 points)

1. `sortByAuthor` - Sorts by author for both `ASC` or `DESC` depending on method parameter. - 10 points

2. `sortByAIRating` - Sort by AI Rating for both `ASC` or `DESC` depending on method parameter. - 10 points

3. `sortByType` - Sort by social media type for both `ASC` or `DESC` depending on method parameter. - 10 points

### Filtering - (40 Points)

1.  `filterByType` - allows the user to specify which social media type of the collection they want to see. - 10 points

2. `filterByGender` - allows the user to specify which gender of the collection's authors they wish to see. - 10 points

3. `filterByAuthor` - allows the user to specify which author of the collection they wish to filter the collection by. - 10 points

4. `filterByAIRatingRange` - allows the user to specify a lower bound and upper bound on which to filter the collection by. - 10 points

### Aggregation - (35 Points)

1. `aggregateByAuthor` - aggregates the collection by author - 5 points

2. `aggregateByMediaType` - aggregates the collection by social media type - 5 Points

3. `aggregateByGender` - aggregates the collection by authors declared gender - 5 points

4. `aggregateByNegativeAndPositiveComments` - aggregates the collection such that negative comments (values less than 0) and positive comments (values greater than or equal to 0) are in a collection with their own lists. - 10 points

5. `aggregateByAuthorsAverageRating` - aggregates the collection by author, providing their average AI Rating across all social media types. - 10 points


## Extra Credit

There are 4 extra credit problems. To be counted, each problem must be completed successfully. You must complete problem 1 before 2, and 2 before 3, and 3 before 4. If any previous problem is incorrect, the following problems will not be graded. This means if problem 1 is incorrect, problems 2, 3, 4 will not be graded. It follows then that if problem 1 and 2 are correct, problem 3 is incorrect, then problem 4 will not be graded.

### Problem 1 (5 Percentage Points)

* `aggregateFavorableRatedFacebookAndTwitter` - The user should be able to aggregate all favorable AI Ratings (>= 0) into two lists, one for facebook, and one for twitter. These need to be mapped to their respective type.

### Problem 2 (5 Percentage Points)

* `filterFavorableRatedAscendingForTwitterAndAggreateForGender` - The user should be able to filter out all unfavorable AI Ratings (< 0) for twitter, have them sorted in ascending order, and then have them aggregated by gender. This means the user should get back a group of lists aggregated and mapped by gender, that holds all favorable tweets, from lowest rating to biggest rating.

### Problem 3 (10 Percentage Points)

* `aggregateBestRatedFromEachMediaType` -  The user should be able to request an aggregation of each social media type that gives back the highest AI Rated post for each type.

### Problem 4 (10 Percentage Points)

* `aggregateBestRatedFromEachMediaTypeForEachGender` - The user should get back a map whose keys are all the genders of authors. The values of this map are the values of each social media type that subsequently map to the best media posting (AI Rating) of that type. So its a map nested in a map. Example: `Map<String, Map<String, Object>>`
