package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.extensions.isKeyboardOpen
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(),TextView.OnEditorActionListener, View.OnClickListener {
    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        this.hideKeyboard()
        val (phrase,color) = benderObj.listenAnswer(messageEt.text.toString())
        messageEt.setText("")
        val(r,g,b) = color
        benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
        textTxt.text = phrase
        return true
    }

    override fun onClick(p0: View?) {
        if (!this.isKeyboardOpen())
            this.hideKeyboard()
        if(p0?.id == R.id.iv_send)
        {
            val (phrase,color) = benderObj.listenAnswer(messageEt.text.toString())
            messageEt.setText("")
            val(r,g,b) = color
            benderImage.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
    }

    lateinit var benderImage: ImageView
    lateinit var textTv: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView
    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTv = tv_text
        messageEt = et_message
        sendBtn = iv_send

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        textTv.text = benderObj.askQuestion()
        setBenderColor(benderObj.status.color)
    }

    private fun sendAnswer() {
        hideKeyboard()
        val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
        messageEt.setText("")
        setBenderColor(color)
        textTv.text = phrase
    }

    private fun setBenderColor(color: Triple<Int, Int, Int>) {
        val (r, g, b) = color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.run {
            putString("STATUS", benderObj.status.name)
            putString("QUESTION", benderObj.question.name)
        }
    }
}
