package cg.tutorials.tutionflow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cg.tutorials.tutionflow.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    var welcome = "Manage your tutoring business with ease and efficiency. \n" +
            "TutorTrack is designed to help local tutors like you streamline daily operations." +
            " From tracking attendance to managing monthly expenditures, we've got you covered."
    lateinit var splashScreenBinding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreenBinding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        splashScreenBinding.titleSplash.text="Welcome"
        splashScreenBinding.tvSplash.text=welcome
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}