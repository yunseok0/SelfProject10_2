package com.example.selfproject10_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()           // 필요시 사용(API 33 이상)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.pici_icon)
        title = "명화 선호도 투표"

        val voteCount = IntArray(9)   // 자동 0으로 초기화
        val image = arrayOfNulls<ImageView>(9)    // 크기가 9인 ImageView 배열을 만들고 모든 요소를 null로 초기화(nullable 배열)
        val imageId = arrayOf(
            R.id.iv1, R.id.iv2, R.id.iv3,
            R.id.iv4, R.id.iv5, R.id.iv6,
            R.id.iv7, R.id.iv8, R.id.iv9
        )
        val imgName = arrayOf(
            "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매",
            "피아노 레슨", "피아노 앞의 소녀들", "해변에서"
        )

        for (i in imageId.indices) {           // imageId.indices는 until imageId.size 즉 0..8
            image[i] = findViewById(imageId[i])   // 이미지 배열에 ImageView 객체들을 저장
            image[i]?.setOnClickListener {        // image[i]가 null이 아니면 setOnClickListener() 호출. 만약 null이면 아무 일도 하지 않음(안전)
                voteCount[i]++
                Toast.makeText(
                    applicationContext,
                    "${imgName[i]}: 총 ${voteCount[i]} 표",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val btnFinish = findViewById<Button>(R.id.btnResult)
        btnFinish.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)  // ResultActivity 클래스에 전달할 Intent 객체를 생성
            intent.putExtra("VoteCount", voteCount)
            intent.putExtra("ImageName", imgName)
            startActivity(intent)
        }
    }
}