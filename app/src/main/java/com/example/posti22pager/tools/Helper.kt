package com.example.posti22pager.tools

import com.example.posti22pager.R
import com.example.posti22pager.model.Post
import com.google.firebase.firestore.DocumentSnapshot

class Helper {

    fun getRanges(): List<Pair<Int, Int>> {
        return listOf(
            Pair(100, 103),
            //---------------------------
            Pair(29900, 29916),
            Pair(200, 299),
            //---------------------------
            Pair(3999000, 3999023),
            Pair(3969, 3999),
            Pair(300, 390),
            //---------------------------
            Pair(49990000, 4999078),
            Pair(4900, 4999),
            Pair(400, 490),
            //---------------------------
            Pair(5999000, 5999006),
            Pair(500, 599),
            //---------------------------
            Pair(600, 656),
            //---------------------------
            Pair(700, 719),
            //---------------------------
            Pair(800, 814),
            //---------------------------
            Pair(902, 905),
            //---------------------------
            Pair(1000, 1006),
            //---------------------------
            Pair(1100, 1100),
            //---------------------------
            Pair(1200, 1200),

        )
    }

    fun retrivePostFromFirestore(snap: DocumentSnapshot?): Post {

        var textLocation1: String? =null
        var postTextSize1: String? =null
        var postPadding1:String?=null
        val postId = snap?.getLong(POST_ID)!!.toInt()
        val postNum = snap.getLong(POST_NUM)!!.toInt()
        val lineNum = snap.getLong(POST_LINE_NUM)!!.toInt()
        val imageUri = snap.getString(POST_IMAGE_URI).toString()
        val postText: ArrayList<String> = snap.get(POST_TEXT) as ArrayList<String>
        val postBackground = snap.getString(POST_BACKGROUND).toString()
        val postTranparency = snap.getLong(POST_TRANPARECY)!!.toInt()
        val postTextColor: ArrayList<String> = snap?.get(POST_TEXT_COLOR) as ArrayList<String>
        val postFontFamily = snap.getLong(POST_FONT_FAMILY)!!.toInt()
        val postRadius = snap.getLong(POST_RADIUS)!!.toInt()
        val timestamp = snap?.getTimestamp(POST_TIME_STAMP)

        postTextSize1 = snap?.getString(POST_TEXT_SIZE).toString()
        val postTextSize: ArrayList<Int> = convertFromStringArrayToIntArry(postTextSize1)

        postPadding1 = snap?.getString(POST_PADDING).toString()
        val postPadding: ArrayList<Int> = convertFromStringArrayToIntArry(postPadding1)

        textLocation1 = snap?.getString(POST_TEXT_LOCATION).toString()
        val  textLocation: ArrayList<Int> = convertFromStringArrayToIntArry(textLocation1)

        val postMargin1 = snap?.getString(POST_MARGIN).toString()
        val postMargin: ArrayList<ArrayList<Int>> = convertFromStringArrayToIntArrayList1(postMargin1)

        val postLineSpacing = snap?.getDouble(POST_LINE_SPACING)
        val postVideoUrl = snap?.getString(POST_VIDEO_URL).toString()
        val postVideoText= snap?.getString(POST_VIDEO_TEXT).toString()

        val newPost1 = Post(
            postId,
            postNum,
            lineNum,
            imageUri,
            postText,
            postMargin,
            postBackground,
            postTranparency,
            postTextSize,
            postPadding,
            textLocation,
            postTextColor,
            postFontFamily,
            postRadius,
            timestamp,
            postLineSpacing!!.toFloat(),
            0,
            postVideoUrl,
            postVideoText
        )
        return newPost1
    }

private fun convertFromStringArrayToIntArry(str: String): ArrayList<Int> {
    if (str == "null") {
        return ArrayList()
    }
    val textSizeList = str.split(",").map {
        if (it.trim().isNotEmpty()) it.trim().toInt() else 0
    }
    return ArrayList(textSizeList)
}

private fun convertFromStringArrayToIntArrayList1(str: String): ArrayList<ArrayList<Int>> {
    if (str == "null") {
        return ArrayList()
    }
    val outerArrayList = ArrayList<ArrayList<Int>>()
    val outerArrayStrings = str.split(";")
    outerArrayStrings.forEach { innerString ->
        val innerArrayList = convertFromStringArrayToIntArry(innerString)
        outerArrayList.add(innerArrayList)
    }
    return outerArrayList
}




fun getTransfo(index:Int)=
        when (index){
            1->"1A"
            2->"33"
            3->"4D"
            4->"66"
            5->"80"
            6->"99"
            7->"B3"
            8->"CC"
            9->"E6"
            10->"FF"
            else->"00"
        }

