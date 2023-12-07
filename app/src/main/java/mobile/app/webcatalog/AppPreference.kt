package mobile.app.webcatalog

import android.content.Context
import com.google.gson.Gson

class AppPreference(context: Context?) {

    var mContext: Context? = context

    fun setPreference(name: Enum<*>?, Value: Any?) {
        mContext?.let { AppPersistence.start(it).save(name!!, Value!!) }
    }

    fun getPreference(name: Enum<*>?): Any? {
        return mContext?.let { AppPersistence.start(it).get(name!!) }
    }

//    public Object getJSONPreference(Enum name) {
//        return getGson().fromJson(getPreference(name), Object.class);
//    }

    //    public Object getJSONPreference(Enum name) {
    //        return getGson().fromJson(getPreference(name), Object.class);
    //    }

    fun removePreference(name: Enum<*>?) {
        mContext?.let { AppPersistence.start(it).remove(name!!) }
    }

//    fun getAdsDetails(): AdsResponse? {
//        if (AppPreference(MyApplication.context).getPreference(AppPersistence.keys.APP_DATA) == null)
//            AppPreference(MyApplication.context).setPreference(AppPersistence.keys.APP_DATA, "")
//
//        return if (getPreference(AppPersistence.keys.APP_DATA) != null ||
//            (!(getPreference(AppPersistence.keys.APP_DATA)?.equals(""))!!)
//        ) {
//            Gson().fromJson(
//                getPreference(AppPersistence.keys.APP_DATA).toString(), AdsResponse::class.java)
//        } else {
//            null
//        }

//        val detail = getPreference(name) as String?
//        val dataStr = AESHelper.decrypt(dataStr()).split(",".toRegex()).toTypedArray()
//        val adsData = AdsData()
//        try {
//            val detailObj = JSONObject(detail)
//            adsData.appVersion = detailObj.getInt(dataStr[0])
//
//            adsData.isAd = detailObj.getBoolean(dataStr[1])
//            adsData.isNavigationBar = detailObj.getBoolean(dataStr[2])
//
//            adsData.appOpen = detailObj.getString(dataStr[27])
//            adsData.appOpenInterval = detailObj.getInt(dataStr[28])
//
//            adsData.first = detailObj.getString(dataStr[3])
//            adsData.isBannerSpace = detailObj.getBoolean(dataStr[4])
//            adsData.isNativeSpace = detailObj.getBoolean(dataStr[5])
//
//            val gObject = detailObj.getJSONObject(dataStr[6])
//            val gData = GAdsData()
//            gData.appID = gObject.getString(dataStr[7])
//            gData.interID = gObject.getString(dataStr[8])
//            gData.bannerID = gObject.getString(dataStr[9])
//            gData.nativeID = gObject.getString(dataStr[10])
//            gData.rewardID = gObject.getString(dataStr[11])
//            gData.interCount = gObject.getInt(dataStr[12])
//            gData.interClick = gObject.getInt(dataStr[13])
//            gData.bannerCount = gObject.getInt(dataStr[14])
//            gData.nativeCount = gObject.getInt(dataStr[15])
//            gData.rewardCount = gObject.getInt(dataStr[16])
//            gData.timeGap = gObject.getInt(dataStr[17])
//            gData.rewardTimeGap = gObject.getInt(dataStr[18])
//            adsData.gAdData = gData
//
//            val fbObject = detailObj.getJSONObject(dataStr[19])
//            val fbData = FBAdsData()
//            fbData.interID = fbObject.getString(dataStr[20])
//            fbData.bannerID = fbObject.getString(dataStr[21])
//            fbData.nativeID = fbObject.getString(dataStr[22])
//            fbData.interCount = fbObject.getInt(dataStr[23])
//            fbData.bannerCount = fbObject.getInt(dataStr[24])
//            fbData.nativeCount = fbObject.getInt(dataStr[25])
//            fbData.timeGap = fbObject.getInt(dataStr[26])
//
//            adsData.fbAdData = fbData
//
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return adsData
//            ?: if (!TextUtils.isEmpty(detail)) {
//                fromJson(detail, AdsData::class.java)
//            } else {
//                AdsData()
//            }
    // TODO:  comment for this date 17/09/2023
//    }
//    external fun dataStr(): String?



    /*fun getAdsDetails(name: Enum<*>?): AdsData? {
        val detail = getPreference(name) as String?
        val dataStr: Array<String> = AESHelper.decrypt(dataStr()).split(",")
        val adsData = AdsData()
        try {
            val detailObj = JSONObject(detail)
            adsData.setFirst(detailObj.getString(dataStr[0]))
            adsData.setBannerSpace(detailObj.getBoolean(dataStr[1]))
            adsData.setNativeSpace(detailObj.getBoolean(dataStr[2]))
            val gObject = detailObj.getJSONObject(dataStr[3])
            val gData = GAdsData()
            gData.setAppID(gObject.getString(dataStr[4]))
            gData.setInterID(gObject.getString(dataStr[5]))
            gData.setBannerID(gObject.getString(dataStr[6]))
            gData.setNativeID(gObject.getString(dataStr[7]))
            gData.setRewardID(gObject.getString(dataStr[8]))
            gData.setInterCount(gObject.getInt(dataStr[9]))
            gData.setBannerCount(gObject.getInt(dataStr[10]))
            gData.setNativeCount(gObject.getInt(dataStr[11]))
            gData.setRewardCount(gObject.getInt(dataStr[12]))
            gData.setTimeGap(gObject.getInt(dataStr[13]))
            gData.setRewardTimeGap(gObject.getInt(dataStr[14]))
            adsData.setgAdData(gData)
            val fbObject = detailObj.getJSONObject(dataStr[15])
            val fbData = FBAdsData()
            fbData.setInterID(fbObject.getString(dataStr[16]))
            fbData.setBannerID(fbObject.getString(dataStr[17]))
            fbData.setNativeID(fbObject.getString(dataStr[18]))
            fbData.setInterCount(fbObject.getInt(dataStr[19]))
            fbData.setBannerCount(fbObject.getInt(dataStr[20]))
            fbData.setNativeCount(fbObject.getInt(dataStr[21]))
            fbData.setTimeGap(fbObject.getInt(dataStr[22]))
            adsData.setFbAdData(fbData)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return if (adsData != null) adsData else if (!TextUtils.isEmpty(detail)) {
            fromJson(detail, AdsData::class.java)
        } else {
            AdsData()
        }
    }*/

//    external fun dataStr(): String?

    fun <T> fromJson(jsonString: String?, theClass: Class<T>?): T {
        return getGson().fromJson(jsonString, theClass)
    }

    fun getGson(): Gson {
        return Gson()
    }
}