<h1>GUILDED ROSE KATA (java) </h1>
<h5><i>Technical Showcase by TJ English</i></h5>
<h6><i>(branch: <b>tjenglish-tech-showcase)</b></i></h6>

***

<h3><b>PREREQUISITES</b></h3>

<h5><b>Java 15</b> and <b>Gradle</b> must be installed.</h5>

***

<h3><b>RUNNING THE PROJECT</b></h3>

<h5>Once you have installed the above prerequisite dependencies, open the project in your IDE of choice.</h5>

<h5>Open a terminal window and navigate to the Java package of the project directory:</h5> `/gilded-rose-kata/Java`

<h5>Execute the "gradle build" command to build the project and execute the tests:</h5> `./gradlew build`

<h5>To view a report of the test results: in the browser of your choice, open the html file located here:</h5> `/build/reports/tests/test/index.html`

<h5>The _Jacoco_ Gradle plugin also generates a report for line coverage. This can be viewed here:</h5> `build/reports/jacoco/test/html/index.html`

***

<h3><b>BUSINESS REQUIREMENTS</b></h3>

Hi and welcome to team Gilded Rose.

As you know, we are a small inn with a prime location in a prominent city ran
by a friendly innkeeper named Allison.  We also buy and sell only the finest
goods. Unfortunately, our goods are constantly degrading in quality as they
approach their sell by date.

We have a system in place that updates our inventory for us. It was developed
by a no-nonsense type named Leeroy, who has moved on to new adventures. Your
task is to add the new feature to our system so that we can begin selling a
new category of items.

First an introduction to our system:

- All items have a sell-in value which denotes the number of days we have to
  sell the item

- All items have a quality value which denotes how valuable the item is

- At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

- Once the sell by date has passed, quality degrades twice as fast

- The quality of an item is never negative

- "Aged Brie" actually increases in quality the older it gets

- The quality of an item is never more than 50

- "Sulfuras", being a legendary item, never has to be sold or decreases in
  quality

- "Backstage passes", like aged brie, increases in quality as its sell-in
  value approaches; quality increases by 2 when there are 10 days or less
  and by 3 when there are 5 days or less but quality drops to 0 after the
  concert

We have recently signed a supplier of conjured items. This requires an update
to our system:

- "Conjured" items degrade in quality twice as fast as normal items

Feel free to make any changes to the update-quality method and add any new code
as long as everything still works correctly. However, do not alter the item
function as that belongs to the goblin in the corner who will insta-rage and
one-shot you as he doesn't believe in shared code ownership.


Just for clarification, an item can never have its quality increase above 50,
however "Sulfuras" is a legendary item and as such its quality is 80 and it
never alters.
