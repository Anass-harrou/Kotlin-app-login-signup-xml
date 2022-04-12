package com.example.login_kt

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val editTextTextEmailAddress:EditText=findViewById(R.id.editTextTextEmailAddress)
        val editTextTextPassword:EditText=findViewById(R.id.editTextTextPassword)
        val button:Button=findViewById(R.id.button)
            val button1:Button=findViewById(R.id.button1)

        button.setOnClickListener{
            /*if(editTextTextEmailAddress.text.isNullOrBlank()&&editTextTextPassword.text.isNullOrBlank()){
                Toast.makeText(this, "Please fill the required fieleds", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "${editTextTextEmailAddress.text} is logedin!!", Toast.LENGTH_SHORT).show()
                val intent=Intent(this@MainActivity,secondActivity::class.java)
                intent.putExtra("usernaem",""+editTextTextEmailAddress.text.toString())
                startActivity(intent)
            }*/

            val email:String =editTextTextEmailAddress.text.toString()
            val pass:String=editTextTextPassword.text.toString()
            val url:String="http://172.16.1.182/API%20PHP/Operations/Login.php"
            val params=HashMap<String,String>()
            params["email"]=email
            params["password"]=pass
            val jO= JSONObject(params as Map<*, *>)
            val rq: RequestQueue = Volley.newRequestQueue(this@MainActivity)
            val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
                try {
                    if(res.getString("success").equals("1")){
                        val intent=Intent(this@MainActivity,secondActivity::class.java)
                        intent.putExtra("UserName",res.getString("user"))
                        startActivity(intent)
                        editTextTextEmailAddress.text.clear()
                        editTextTextPassword.text.clear()
                    } else { alert("Message d'Erreur !",res.getString("message")) }

                }catch (e:Exception){
                    alert("Message d'Erreur !",""+e.message)
                }
            }, Response.ErrorListener { err->
                alert("Message d'Erreur !",""+err.message)
            })
            rq.add(jor)

        }
            button1.setOnClickListener{
                val intent2=Intent(this@MainActivity,SignUpActivity::class.java)
                startActivity(intent2)
            }
    }

    override fun onCreateOptionsMenu(men: Menu?): Boolean {
        val menu=menuInflater
        menu.inflate(R.menu.menu,men)
        return true
    }


    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@MainActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}