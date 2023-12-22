# Purpose
Just a dead simple android calorie calculator to calculate calories in beer and wine. 
Designed as a response to the problem created by our (Canada) government not requiring nutritional labeling on alcohol. 
Online calculators are generally insufficient or inaccurate. 
Search engine results for these sorts of calculators are also plagued by all sorts of nonsense blogs linking to the same poor calculators. 

This app uses the percent alcohol to calculate the calories contributed from ethanol and adds an approximation for calories contributed by residual carbohydrates via a subjective sweetness rating. 
The carbohydrate approximation was designed by taking reported calories from various breweries and fitting a function to the available data. 
It is not perfect and relies on subject ratings of sweetness as a proxy for residual carbs, but it provides a pretty good approximation. 
An example for beer is a traditional big brand lager would be about a 3 on the scale, where a sweet stout would be a 9. 
As for wine, most fall in the range of 2 to 5 with higher numbers corresponding to things like port or sherry. 

# Instructions for use
Install android studio, clone repository, and install on desired android device. 
Input mL for beer or grams for wine, percentage alcohol, and a perceived sweetness rating out of 10. 
Hit calculate and get an approximation of the calories. 
