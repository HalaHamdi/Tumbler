package com.example.tumbler

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.tumbler.databinding.ActivityCreatePostBinding
import com.example.tumbler.model.entity.addpost.CreatePostBody
import jp.wasabeef.richeditor.RichEditor
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*


class CreatePostActivity : AppCompatActivity() {

    private val viewModel:CreatePostViewModel by viewModel()

    private lateinit var binding: ActivityCreatePostBinding
    private var styleEditor: RichEditor? = null

    private val pickImage = 100
    private val pickMusic = 10
    private var imageUri: Uri? = null
    private var musicUri: Uri? = null
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
        addMusic()
        submitPost()
        spinnerTumblrAccount()

        // some padding are added since the editor refuses to add an initial audio, image, video as the first item in the editor
        styleEditor!!.setPadding(10, 10, 10, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            Log.i("Hala", imageUri.toString())

            val file = File(imageUri.toString())
            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file)

            Log.i("Hala", "file=${file}  requestfile=${requestFile}")

            // MultipartBody.Part is used to send also the actual file name

            // MultipartBody.Part is used to send also the actual file name
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

            // add another part within the multipart request

            // add another part within the multipart request
            val fullName: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")

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

    fun submitPost() {
        binding.toolbarCreatePost.submitPost.setOnClickListener {

            if (styleEditor!!.html == null || styleEditor!!.html.toString().isEmpty() || styleEditor!!.html.toString() == "<br>") {
                Log.i("Hala", "will not post")
            } else {
                Log.i("Hala", styleEditor!!.html.toString())
                val cal=Calendar.getInstance(TimeZone.getTimeZone("Egypt/Cairo"))
                val time:String="${cal.get(Calendar.DAY_OF_MONTH)} - ${cal.get(Calendar.MONTH)} - ${cal.get(
                    Calendar.YEAR
                )} "
                Log.i("Hala", "time=${time}")
                val postBody= CreatePostBody(
                    "published",
                    time,
                    "general",
                    styleEditor!!.html.toString()
                )

                viewModel.createPost(postBody, 5)
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
