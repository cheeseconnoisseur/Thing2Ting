package com.dezon.arturo.thing2ting

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.JsonReader
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONObject




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)

        val b1 = findViewById(R.id.b1) as Button?
        val t1 = findViewById(R.id.t1)as EditText
        val t2 = findViewById(R.id.t2)as TextView

        val fab1 = findViewById(R.id.fab1) as FloatingActionButton?
        fab1!!.setOnClickListener { view ->
            var ublookup:String = t2.text.toString()
            var ublink = "https://www.urbandictionary.com/define.php?term=$ublookup"
            Snackbar.make(view, ublink, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            val intent = Intent(this, ub2 ::class.java)
            intent.putExtra("link", ublookup)
            startActivity(intent)}


        var hi ="hi"


        b1!!.setOnClickListener {
            var legfile : String = applicationContext.assets.open("legible.txt").bufferedReader().use {
                it.readText()
            }
            var roadfile : String = applicationContext.assets.open("road.txt").bufferedReader().use {
                it.readText()
            }

            var temproad = roadfile.split(",")

            var templeg = legfile .split(",")


            var leg:String = t1.text.toString()


            var split1 = leg.split(" ")

            var f1 = ""
            var f2 = ""
            var f3 = ""
            var f4 = ""
            var cmon = ""
            var cmon1 = ""
            var tv = 1
            var ay = "ay"










            for (i in split1) if (tv % 2 == 0   ) {
                tv = tv + 1
                cmon1 = cmon1 + i + " "
            }else {
                cmon1 = cmon1 + i + "^"
                tv = tv + 1
            }
            //t2.text = cmon1




            var part2 =cmon1.split("^")
            //var part21 = part2.toString()
            //t2.text = part21





            for (i in part2) if (templeg.contains(i) ) {
                var ay = templeg.indexOf(i)
                var hagay = temproad.get(ay)

                f2 = f2 + hagay + " " }else f2 = f2 + i + " "
            //t2.text = f2



            var parting2 =f2.split(" ")
            tv = 1

            //var part221 = parting2.toString()
            //t2.text = part221




            for (i in parting2) if (tv % 2 == 1   ) {
                tv = tv + 1
                cmon = cmon + i + " "
            }else {
                cmon = cmon + i + "^"
                tv = tv + 1
            }
            //t2.text = cmon





            var part3 =cmon.split("^")

            //var part21 = part3.toString()
            //t2.text = part21

            //for (b in part3)
            //  b.replace(",.",", "

            for (s in part3) if (templeg.contains(s)  ) {
                var ay = templeg.indexOf(s)
                var hagay = temproad.get(ay)

                f3 = f3 + hagay + " " }else f3 = f3 + s + " "

            var parting3 =f3.split(" ")

            for (i in parting3) if (templeg.contains(i)  ){
                var ay = templeg.indexOf(i)
                var hagay = temproad.get(ay)

                f4 = f4 + hagay + " "} else f4 = f4 + i + " "

            t2.text = f4
        }



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }


}
