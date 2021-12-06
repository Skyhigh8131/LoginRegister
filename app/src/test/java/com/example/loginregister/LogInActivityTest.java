package com.example.loginregister;

import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class LogInActivityTest extends TestCase {
    UserAccount userAccount;

    @Before
    public void setUp(){
        userAccount = new UserAccount();
    }

    public boolean isWriteCorrect(){
        Pattern emailPattern = Pattern.compile("^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$");
        Pattern idPattern = Pattern.compile("(^[0-9]*$)");

        String id = userAccount.getHackbun().toString();
        String email = userAccount.getEmail().toString();

        if(id.length() != 0 && email.length() != 0){
            return(emailPattern.matcher(email).matches() && idPattern.matcher(id).matches());
        }
        else {
            return false;
        }
    }

    @Test
    public void testOnClick() {


        userAccount.setEmail("");
        userAccount.setHackbun("");

        boolean isWriteDone = isWriteCorrect();

        assertEquals(true, isWriteDone);
    }
}