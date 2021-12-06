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

import java.util.regex.Pattern;

public class LogInActivityTest extends TestCase {
    UserAccount userAccount;

    @Before
    public void setUp(){
        userAccount = new UserAccount();
    }

    public boolean isWriteDone(){
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Pattern idPattern = Pattern.compile("(^[0-9]*$)");

        if(((userAccount.getEmail().toString().length() == 0) || (!emailPattern.matcher(userAccount.getEmail().toString()).matches())) ||
        ((userAccount.getHackbun().toString().length() == 0) || (!idPattern.matcher(userAccount.getHackbun().toString()).matches())))
        {
            return false;
        }
        else{
            return true;
        }
    }

    public void testOnClick() {
        boolean isWriteDone;

        userAccount.setEmail("lyuss0213@naver.com");
        userAccount.setHackbun("유준호");

        isWriteDone = isWriteDone();

        assertEquals(isWriteDone, true);
    }
}