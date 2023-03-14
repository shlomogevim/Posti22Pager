package com.example.posti22pager.tools

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.posti22pager.model.Article
import com.example.posti22pager.model.Comment
import com.example.posti22pager.model.Post
import com.example.posti22pager.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore


class UtilityPost {

    val currentUser = FirebaseAuth.getInstance().currentUser
    var arr0 = arrayListOf<Int>()
    var arr1 = arrayListOf<Int>()
    var arr2 = arrayListOf<Int>()
    var arr3 = arrayListOf<Int>()
    var arr4 = arrayListOf<Int>()
    var arr5 = arrayListOf<Int>()
    var arr6 = arrayListOf<Int>()
    var arr7 = arrayListOf<Int>()
    var arr8 = arrayListOf<Int>()
    var arr9 = arrayListOf<Int>()
    private fun initArray() {
        arr0.clear()
        arr1.clear()
        arr2.clear()
        arr3.clear()
        arr4.clear()
        arr5.clear()
        arr6.clear()
        arr7.clear()
        arr8.clear()
        arr9.clear()
    }


    fun deleteComment(comment: Comment) {
        //  logi("Utility 111      comment.postNumString=${comment.postNumString}           comment.commntId=${comment.commntId}")
        FirebaseFirestore.getInstance().collection(COMMENT_REF)
            .document(comment.postNumString)
            .collection(COMMENT_LIST).document(comment.commntId).delete()
    }


    fun activatePosts(activity: Activity) {
        /* var currentId=getCurrentUserID()
            if (currentId.isNotEmpty()) {
              mFirestore.collection(Constants.USER_REF).document(getCurrentUserID())
                  .get()
                  .addOnSuccessListener { document ->
                      //Log.i(activity.javaClass.simpleName, document.toString())
                      // Here we have received the document snapshot which is converted into the User Data model object.
                      val user = document.toObject(User::class.java)!!

                      insertToSharedPref(activity, user)

                      when (activity) {
                          is LoginActivity -> {
                              activity.userLoggedInSuccess(user)
                          }
                          is PostDetailesActivity -> {
                              activity.getUserNameSetting(user)
                          }

                      }
                  }

                  .addOnFailureListener { e ->
                      when (activity) {
                          is LoginActivity -> {
                              activity.hideProgressDialog()
                          }
                          is PostSettingActivity -> {
                              activity.hideProgressDialog()
                          }
                      }


                  }
          }*/
    }


    /* fun createDialog(context: Context, ind: Int) {

         val intent = Intent(context, DialogActivity::class.java)
         intent.putExtra(DIALOG_EXSTRA, ind)
         context.startActivity(intent)


         //   logi("Utility 32 createDialoge   =====> ind=$ind      contex=$context")

         val dialog = Dialog(context)
         // logi("Utility 39 createDialoge   =====> ind=$ind")
         dialog.setCancelable(false)
         dialog.setContentView(R.layout.option_menu1)
         val btn1 = dialog.findViewById<Button>(R.id.btn1_dialog)
         val btn2 = dialog.findViewById<Button>(R.id.btn2_dialog)
         val btn3 = dialog.findViewById<Button>(R.id.btn3_dialog)
         val loti = dialog.findViewById<LottieAnimationView>(R.id.lottie_anim_dialog)
         val dialogText1 = dialog.findViewById<TextView>(R.id.text_dialog1)
         val dialogText2 = dialog.findViewById<TextView>(R.id.text_dialog2)
         val dialogText3 = dialog.findViewById<TextView>(R.id.text_dialog3)
         val dialogText4 = dialog.findViewById<TextView>(R.id.text_dialog4)
         btn1.visibility = View.GONE
         btn2.visibility = View.GONE

         //   logi("Utility  createDialoge   =====> ind=$ind")

         val arString: ArrayList<String> = getDialogMessage(ind)

         logi("Utility 95  createDialoge   =====> ind=$ind")
         dialogText1.text = arString[0]
         dialogText2.text = arString[1]
         dialogText3.text = arString[2]
         dialogText4.text = arString[3]
         btn3.text = arString[4]
         loti.setAnimation(arString[5])
         btn1.setOnClickListener { }
         btn2.setOnClickListener { }
         btn3.setOnClickListener {
             dialog.dismiss()
         }
         dialog.show()

     }*/

