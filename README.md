# url-shortener

URL Shortener

Use this application to create shortened version of URLs

#### How to run it

From command line run command below:

./gradlew bootRun

#### How to access it

Open your browser and navigate to URL below:

http://localhost:8080

#### Use instructions

On main page input a redirect URL to have its shortened URL created and hit "submit".

Copy the shortened URL, open a new browser or tab, past shortened URL and press enter.

You will be redirected.

#### Comments about project

Implementation has some limitations, as listed below:

- As already mentioned above, repository layer is using an in-memory mechanism to store information about redirect and shortened URLs, hence, this implementation works only in standalone mode and is not scalable, and data is volatile, being missed when application is restarted.
- Repository layer is trying to mimic behaviors implemented by more complex solutions such relational and NoSQL, such unique keys and reverse indexes, but implementation is not completely threadsafe and may present concurrency issues when handling high number of requests.      
