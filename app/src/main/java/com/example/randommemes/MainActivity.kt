package com.example.randommemes

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.randommemes.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //https://meme-api.com/gimme
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getData()
    }

    private fun getData() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.show()


        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataclass?> {
            override fun onResponse(
                call: Call<responseDataclass?>,
                response: Response<responseDataclass?>
            ) {
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<responseDataclass?>, t: Throwable) {
            Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                .show()
            }


        })
    }
}