    fun getFamilyFont(index:Int)=
        when (index){
            // for the old posts
            1-> R.font.a101_ankaclm_bold_webfont
            1-> R.font.a101_ankaclm_bold_webfont
            2-> R.font.a210_danayadalefalefalef_normal
            3-> R.font.a220_davidlibre_regular
            31-> R.font.a221_davidlibre_bold
            32-> R.font.a222_davidlibre_medium
            4-> R.font.a100_gveretlevinalefalefalef_regular

            // for the new post

            100-> R.font.a100_gveretlevinalefalefalef_regular
            101-> R.font.a101_ankaclm_bold_webfont
            102-> R.font.a102_varelaround_regular
            103-> R.font.a103_gadi_almog_regular_aaa
            104-> R.font.a104_danayadalefalefalef_normal

            109-> R.font.a109_yiddishkeitalefalefalef_bold
            110-> R.font.a110_abraham_regular

            120-> R.font.a120_aharoniclm_bold_webfont
            121-> R.font.a121_aharoniclm_boldoblique_webfont
            122-> R.font.a122_aharoniclm_book_webfont
            123-> R.font.a123_aharoniclm_bookoblique_webfont

            130-> R.font.a131_alef_bold
            131-> R.font.a130_alef_regular

            140-> R.font.a140_amaticasc_regular
            141-> R.font.a141_amaticasc_bold

            150-> R.font.a150_asakim_bold_webfont

            160-> R.font.a160_assistant_regular
            161-> R.font.a161_assistant_bold
            162-> R.font.a162_assistant_extrabold
            163-> R.font.a163_assistant_extralight
            164-> R.font.a164_assistant_light
            165-> R.font.a165_assistant_semibold

            170-> R.font.a170_carmela

            180-> R.font.a180_ubuntu_r_webfont
            181-> R.font.a181_ubuntumono_r_webfont

            190-> R.font.a190_carmelitregular
            191-> R.font.a191_carmelitbold

            200-> R.font.a200_comixno2clm_bold_webfont
            201-> R.font.a201_comixno2clm_medium_webfont

            210-> R.font.a210_danayadalefalefalef_normal

            220-> R.font.a220_davidlibre_regular
            221-> R.font.a221_davidlibre_bold
            222-> R.font.a222_davidlibre_medium

            230-> R.font.a230_dina_talbal

            240-> R.font.a240_dorianclm_book_webfont
            241-> R.font.a241_dorianclm_bookitalic_webfont

            250-> R.font.a250_dragon_webfont

            260-> R.font.a260_drugulinclm_bold
            261-> R.font.a261_drugulinclm_bolditalic

            270-> R.font.a270_elliniaclm_bold_webfont
            271-> R.font.a271_elliniaclm_bolditalic_webfont
            272-> R.font.a272_elliniaclm_light_webfont
            273-> R.font.a273_elliniaclm_lightitalic_webfont

            280-> R.font.a280_felix007_medium_webfont

            290-> R.font.a290_frankruhllibre_black
            291-> R.font.a291_frankruhllibre_bold
            292-> R.font.a292_frankruhllibre_light
            293-> R.font.a293_frankruhllibre_medium
            294-> R.font.a294_frankruhllibre_regular

            300-> R.font.a300_ganclm_bold_webfont

            310-> R.font.a310_gladiaclm_bold_webfont

            320-> R.font.a320_hadasimclm_boldoblique_webfont
            321-> R.font.a321_hadasimclm_bold_webfont
            322-> R.font.a322_hadasimclm_regularoblique_webfont
            323-> R.font.a323_hadasimclm_regular_webfont

            340-> R.font.a340_heebo_regular
            341-> R.font.a341_heebo_bold
            342-> R.font.a342_heebo_extrabold
            343-> R.font.a343_heebo_light
            344-> R.font.a344_heebo_medium
            345-> R.font.a345_heebo_black
            346-> R.font.a346_heebo_thin

            350-> R.font.a350_hillelclm_medium_webfont

            360-> R.font.a360_horevclm_heavy_webfont

            370-> R.font.a370_journalclm_light_webfont

            380-> R.font.a380_keteryg_boldoblique_webfont
            381-> R.font.a381_keteryg_bold_webfont
            382-> R.font.a382_keteryg_mediumoblique_webfont
            383-> R.font.a383_keteryg_medium_webfont

            390-> R.font.a390_makabiyg_webfont

            400-> R.font.a400_migdalfontwin_webfont

            410-> R.font.a410_miriwin_webfont

            420-> R.font.a420_miriamlibre_regular
            421-> R.font.a421_miriamlibre_bold

            430-> R.font.a430_nachlieliclm_boldoblique_webfont
            431-> R.font.a431_nachlieliclm_bold_webfont
            432-> R.font.a432_nachlieliclm_lightoblique_webfont
            433-> R.font.a433_nachlieliclm_light_webfont

            450-> R.font.a450_nehama_webfont

            460-> R.font.a460_opensanshebrew_regular_webfont
            461-> R.font.a461_opensanshebrew_bold_webfont
            462-> R.font.a462_opensanshebrew_extrabolditalic_webfont
            463-> R.font.a463_opensanshebrew_extrabold_webfont
            464-> R.font.a464_opensanshebrew_italic_webfont
            465-> R.font.a465_opensanshebrew_lightitalic_webfont
            466-> R.font.a466_opensanshebrew_light_webfont
            467-> R.font.a467_opensanshebrew_bolditalic_webfont

            470-> R.font.a470_paskol_webfont

            480-> R.font.a480_pfennigbold_webfont
            481-> R.font.a481_pfennigbolditalic_webfont
            482-> R.font.a482_pfennigitalic_webfont
            483-> R.font.a483_pfennig_webfont

            490-> R.font.a490_ptilnarrow_medium_webfont
            491-> R.font.a491_ptilnarrow_regular_webfont
            492-> R.font.a492_ptilwide_medium_webfont
            493-> R.font.a493_ptilwide_regular_webfont

            500-> R.font.a500_rubik_regular
            501-> R.font.a501_rubik_blackitalic
            502-> R.font.a502_rubik_bold
            503-> R.font.a503_rubik_bolditalic
            504-> R.font.a504_rubik_italic
            505-> R.font.a505_drubik_light
            506-> R.font.a506_rubik_medium
            507-> R.font.a507_rubik_mediumitalic
            508-> R.font.a508_rubik_lightitalic
            509-> R.font.a509_rubik_black

            510-> R.font.a510_secularone_regular

            520-> R.font.a520_shmulikclm_webfont

            530-> R.font.a530_shofarregular_webfont
            531-> R.font.a531_shofardemi_boldoblique_webfont
            532-> R.font.a532_shofardemi_bold_webfont
            533-> R.font.a533_shofarregularoblique_webfont

            540-> R.font.a540_simpleclm_medium_webfont
            541-> R.font.a541_simpleclm_mediumoblique_webfont
            542-> R.font.a542_simpleclm_bold_webfont
            543-> R.font.a543_simpleclm_boldoblique_webfont

            550-> R.font.a550_stamashkenazclm_webfont
            551-> R.font.a551_stamsefaradclm_webfont

            560-> R.font.a560_stamashkenazclm

            570-> R.font.a570_stamsefaradclm

            580-> R.font.a580_stopmotiondecember11_webfont

            590-> R.font.a590_suezone_regular

            600-> R.font.a600_trashimclm_bold_webfont

            610-> R.font.a610_varelaround_regular

            620-> R.font.a620_yehudaclm_bold_webfont
            621-> R.font.a621_yehudaclm_light_webfont

            else-> R.font.a220_davidlibre_regular
        }



}