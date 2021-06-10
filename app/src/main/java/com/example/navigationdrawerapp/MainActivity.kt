package com.example.navigationdrawerapp

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView



class MainActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {

        val a = getResources().getBoolean(R.bool. portrait_only)
        val b = getResources().getBoolean(R.bool. portrait_only2)

        if (a or b) {
            setRequestedOrientation (ActivityInfo. SCREEN_ORIENTATION_PORTRAIT );
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTitle("")
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        val mediaController = MediaController(this)

        val video_view: VideoView = findViewById(R.id.video_view)

        mediaController.setAnchorView(video_view)

        val uri: Uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video1)

        video_view.setMediaController(mediaController)
        video_view.setVideoURI(uri)
        video_view.requestFocus()
        video_view.start()

        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val imageviewfb: ImageButton =findViewById(R.id.imageViewfb)
        imageviewfb.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
            startActivity(i)

        })
        val imageviewig: ImageButton =findViewById(R.id.imageViewig)
        imageviewig.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"))
            startActivity(i)

        })
        val imageviewtw: ImageButton =findViewById(R.id.imageViewsnap)
        imageviewtw.setOnClickListener(View.OnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com"))
            startActivity(i)

        })
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_messages -> {
                val intent2 = Intent(this, ActivityMessages::class.java)
                startActivity(intent2)

            }
            R.id.nav_friends -> {
                val intent3 = Intent(this, ActivityFriends::class.java)
                startActivity(intent3)

            }
            R.id.nav_update -> {
                val intent4 = Intent(this, ActivityProfile::class.java)
                startActivity(intent4)

            }
            R.id.strona -> {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://uindowebsite.000webhostapp.com/index.php"))
                startActivity(i)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}