package com.example.login_kt

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val btn2: Button =findViewById(R.id.button2)
        val btn: Button =findViewById(R.id.button)
        val editTextTextUsername: EditText =findViewById(R.id.editTextTextUsername)
        val editTextTextEmailAddress: EditText =findViewById(R.id.editTextTextEmailAddress)
        val editTextTextPassword: EditText =findViewById(R.id.editTextTextPassword)

        btn2.setOnClickListener{
            val intent2= Intent(this,MainActivity::class.java)
            startActivity(intent2)
        }
        btn.setOnClickListener{
            /*if(editTextTextUsername.text.isNullOrBlank()&&editTextTextEmailAddress.text.isNullOrBlank()&&editTextTextPassword.text.isNullOrBlank()){
                Toast.makeText(this, "Please fill the required fieleds", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Thanks ${editTextTextUsername.text} for regestriation, go login now!", Toast.LENGTH_SHORT).show()
                val intent=Intent(this,MainActivity::class.java)
                intent.putExtra("username",""+editTextTextEmailAddress.text.toString())
                startActivity(intent)
            }*/

            val username:String=editTextTextUsername.text.toString()
            val email:String =editTextTextEmailAddress.text.toString()
            val pass:String=editTextTextPassword.text.toString()
            val url:String="http://172.16.1.182/API%20PHP/Operations/Register.php"
            val params=HashMap<String,String>()
            params["username"]=username
            params["email"]=email
            params["password"]=pass
            val jO= JSONObject(params as Map<*, *>)
            val rq: RequestQueue = Volley.newRequestQueue(this@SignUpActivity)
            val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
                try {
                    if(res.getString("success").equals("1")){
                        val intent=Intent(this@SignUpActivity,MainActivity::class.java)
                        startActivity(intent)
                        editTextTextUsername.text.clear()
                        editTextTextEmailAddress.text.clear()
                        editTextTextPassword.text.clear()
                    } else { alert("Message d'Erreur !",res.getString("message")) }

                }catch (e:Exception){
                    alert("Message d'Erreur !",""+e.message)
                }
            },Response.ErrorListener { err->
                alert("Message d'Erreur !",""+err.message)
            })
            rq.add(jor)
        }

    }
    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@SignUpActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}