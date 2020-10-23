package com.neel.desai.workdemo

import com.neel.desai.workdemo.fragment.HomeFragmentNew
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var mToolbar: androidx.appcompat.widget.Toolbar? = null
    var activity: MainActivity? = null
    private var mFrameContainer: FrameLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        setToolbar()
        initView()
    }

    public fun setToolbar() {
        mToolbar = findViewById(R.id.toolbar) as Toolbar;
        mToolbar!!.contentInsetStartWithNavigation =
            resources.getInteger(R.integer.dimen_spacing_between_toolbar_icon_and_title)
        setSupportActionBar(mToolbar);


        supportActionBar?.setDisplayShowTitleEnabled(false)
        mToolbar!!.setTitleTextColor(ContextCompat.getColor(this, R.color.color_white))
        mToolbar!!.title = "Match";
        mToolbar!!.setNavigationOnClickListener {
            onBackPressed()
        };
    }

    fun initView() {
        mFrameContainer = findViewById(R.id.frame_container) as FrameLayout
        switchContent(HomeFragmentNew(), null)
    }


    /**
     * Switch Fragments
     */
    @SuppressLint("NewApi")
    fun switchContent(fragment: Fragment, bundle: Bundle?) {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        mToolbar!!.title = "Matches"
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_from_left,
            R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_to_right
        )
        fragmentTransaction.replace(R.id.frame_container, fragment).commit()
    }

    override fun onBackPressed() {
        ShowExit(false)
        super.onBackPressed()
    }

    fun ShowExit(Isforlogout: Boolean) {
        var msg: String = "";

        if (Isforlogout) {
            msg = "Are you sure you want to Logout?"
        } else {
            msg = "Are you sure you want to exit?"
        }

        var Title = ""
        Title = if (Isforlogout) {
            "Confirm Logout"
        } else {
            "Confirm Exit"
        }
        val alertDialogBuilder = android.app.AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(Title)
        alertDialogBuilder.setMessage(msg)
            .setCancelable(true)
            .setPositiveButton(getString(R.string.yes)) { dialog, id -> finish() }
            .setNegativeButton(
                getString(R.string.cancel)
            ) { dialog, id -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}