    private fun getDialogMessage(ind: Int): ArrayList<String> {
        var stMessage1 = ""
        var stMessage2 = ""
        var stMessage3 = ""
        var stMessage4 = ""
        var stBackBtn = ""
        var stAnimation = ""
        if (ind == 2) {
            stMessage1 = "אתה כרגע משתתף אנונימי "
            stMessage2 = "ולכן אין לך אישור לכתוב הערות ,"
            stMessage3 = "אתה צריך קודם  להיכנס ..."
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור להערות"
            stAnimation = "right.json"
        }
        /*  if (ind == 1) {
              stMessage1 = "אתה כרגע משתתף אנונימי "
              stMessage2 ="ולכן לא יעזור לך ללחוץ על צלמית השלח ,"
              stMessage3 =  "אתה צריך קודם להיכנס..."
              stMessage4 = " "
              stBackBtn= "לחץ פה כדי לחזור להערות"
              stAnimation="right.json"
          }*/
        if (ind == 3) {
            stMessage1 = " לא כתבת כלום בהערה ..."
            stMessage2 = "קודם תכתוב משהו,"
            stMessage3 = "ואחר כך לחץ על שלח ..."
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור להערות"
            stAnimation = "right.json"
        }
        if (ind == 4) {
            stMessage1 = " לא הכנסת מייל..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך הכניסה"
            stAnimation = "right.json"
        }
        if (ind == 5) {
            stMessage1 = " לא הכנסת סיסמה..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך הכניסה"
            stAnimation = "right.json"
        }
        if (ind == 6) {
            stMessage1 = " לא הכנסת שם משתמש..."
            stMessage2 = "זה שם שיזהה אותך"
            stMessage3 = "(יכול להיות פקטיבי)"
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 7) {
            stMessage1 = " לא הכנסת מייל..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 8) {
            stMessage1 = " לא הכנסת סיסמה..."
            stMessage2 = ""
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 9) {
            stMessage1 = "חביבי, מישהו כבר משתמש במייל הזה,"
            stMessage2 = "נסה להכניס מייל אחר"
            stMessage3 = ""
            stMessage4 = " "
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 10) {
            stMessage1 = "הסיסמה לא תקינה"
            stMessage2 = "צריך להיות לפחות 6 מספרים"
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 11) {
            stMessage1 = "המייל שהכנסת לא תקין ... "
            stMessage2 = "נסה להכניס מייל אחר"
            stMessage3 = "כמובן יכול להיות סתם מייל פקטיבי"
            stMessage4 = " משהוא כמו:        a@bc.com"
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 12) {
            stMessage1 = "השם הזה כבר קיים במערכת ... "
            stMessage2 = "מצא לעצמך שם אחר"
            stMessage3 = ""
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        if (ind == 13) {
            stMessage1 = "מזל טוב ... "
            stMessage2 = "הצלחת להרשם בהצלחה"
            stMessage3 = "ברוך הבא"
            stMessage4 = ""
            stBackBtn = "לחץ פה כדי לחזור למסך ההרשמה"
            stAnimation = "right.json"
        }
        return arrayListOf(stMessage1, stMessage2, stMessage3, stMessage4, stBackBtn, stAnimation)
    }


    /*   fun createDialoge(  contex:Context,title:String,body:String) {
           val alertDialog = AlertDialog.Builder(contex, R.style.RoundedCornerDialog).create()
           alertDialog.setTitle(title)
           alertDialog.setMessage(body)

           alertDialog.setButton(
               AlertDialog.BUTTON_NEUTRAL, "לחץ כאן כדי להמשיך ...",
               DialogInterface.OnClickListener {
                       dialog, which -> dialog.dismiss()
                   // finish()
               })
           alertDialog.show()
       }*/
    fun convertToUser(snap: DocumentSnapshot?): User {
        var userName = ""
        var fullName = "no fullName"
        var email: String = "no email"
        var profileImage =
            "https://firebasestorage.googleapis.com/v0/b/social55firestore.appspot.com/o/Default%20Images%2Fprofile.png?alt=media&token=4a02bf76-8cc4-43e7-9750-930176c9c9ee"
        var dio: String = "no dio"
        var uid: String = "no uid"
        userName = snap?.getString(USER_USERNAME).toString()
        fullName = snap?.getString(USER_FULLNAME).toString()
        email = snap?.getString(USER_EMAIL).toString()
        profileImage = snap?.getString(USER_IMAGE).toString()
        dio = snap?.getString(USER_BIO).toString()
        uid = snap?.getString(FIRESTORE_USER_ID).toString()

        val newUser = User(
            uid = uid,
            userName = userName,
            lastName = fullName,
            email,
            image = profileImage,
            moto = dio
        )
        return newUser
    }


    /* fun downloadPost1(context: Context, index: Int) {
         // val layout1: ConstraintLayout = (context as Activity).findViewById(R.id.mainLayout1)
         //  val createPost1 = CreatePost1(context, layout1)
         FirebaseFirestore.getInstance().collection(POST_REF).document(index.toString()).get()
             .addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                     val post = retrivePostFromFirestore(task.result)

                     //  createPost1.drawPost(post)
                 }
             }
         *//*FirebaseUser*//*
    }*/


    /*  fun createComment(post: Post, commentText: String, currentUser: User) {
          logi("UtilityPost 298     commentText=$commentText")
          val data = HashMap<String, Any>()
          data[COMMENT_ID] = "1"
          data[COMMENT_POST_NUM_STRING] = post.postNum.toString()
          data[COMMENT_TEXT] = commentText
          data[COMMENT_USER_NAME] = currentUser.userName
          data[COMMENT_USER_ID] = currentUser.uid
          data[COMMEND_TIME_STAMP] = FieldValue.serverTimestamp()
          val ref = FirebaseFirestore.getInstance().collection(COMMENT_REF)
              .document(post.postNum.toString())
              .collection(COMMENT_LIST)
          ref.add(data)
              .addOnSuccessListener {
                  data[COMMENT_ID] = it.id
                  ref.document(it.id).update(data)
              }
      }*/


    fun retriveCommentFromFirestore(snap: DocumentSnapshot?): Comment {
        val comId = snap?.get(COMMENT_ID).toString()
        val postId = snap?.get(COMMENT_POST_NUM_STRING).toString()
        val comText = snap?.get(COMMENT_TEXT).toString()
        val comUserName = snap?.get(COMMENT_USER_NAME).toString()
        val comUserId = snap?.get(COMMENT_USER_ID).toString()
        val timestamp = snap?.getTimestamp(COMMEND_TIME_STAMP)
        val index = snap?.get(COMMEND_INDEX).toString()
        val newComment = Comment(comId, postId, comText, comUserName, comUserId, timestamp, index)
        return newComment
    }
    fun retriveArticleFromFirestore(doc: DocumentSnapshot?): Article {
        val articleNum=doc?.getLong(ARTICAL_NUM)!!.toInt()
        val articleText=doc?.getString(ARTICAL_TEXT).toString()
        val articleTextSize=doc?.getLong(ARTICAL_TEXT_SIZE)!!.toInt()
        val articleTitle=doc?.getString(ARTICAL_TITLE).toString()
        val articleTextColor=doc?.getString(ARTICAL_TEXT_COLOR).toString()
        val articleBackground=doc?.getString(ARTICAL_BACKGROUND).toString()
        val articleFontFamily=doc?.getLong(ARTICAL_FONT_FAMILY)!!.toInt()
        val timestamp = doc?.getTimestamp(ARTICAL_TIMESTAMP)
       val newArticle= Article(
           articleNum,
           articleTitle,
           articleText,
           articleBackground,
           articleTextColor,
           articleTextSize,
           articleFontFamily,
           timestamp
       )
        return newArticle
    }
    /* var aricleNum:Int=0,
     var aricleTitle:String="",
     var aricleText:String="",
    var articleBackground:String="",
    var articleTextColor:String="",
    var articleFontFamily:Int=0,
    var timestamp: Timestamp?=null*/


    /*  fun retrivePostFromFirestore(snap: DocumentSnapshot?): Post {
          val postId = snap?.getLong(POST_ID)!!.toInt()
          val postNum = snap?.getLong(POST_NUM)!!.toInt()
          val lineNum = snap?.getLong(POST_LINE_NUM)!!.toInt()
          val imageUri = snap?.getString(POST_IMAGE_URI).toString()
          val postText: ArrayList<String> = snap?.get(POST_TEXT) as ArrayList<String>
          val postBackground = snap?.getString(POST_BACKGROUND).toString()
          val postTranparency = snap?.getLong(POST_TRANPARECY)!!.toInt()
          val postTextColor: ArrayList<String> = snap?.get(POST_TEXT_COLOR) as ArrayList<String>
          val postFontFamily = snap?.getLong(POST_FONT_FAMILY)!!.toInt()
          val postRadius = snap?.getLong(POST_RADIUS)!!.toInt()
          val timestamp = snap?.getTimestamp(POST_TIME_STAMP)
          val postTextSize1 = snap?.getString(POST_TEXT_SIZE).toString()
          val postTextSize: ArrayList<Int> = convertFromStringArrayToIntArry(postTextSize1)
          val postPadding1 = snap?.getString(POST_PADDING).toString()
          val postPadding: ArrayList<Int> = convertFromStringArrayToIntArry(postPadding1)
          val postMargin1 = snap?.getString(POST_MARGIN).toString()
          // logi("Utility 383  ")
          val postMargin: ArrayList<ArrayList<Int>> = convertFromStringArrayToIntArry2(postMargin1)
          // var postLineSpacing=1.4f
          //  if ( snap?.getLong(POST_LINE_SPACING) !=null) {
          val postLineSpacing = snap.getDouble(POST_LINE_SPACING)       //save it in Double
          // logi("UtilityPost 415       postLineSpacing=$postLineSpacing")
          //val postRecommended=snap.getLong(POST_RECOMMENDED)!!.toInt()

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
              postTextColor,
              postFontFamily,
              postRadius,
              timestamp,
              postLineSpacing


          )
      /*
  //         if (newPost1.postNum==1000){
           if (newPost1.postNum==901){
  //         if (newPost1.postNum==4940){
             logi("Utility 431   newPost1.postMargin=${newPost1.postMargin.joinToString()}")
           }*/
          return newPost1
      }*/

