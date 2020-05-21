package data

import cache.AppCache
import model.News
import repo.Store

class StoreImpl : Store {
    override fun saveUrlToHashIfNeeded(url: String): Boolean {
        if (!AppCache.instance?.newsUrl?.containsKey(url)!!) {
            AppCache.instance?.newsUrl?.put(url, false)
            println("1. saved new url : $url")
            return true;
        } else {
            println("Processed url, do not thing $url")
            return false
        }
    }

    override fun markUrlAsDone(url: String) {
        // check url is visible in our hash
        if (AppCache.instance!!.newsUrl.containsKey(url)) {
            AppCache.instance?.newsUrl?.put(url, true)
            println("3. Mark url as done: $url")
            println()
        } else {
            println("Key not match, abort")
        }
    }

    override fun saveNewsInfo(news: News) {
        AppCache.instance?.news?.add(news)
        println("2. News was saved to RAM: ${news.url}")
    }

}