package com.example.tumbler

import android.content.ContentResolver
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.session.MediaSession
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.databinding.ActivityCreatePostBinding
import com.example.tumbler.model.entity.addpost.CreatePostBody
import jp.wasabeef.richeditor.RichEditor
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.util.*


class CreatePostActivity : AppCompatActivity() {

    private val viewModel: CreatePostViewModel by viewModel()

    private lateinit var binding: ActivityCreatePostBinding
    private var styleEditor: RichEditor? = null

    private val pickImage = 100
    private val pickMusic = 10
    private var imageUri: Uri? = null
    private var musicUri: Uri? = null
    private var styleIndex: Int = 0
    private var myshered:SharedPreferences?=null

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
        myshered=getSharedPreferences("myshared",0)
        var token=myshered?.getString("access_token","")
        var blog_id=myshered?.getString("blog_id","")

        addImage()
        addStyle()
        addUrl()
        addMusic()
        submitPost(token!!,blog_id!!.toInt())
        spinnerTumblrAccount()

        // some padding are added since the editor refuses to add an initial audio, image, video as the first item in the editor
        styleEditor!!.setPadding(10, 10, 10, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Log.i("Hala", imageUri.toString())

//            var encodedImg=encoder(imageUri!!)
//            Log.i("Hala",encodedImg.toString())


            if (imageUri.toString().startsWith("content://media/external/images")) {
                styleEditor!!.insertImage(imageUri.toString(), "Image Not Found", 200, 200)
            } else {
                // TODO: Make the with of the imgae match parent
                styleEditor!!.insertVideo(imageUri.toString(), 200, 200)
            }
        } else if (resultCode == RESULT_OK && requestCode == pickMusic) {
            musicUri = data?.data
            Log.i("Hala", musicUri.toString())
            // some padding are added since the editor refuses to add an initial audio, image, video as the first item in the editor
//            styleEditor!!.setPadding(10,10,10,10)
            styleEditor!!.insertAudio(musicUri.toString())
        }
    }
    /**
     * while adding a new post/ blog , you could add a new photo from gallery  using this function
     * */
    fun addImage() {
        binding.addPhoto.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

     fun encoder(imageUri: Uri): String {
        val input = this.contentResolver.openInputStream(imageUri)
        //val bm = BitmapFactory.decodeResource(resources, R.drawable.test)
        val image = BitmapFactory.decodeStream(input, null, null)
        //encode image to base64 string
        val baos = ByteArrayOutputStream()
        //bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var imageBytes = baos.toByteArray()

        return android.util.Base64.encodeToString(imageBytes, android.util.Base64.NO_WRAP)
        //return Base64.getEncoder().encodeToString(imageBytes) // Not Worked, too.
    }

    fun getMediaType(imageUri: Uri):String{
        val cR: ContentResolver = this.getContentResolver()
        val mime = MimeTypeMap.getSingleton()
        val type = mime.getExtensionFromMimeType(cR.getType(imageUri!!))
        return type.toString()

    }
/**
     * while adding a new post/ blog , you could change the text style/formating using this function
     * */
    fun addStyle() {
        binding.addStyle.setOnClickListener {
//    Todo: removeFormat before applying the new one
            when (styleIndex % 7) {
                0 -> {
                    styleEditor!!.setOutdent()
                    styleEditor!!.setIndent()
                }
                1 -> {
                    styleEditor!!.undo()
                    styleEditor!!.setHeading(4)
                }

                2 -> {
                    styleEditor!!.undo()
                    styleEditor!!.setHeading(2)
                }
                3 -> {
                    styleEditor!!.undo()
                    styleEditor!!.setNumbers()
                }
                4 -> {
                    styleEditor!!.undo()
                    styleEditor!!.setBullets()
                }
            }

            styleIndex = (styleIndex + 1) % 7
            Log.i("Hala", styleIndex.toString())
        }
    }

    fun addUrl() {
        binding.addUrl.setOnClickListener {
            showURLDialog()
        }
    }

    fun addMusic() {
        binding.addMusic.setOnClickListener {
            val music = Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(music, pickMusic)
        }
    }

    fun submitPost(token:String,blog_id:Int) {
        binding.toolbarCreatePost.submitPost.setOnClickListener {

            if (styleEditor!!.html == null || styleEditor!!.html.toString().isEmpty() || styleEditor!!.html.toString() == "<br>") {
                Log.i("Hala", "will not post")
            } else {
                Log.i("Hala", styleEditor!!.html.toString())
                val cal = Calendar.getInstance(TimeZone.getTimeZone("Egypt/Cairo"))
                val time =" ${cal.get(Calendar.DAY_OF_MONTH)} - ${cal.get(Calendar.MONTH)} - ${cal.get(
                    Calendar.YEAR
                )}"
                Log.i("Hala", "time=$time")
                val postBody = CreatePostBody(
                     "published",
                 " ",
                 "general",
                    styleEditor!!.html.toString()
                )

                var authToken="Bearer ${token}"
                viewModel.createPost(authToken,postBody, blog_id)
            }
        }
    }

    fun spinnerTumblrAccount() {
        val spinner: Spinner = binding.spinnerCreatePost
        var tumblrAccounts = arrayOf<String>("Hala", "Nada", "Mariam", "Dina")

        ArrayAdapter(this, android.R.layout.simple_spinner_item, tumblrAccounts).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun showURLDialog() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Adding Url")

        val linearlayout = LinearLayout(this)
        linearlayout.orientation = LinearLayout.VERTICAL
        // Set up the input
        val word = EditText(this)
        val Url = EditText(this)
        linearlayout.addView(word)
        linearlayout.addView(Url)

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        word.setHint("Enter the hyper word")
        builder.setView(word)
        Url.setHint("Add a Link Url")

        builder.setView(linearlayout)
        // Set up the buttons

        builder.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which ->
                if (Patterns.WEB_URL.matcher(Url.text.toString()).matches() == false) {
                    errorUrlDialog()
                } else {
                    styleEditor!!.insertLink(Url.text.toString(), word.text.toString())
                }
            }
        )
        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() }
        )

        builder.show()
    }

    fun errorUrlDialog() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Title")
        builder.setMessage("Invalid Url")
        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() }
        )

        builder.show()
    }
}