  /*  fun retrivePostFromFirestore(snap: DocumentSnapshot?): Post {
        var textLocation1: String? =null
        var postTextSize1: String? =null
        var postPadding1:String?=null
        val postId = snap?.getLong(POST_ID)!!.toInt()
        val postNum = snap?.getLong(POST_NUM)!!.toInt()
        val lineNum = snap?.getLong(POST_LINE_NUM)!!.toInt()
        val imageUri = snap?.getString(POST_IMAGE_URI).toString()
        val postText: ArrayList<String> = snap?.get(POST_TEXT) as ArrayList<String>
        val postBackground = snap?.getString(POST_BACKGROUND).toString()
        val postTranparency = snap?.getLong(POST_TRANPARECY)!!.toInt()
        val postTextColor: ArrayList<String> = snap?.get(POST_TEXT_COLOR) as ArrayList<String>
        val postFontFamily = snap?.getLong(POST_FONT_FAMILY)!!.toInt()
        val postRadius = snap?.getLong(POST_RADIUS)!!.toInt()
        val timestamp = snap?.getTimestamp(POST_TIME_STAMP)

          postTextSize1 = snap?.getString(POST_TEXT_SIZE).toString()
        val postTextSize: ArrayList<Int> = convertFromStringArrayToIntArry(1,postTextSize1)

          postPadding1 = snap?.getString(POST_PADDING).toString()
        val postPadding: ArrayList<Int> = convertFromStringArrayToIntArry(2,postPadding1)

         textLocation1 = snap?.getString(POST_TEXT_LOCATION).toString()
        val  textLocation: ArrayList<Int> = convertFromStringArrayToIntArry(3,textLocation1)
        val postMargin1 = snap?.getString(POST_MARGIN).toString()
        val postMargin: ArrayList<ArrayList<Int>> = convertFromStringArrayToIntArry2(postMargin1)
        val postLineSpacing = snap?.getDouble(POST_LINE_SPACING)
        //val postLineSpacing = 1.4f

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
            postLineSpacing
        )
        //   logi("UtilityPost 486   postNum=${postNum}")
        return newPost1
    }*/
    private fun convertFromStringArrayToIntArryS(num: Int, str: String): ArrayList<Int> {
        /* if (num==100) {
           logi("Utility  479    str=$str ")
       }*/
        var newAr = ArrayList<Int>()
        return littleHelperS(num, str, newAr)
    }

