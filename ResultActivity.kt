package com.example.selfproject10_2

import android.R.attr.text
import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)
        title = "투표 결과"

        // 앞 화면에서 보낸 투표 결과 값을 받는다. 인텐트 데이터 수신
        //var intent = intent  <== 이문장은 생략하는 것이 좋음. intent는 현재 액티비티에 전달된 Intent 객체이므로 바로 사용
        val voteResult = intent.getIntArrayExtra("VoteCount") ?: return
        val imageName = intent.getStringArrayExtra("ImageName") ?: return

        // 그림 리소스 배열
        val imageField = arrayOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        )

        // 9개의 TextView, RatingBar ID 배열
        val tvIDs = arrayOf(R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9)
        val rbarIDs = arrayOf(R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9)

        // 각 TextVeiw 및 RatingBar에 넘겨 받은 값을 반영.
        for (i in voteResult.indices) {
            findViewById<TextView>(tvIDs[i]).text = imageName[i]       // tvIDs[i]에 해당하는 텍스트뷰의 text속성에 imageName[i] 값을 설정
            findViewById<RatingBar>(rbarIDs[i]).rating = voteResult[i].toFloat()
        }

        // 가장 많은 투표 수를 받은 이미지의 인덱스 찾기
        var maxIndex = 0
        for (i in voteResult.indices) {
            if (voteResult[i] > voteResult[maxIndex]) {
                maxIndex = i
            }
        }

        // 상단에 다득표 그림 이름과 이미지를 표시할 텍스트뷰와 이미지뷰
        val tvTop = findViewById<TextView>(R.id.tvTop)
        val ivTop = findViewById<ImageView>(R.id.ivTop)

        tvTop.apply {
            text = imageName[maxIndex]
            setTextColor(Color.BLUE)
            setTypeface(null, Typeface.BOLD)
        }
        ivTop.setImageResource(imageField[maxIndex])

        // 돌아가기 버튼
        findViewById<Button>(R.id.btnReturn).setOnClickListener {
            finish()
        }

    }
}