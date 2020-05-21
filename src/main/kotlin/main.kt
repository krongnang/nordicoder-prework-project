import cache.AppCache
import data.StoreImpl
import data.VnExpressCaoCao
import repo.CaoCao
import repo.Store

const val SITE = "https://vnexpress.net/the-thao"
fun main() {
    bem(SITE, VnExpressCaoCao())
}

fun bem(url: String, caocao: CaoCao) {
    val store: Store = StoreImpl();
    val news = caocao.getUrlsFromHome(url);
    news.forEach {
        // add to map url
        if (store.saveUrlToHashIfNeeded(it)) {
            // get info
            val newInfo = caocao.getAndSaveNewsInfoToMemory(it);

            // mark as processed
            store.markUrlAsDone(it)

            // recheck and get current url task
            recheckAndGetCurrentUrlTask(it,caocao);
        }


    }

    println("Complete ${AppCache.instance?.news?.size}")

    print("duplicate value? ${AppCache.instance?.news?.groupingBy { it.url }?.eachCount()?.filter { it.value > 1 }}")

}

fun recheckAndGetCurrentUrlTask(url: String, caocao: CaoCao) {
    val store: Store = StoreImpl();
    val news = caocao.getUrlsFromNewsDetail(url);
    news.forEach {
        // add to map url
        if (store.saveUrlToHashIfNeeded(it)) {
            val newInfo = caocao.getAndSaveNewsInfoToMemory(it);

            // mark as processed
            store.markUrlAsDone(it)
            println("- - - - -- - - -done recheck task - -- - \n")
        }
    }
}
