package eskool.com.eskoolapp.LoginPages;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.Profile.ParentProfile;
import eskool.com.eskoolapp.R;

public class LoginPage extends AppCompatActivity {
    TextView tv_forgot_something;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv_forgot_something = (TextView) findViewById(R.id.tv_forgot_something);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_forgot_something.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ForgotPasswordFrgmnt frag1 = new ForgotPasswordFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, TeacherHome.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
