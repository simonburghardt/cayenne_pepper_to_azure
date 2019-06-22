package com.softbankrobotics.chatbotdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.View
import android.widget.TextView
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.context.RobotContext
import com.aldebaran.qi.sdk.`object`.conversation.Chat
import com.aldebaran.qi.sdk.`object`.conversation.Phrase
import com.aldebaran.qi.sdk.`object`.conversation.*
import com.aldebaran.qi.sdk.`object`.locale.Language
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.`object`.locale.Region
import com.aldebaran.qi.sdk.builder.ChatBuilder
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.builder.TopicBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import java.util.*

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    private var chat: Chat? = null
    private var chat2: Chat? = null
    // private var chat2: Chat? = null
    private var qiContext: QiContext? = null
    private var testBot = BotConnect()
    internal var localeG = Locale(Language.GERMAN, Region.GERMANY)
    var azurebot: AzureChatbot? = null
    private lateinit var textView2: TextView
    private lateinit var editText: TextInputEditText
    var test2: StandardReplyReaction? = null
    var phra: Phrase? = null
    var phraStr: String? = null
    private var str: String? = null
    private var inpTxt: String = "default value"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "... asking for register ...")
        QiSDK.register(this, this)
        Log.d(TAG, "... registered.")
        textView2 = findViewById(R.id.textTest)
        editText = findViewById(R.id.requestChatbot)
    }

    override fun onRobotFocusGained(theContext: QiContext) {

        qiContext = theContext
        Log.d(TAG, "runChat()")
        // Create a QiChatbot
        val say = SayBuilder.with(qiContext)
                .withText("Getting ready to chat")
                .build()
        say.run()


        //val topic = TopicBuilder.with(qiContext)
        // .withResource(R.raw.demo_qichat)
        //.build()
        // Create a new QiChatbot.
        //Log.i("TAG", "Create qichatbot")


        //val qiChatbot = QiChatbotBuilder.with(qiContext)
        //  .withTopic(topic)
        // .build()
        //Create a new Chatbot to handle Azure chatbot


        if (this.qiContext != null) {
            val azureChatbot = AzureChatbot(this.qiContext)
            // val qnAChatbot = QnAChatbot(this.qiContext)
            ///Create a new Chat action.
            Log.i("TAG", "Create chat action")
            chat = ChatBuilder.with(qiContext)
                    .withChatbot(azureChatbot)
                    //.withChatbot(qiChatbot, qnAChatbot)
                    .build()
            chat?.async()?.run()
            Log.i("TAG", "Async run started...")

            //Aufruf der BotConnect Insatanz "testBot" und der Methode AnswerTo, um eine Antwort vom Chatbot zu bekommen. Abspeichern der antwort als String
            //Ausgabe der Antwort via Methode"LoadScreen2" in dieser Klasse. Diese Methode ist auf ein UI Button OnClick gebunden.
            str = testBot.answerTo(inpTxt)

            //Normalerweise ist folgende Methode für die Wiedergabe zu verwenden. Diese enthält "answerTo" und die AI response.
            //Das ergebnis ist ein objekt der Klasse StandardReplyReaction auf welche man die Methode .chatBOtReaction aufrufen kann, um die Reaction als WErt zu bekommen
            //var test = Phrase("hallo")
            //azureChatbot.replyTo(test,localeG)

            // Test Code, nicht verwendbar so

            //var test = Phrase("hallo")
            //phra = chat?.saying
            //phraStr = phra.toString()
            //azureChatbot.qiContext.conversation.makeSay(,testL)
            //azureChatbot.acknowledgeHeard(test,localeG)
            //azureChatbot.acknowledgeSaid(test,localeG)
            //azureChatbot.qiContext.robotContext
            //azureChatbot.qiContext.conversation.makeSay(azureChatbot.qiContext.robotContext, test)
            //Log.i("TAG", "ChatSaying: $p")
            //test2 = azureChatbot.replyTo(test, localeG)
            //var z = azureChatbot.replyTo(test, localeG)?.chatbotReaction
            //Log.i(TAG, "StandardReplyReaction: $z")
            //var t:String = z?.chatbotReaction.toString()
            // Log.i(TAG, "StandardReplyReaction.ChatbotReaction ToString: $t")
            // str = testBot.answerTo("hallo du")
            //str2 = str
            //Log.i(TAG, "BotAnswer: $str")
            //str = testBot.answerTo(inpTxt)
            //azureChatbot.replyTo(test,localeG)
        }
    }

    override fun onRobotFocusLost() {
        Log.i(TAG, "Focus lost")
    }

    override fun onRobotFocusRefused(reason: String) {
        Log.i(TAG, "Focus refused: $reason")
    }

    fun initInteraction() {
    }

    //fun answerTest(txt:String) {
      //  str = testBot.answerTo(txt)
    //}


    fun loadScreen2(view: View) {

        //var reaction:ChatbotReaction? = test2?.chatbotReaction
        //Log.i(TAG, "ChatBot: $reaction")
        //reaction = test2.chatbotReaction
        inpTxt = editText.text.toString()
        textView2.text = str
        //answerTest(inpTxt)
        // answerTest()
        //textView2.text = reaction.toString()
        //val azureChatbot2 = AzureChatbot(qiContext)
        //if (azurebot != null) {
        //azurebot.replyTo(test, localeG)
        //val intent = Intent(this, Main2Activity::class.java)
        //startActivity(intent)
    }

    fun loadScreen3(view: View) {

            inpTxt = editText.text.toString()
            textView2.text = inpTxt
    }

}