    private fun littleHelperS(num: Int, str: String, arr: ArrayList<Int>): ArrayList<Int> {
        val str = str.split(",")
       /* if (num == 100) {
            logi("Utility  487    str=$str ")
            for (index in 0 until str.size) {
                arr.add(str[index].trim().toInt())
            }
        }*/

        for (index in 0 until str.size) {
            arr.add(str[index].trim().toInt())
        }

      /*    for (index  in 0..2){
              arr.add(222222)
          }*/

        return arr
    }

    private fun convertFromStringArrayToIntArry(ind:Int,str: String): ArrayList<Int> {
        var st=""
   //     logi("Utility 508    ind=$ind     str=$str")
        if (str=="null"){
            when (ind){
                1->st="0,20"
                2->st="0,0,0,0"
                3->st="102, -1, 35, 10, 0, -60, 0, 0"
                else->st="0,0,0,0,0,0,0,0"
            }
        }else{
            st=str
        }
    //   logi("Utility 520    ind=$ind     st=$st")
        var newAr = ArrayList<Int>()
        return littleHelper(st, newAr)
    }

    private fun littleHelper(str: String, arr: ArrayList<Int>): ArrayList<Int> {

        val str = str.split(",")
       // logi("Utility 515 str=$str")

           for (index in 0 until str.size) {
               arr.add(str[index].trim().toInt())
           }
//        logi("UtilityPost 537   arr=${arr.joinToString()}   ")
           return arr
    }

