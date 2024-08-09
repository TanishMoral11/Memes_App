package com.example.randommemes

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.randommemes.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Declaring a lateinit variable for View Binding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Handling window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fetch the first meme on activity creation
        getData()

        // Set an onClickListener to fetch a new meme when the button is clicked
        binding.btnNewMeme.setOnClickListener {
            getData()
        }
    }

    // Function to fetch meme data using Retrofit
    private fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.show()

        // Making a network request using Retrofit
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataclass?> {
            override fun onResponse(
                call: Call<responseDataclass?>,
                response: Response<responseDataclass?>
            ) {
                // Set the meme author and title text views
                binding.memeAuthor.text = response.body()?.author
                binding.memeTitle.text = response.body()?.title
                // Load the meme image using Glide
                Glide.with(this@MainActivity)
                    .load(response.body()?.url)
                    .into(binding.memeImage)

                // Dismiss the progress dialog
                progressDialog.dismiss()
            }

            override fun onFailure(call: Call<responseDataclass?>, t: Throwable) {
                // Show a toast message if the request fails
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss() // Dismiss the progress dialog on failure as well
            }
        })
    }
}
