import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import models.CollectionDescription
import services.WebsiteResultScrapper

class ApplicationImpl constructor(
    private val websiteResultScrapper: WebsiteResultScrapper,
    private val collectionDescription: CollectionDescription
) : Application {

    override suspend fun run() {
        withContext(context = Dispatchers.IO) {
            websiteResultScrapper.scrap(collectionDescription = collectionDescription)
        }
    }

}