    private fun convertFromStringArrayToIntArry2(str: String): ArrayList<ArrayList<Int>> {
        var newAr = ArrayList<ArrayList<Int>>()
        // logi("Utilities 467  str=$str")
        return littleHelperForMargin(str, newAr)
    }

    private fun littleHelperForMargin(
        str: String,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        initArray()
        val str1 = str.replace("]", "").replace("[", "")
        var arStr = str1.split(",")
        //  logi("Utilities 250 arStr=${arStr}")
        val ind = arStr.size.div(4)
        //  logi("\n\n Utility484  ind=${ind} \n\n")

        when (ind) {
            1 -> helper10(arStr, bigArray)
            2 -> helper20(arStr, bigArray)
            3 -> helper30(arStr, bigArray)
            4 -> helper40(arStr, bigArray)
            5 -> helper50(arStr, bigArray)
            6 -> helper60(arStr, bigArray)
            7 -> helper70(arStr, bigArray)
            8 -> helper80(arStr, bigArray)
            9 -> helper90(arStr, bigArray)
            10 -> helper100(arStr, bigArray)
        }
        return bigArray
    }

    private fun helper10(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        return bigArray
    }

    private fun helper20(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        return bigArray
    }

    private fun helper30(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        return bigArray
    }

    private fun helper40(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        return bigArray
    }

    private fun helper50(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        return bigArray
    }

    private fun helper60(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        for (index in 20..23) {
            arr5.add(arStr[index].trim().toInt())
        }
        bigArray.add(5, arr5)
        return bigArray
    }

    private fun helper70(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        for (index in 20..23) {
            arr5.add(arStr[index].trim().toInt())
        }
        bigArray.add(5, arr5)
        for (index in 24..27) {
            arr6.add(arStr[index].trim().toInt())
        }
        bigArray.add(6, arr6)
        return bigArray
    }

    private fun helper80(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        for (index in 20..23) {
            arr5.add(arStr[index].trim().toInt())
        }
        bigArray.add(5, arr5)
        for (index in 24..27) {
            arr6.add(arStr[index].trim().toInt())
        }
        bigArray.add(6, arr6)
        for (index in 28..31) {
            arr7.add(arStr[index].trim().toInt())
        }
        bigArray.add(7, arr7)
        //  logi("Utility 858  bigArray=${bigArray.joinToString()} ")
        return bigArray
    }

    private fun helper90(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        for (index in 20..23) {
            arr5.add(arStr[index].trim().toInt())
        }
        bigArray.add(5, arr5)
        for (index in 24..27) {
            arr6.add(arStr[index].trim().toInt())
        }
        bigArray.add(6, arr6)
        for (index in 28..31) {
            arr7.add(arStr[index].trim().toInt())
        }
        bigArray.add(7, arr7)
        for (index in 32..35) {
            arr8.add(arStr[index].trim().toInt())
        }
        bigArray.add(8, arr8)
        //  logi("Utility 858  bigArray=${bigArray.joinToString()} ")
        return bigArray
    }

