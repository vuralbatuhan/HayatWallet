package com.example.hayatwallet.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hayatwallet.data.HayatCampaign
import org.jsoup.Jsoup

class CampaignViewModel : ViewModel() {
    val campaigns = MutableLiveData<List<HayatCampaign>>()
    val campaignList = mutableListOf<HayatCampaign>()

    fun getData() {

        val doc = Jsoup.connect("https://www.hayatfinans.com.tr/kendim-icin-kampanyalar").get()
        val container = doc.body()
        val cards = container.getElementsByClass("card h-100")

        for (element in cards) {
            val card = element.getElementsByClass("card h-100")
            val imageUrl = card.first()?.getElementsByTag("img")?.first()?.attr("data-src")
            val title = card.first()?.getElementsByClass("card-title")?.first()?.text()
            val fullUrl = "https://www.hayatfinans.com.tr$imageUrl"
            val campaign = HayatCampaign(title, fullUrl)
            campaignList.add(campaign)
        }

        campaigns.postValue(campaignList)
    }
}
