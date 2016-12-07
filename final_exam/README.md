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

### Sorting - (30 points)

### Filtering - (40 Points)

### Aggregation - (35 Points)

## Extra Credit

There are 4 extra credit problems. To be counted, each problem must be completed successfully. You must complete problem 1 before 2, and 2 before 3, and 3 before 4. If any previous problem is incorrect, the following problems will not be graded. This means if problem 1 is incorrect, problems 2, 3, 4 will not be graded. It follows then that if problem 1 and 2 are correct, problem 3 is incorrect, then problem 4 will not be graded.