    private fun helper100(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        for (index in 0..3) {
            arr0.add(arStr[index].trim().toInt())
        }
        bigArray.add(0, arr0)
        for (index in 4..7) {
            arr1.add(arStr[index].trim().toInt())
        }
        bigArray.add(1, arr1)
        for (index in 8..11) {
            arr2.add(arStr[index].trim().toInt())
        }
        bigArray.add(2, arr2)
        for (index in 12..15) {
            arr3.add(arStr[index].trim().toInt())
        }
        bigArray.add(3, arr3)
        for (index in 16..19) {
            arr4.add(arStr[index].trim().toInt())
        }
        bigArray.add(4, arr4)
        for (index in 20..23) {
            arr5.add(arStr[index].trim().toInt())
        }
        bigArray.add(5, arr5)
        for (index in 24..27) {
            arr6.add(arStr[index].trim().toInt())
        }
        bigArray.add(6, arr6)
        for (index in 28..31) {
            arr7.add(arStr[index].trim().toInt())
        }
        bigArray.add(7, arr7)
        for (index in 32..35) {
            arr8.add(arStr[index].trim().toInt())
        }
        bigArray.add(8, arr8)
        for (index in 36..39) {
            arr9.add(arStr[index].trim().toInt())
        }
        bigArray.add(9, arr9)
        //  logi("Utility 858  bigArray=${bigArray.joinToString()} ")
        return bigArray
    }
/*   private fun helper10(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        return bigArray
    }

    private fun helper20(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        return bigArray
    }

    private fun helper30(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        for (index in 8..11) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(2, ar3)
        }
        return bigArray
    }

    private fun helper40(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()

        for (index in 0..3) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(0, ar1)
        }
        for (index in 4..7) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(1, ar2)
        }
        for (index in 8..11) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(2, ar3)
        }
        for (index in 12..15) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(3, ar4)
        }
        return bigArray
    }

    private fun helper50(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        return bigArray
    }

    private fun helper60(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()


        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        return bigArray
    }

    private fun helper70(
        arStr: List<String>,
        bigArray: ArrayList<ArrayList<Int>>
    ): ArrayList<ArrayList<Int>> {
        var ar0 = arrayListOf<Int>()
        var ar1 = arrayListOf<Int>()
        var ar2 = arrayListOf<Int>()
        var ar3 = arrayListOf<Int>()
        var ar4 = arrayListOf<Int>()
        var ar5 = arrayListOf<Int>()
        var ar6 = arrayListOf<Int>()

        for (index in 0..3) {
            ar0.add(arStr[index].trim().toInt())
            bigArray.add(0, ar0)
        }
        for (index in 4..7) {
            ar1.add(arStr[index].trim().toInt())
            bigArray.add(1, ar1)
        }
        for (index in 8..11) {
            ar2.add(arStr[index].trim().toInt())
            bigArray.add(2, ar2)
        }
        for (index in 12..15) {
            ar3.add(arStr[index].trim().toInt())
            bigArray.add(3, ar3)
        }
        for (index in 16..19) {
            ar4.add(arStr[index].trim().toInt())
            bigArray.add(4, ar4)
        }
        for (index in 20..23) {
            ar5.add(arStr[index].trim().toInt())
            bigArray.add(5, ar5)
        }
        for (index in 24..27) {
            ar6.add(arStr[index].trim().toInt())
            bigArray.add(6, ar6)
        }
        return bigArray
    }
*/
    /*  private fun helper80(
       arStr: List<String>,
       bigArray: ArrayList<ArrayList<Int>>
   ): ArrayList<ArrayList<Int>> {
       var ar0 = arrayListOf<Int>()
       var ar1 = arrayListOf<Int>()
       var ar2 = arrayListOf<Int>()
       var ar3 = arrayListOf<Int>()
       var ar4 = arrayListOf<Int>()
       var ar5 = arrayListOf<Int>()
       var ar6 = arrayListOf<Int>()
       var ar7 = arrayListOf<Int>()

       for (index in 0..3) {
           ar0.add(arStr[index].trim().toInt())
           bigArray.add(0, ar0)
       }
       for (index in 4..7) {
           ar1.add(arStr[index].trim().toInt())
           bigArray.add(1, ar1)
       }
       for (index in 8..11) {
           ar2.add(arStr[index].trim().toInt())
           bigArray.add(2, ar2)
       }
       for (index in 12..15) {
           ar3.add(arStr[index].trim().toInt())
           bigArray.add(3, ar3)
       }
       for (index in 16..19) {
           ar4.add(arStr[index].trim().toInt())
           bigArray.add(4, ar4)
       }
       for (index in 20..23) {
           ar5.add(arStr[index].trim().toInt())
           bigArray.add(5, ar5)
       }
       for (index in 24..27) {
           ar6.add(arStr[index].trim().toInt())
           bigArray.add(6, ar6)
       }
       for (index in 28..31) {
           ar7.add(arStr[index].trim().toInt())
           bigArray.add(7, ar7)
       }
       return bigArray
   }*/

