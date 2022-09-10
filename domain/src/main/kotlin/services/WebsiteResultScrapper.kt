package services

import models.CollectionDescription

interface WebsiteResultScrapper {
    suspend fun scrap(collectionDescription: CollectionDescription)
}