package com.example.tumbler

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.databinding.ActivityCreatePostBinding
import com.example.tumbler.databinding.ActivityCreatePostBinding.inflate
import com.example.tumbler.databinding.EnterUrlDialogBinding.inflate
import jp.wasabeef.richeditor.RichEditor

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private var styleEditor: RichEditor? = null

    private val pickImage = 100
    private var imageUri: Uri? = null
    private var styleIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Hiding the default action bar
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        styleEditor = binding.richEditorCreatePost
        styleEditor!!.setPlaceholder("Add something, if you would like")
        addImage()
        addStyle()
        addUrl()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            // TODO: Make the with of the imgae match parent
            styleEditor!!.insertImage(imageUri.toString(), "Image Not Found", 200, 200)
        }
    }
    /**
     * while adding a new post/ blog , you could add a new photo from gallery  using this function
     * */
    fun addImage() {
        binding.addPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }
/**
 * while adding a new post/ blog , you could change the text style/formating using this function
 * */
    fun addStyle() {
        binding.addStyle.setOnClickListener {
//    Todo: removeFormat before applying the new one
            when (styleIndex % 7) {
                1 -> styleEditor!!.setHeading(4)
                2 -> styleEditor!!.setHeading(2)
                3 -> styleEditor!!.setIndent()
                4 -> styleEditor!!.setOutdent()
                5 -> styleEditor!!.setNumbers()
                6 -> styleEditor!!.setBullets()
            }

            styleIndex = (styleIndex + 1) % 7
        }
    }

    fun addUrl() {
        binding.addUrl.setOnClickListener {
//Todo: To be taken from an alert Dialog
            styleEditor!!.insertLink("www.google.com", "google")

        }
    }

}