    /* private fun helper90(
                   arStr: List<String>,
           bigArray: ArrayList<ArrayList<Int>>
       ): ArrayList<ArrayList<Int>> {
           var ar0 = arrayListOf<Int>()
           var ar1 = arrayListOf<Int>()
           var ar2 = arrayListOf<Int>()
           var ar3 = arrayListOf<Int>()
           var ar4 = arrayListOf<Int>()
           var ar5 = arrayListOf<Int>()
           var ar6 = arrayListOf<Int>()
           var ar7 = arrayListOf<Int>()
           var ar8 = arrayListOf<Int>()

           for (index in 0..3) {
               ar0.add(arStr[index].trim().toInt())
               bigArray.add(0, ar0)
           }
           for (index in 4..7) {
               ar1.add(arStr[index].trim().toInt())
               bigArray.add(1, ar1)
           }
           for (index in 8..11) {
               ar2.add(arStr[index].trim().toInt())
               bigArray.add(2, ar2)
           }
           for (index in 12..15) {
               ar3.add(arStr[index].trim().toInt())
               bigArray.add(3, ar3)
           }
           for (index in 16..19) {
               ar4.add(arStr[index].trim().toInt())
               bigArray.add(4, ar4)
           }
           for (index in 20..23) {
               ar5.add(arStr[index].trim().toInt())
               bigArray.add(5, ar5)
           }
           for (index in 24..27) {
               ar6.add(arStr[index].trim().toInt())
               bigArray.add(6, ar6)
           }
           for (index in 28..31) {
               ar7.add(arStr[index].trim().toInt())
               bigArray.add(7, ar7)
           }
           for (index in 32..35) {
               ar8.add(arStr[index].trim().toInt())
               bigArray.add(8, ar8)
           }
           return bigArray
       }*/
    /* fun sendPostToStringFirestore(post: Post) {
         val data = HashMap<String, Any>()
         with(post) {
             data[POST_ID] = 1
             data[POST_NUM] = postNum
             data[POST_LINE_NUM] = lineNum
             data[POST_IMAGE_URI] = imageUri
             data[POST_TEXT] = postText
             data[POST_MARGIN] = postMargin.joinToString()
             data[POST_BACKGROUND] = postBackground
             data[POST_TRANPARECY] = postTransparency
             data[POST_TEXT_SIZE] = postTextSize.joinToString()
             data[POST_PADDING] = postPadding.joinToString()
             data[POST_TEXT_COLOR] = postTextColor
             data[POST_FONT_FAMILY] = postFontFamily
             data[POST_RADIUS] = postRadiuas
             data[POST_TIME_STAMP] = FieldValue.serverTimestamp()
             if (lineSpacing!=null) {
                 data[POST_LINE_SPACING] = lineSpacing!!
             }

         }
         FirebaseFirestore.getInstance().collection(POST_REF).document(post.postNum.toString())
             .set(data)
     }*/

    fun toasti(context: Context, str: String) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show()
    }

    fun logi(
        element1: String,
        element2: String = "",
        element3: String = "",
        element4: String = ""
    ) {
        if (element1 != "" && element2 == "" && element3 == "" && element4 == "") {
            Log.d("gg", "${element1}")
        }
        if (element1 != "" && element2 != "" && element3 == "" && element4 == "") {
            Log.d("gg", "${element1} ,${element2}")
        }
        if (element1 != "" && element2 != "" && element3 != "" && element4 == "") {
            Log.d("gg", "${element1} ,${element2} ,${element3}")
        }
        if (element1 != "" && element2 != "" && element3 != "" && element4 != "") {
            Log.d("gg", "${element1} ,${element2} ${element3},${element4}")
        }
    }




}
