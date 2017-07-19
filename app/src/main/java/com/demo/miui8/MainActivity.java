package com.demo.miui8;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import java.util.List;
import java.util.ArrayList;

import com.miui8.widget.MiuiDialog;

public class MainActivity extends Activity implements OnClickListener
{
    @Override
    public void onClick(View p1)
    {
        // TODO: Implement this method
        SharedPreferences sp=getSharedPreferences("single_choice_item",Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit=sp.edit();
        switch(p1.getId()){
            case R.id.one:
                final MiuiDialog a=new MiuiDialog(MainActivity.this);
                a.setTitle("Title");
                a.setMessage("This is Message");
                a.setPositiveButton("Button", new OnClickListener(){
                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            a.dismiss();
                        }
                    });
                a.show();
                break;
             case R.id.two:
                final MiuiDialog b=new MiuiDialog(MainActivity.this);
                b.setTitle("Title");
                b.setMessage("This is Message");
                b.setNegativeButton("Button 1",null);
                b.setPositiveButton("Button 2", new OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            b.dismiss();
                        }
                    });
                b.show();
               break;
            case R.id.three:
                final MiuiDialog c=new MiuiDialog(MainActivity.this);
                c.setTitle("Title");
                c.setMessage("This is Message");
                c.setNeutralButton("Button 1",null);
                c.setNegativeButton("Button 2",null);
                c.setPositiveButton("Button 3", new OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            c.dismiss();
                        }
                    });
                c.show();
               break;
            case R.id.view_dialog:
                ImageView image=new ImageView(MainActivity.this);
                image.setImageResource(R.drawable.ic_launcher);
                final MiuiDialog d=new MiuiDialog(MainActivity.this);
                d.setTitle("Title");
                d.setView(image);
                d.setNegativeButton("Button 1",null);
                d.setPositiveButton("Button 2", new OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            d.dismiss();
                        }
                    });
                d.show();
                break;
           case R.id.edit_dialog:
                final MiuiDialog e=new MiuiDialog(MainActivity.this);
                e.setTitle("Title");
                e.setEditText("EditText","hint");
                e.setNegativeButton("Button 1",null);
                e.setPositiveButton("Button 2", new OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            e.dismiss();
                            Toast.makeText(MainActivity.this,e.getEditText(),0).show();
                        }
                    });
                e.show();
               break;
             
            case R.id.click_item_dialog:
                final MiuiDialog f=new MiuiDialog(MainActivity.this);
                f.setTitle("Title");
                final List<String> lf=new ArrayList<>();
                for(int i=0;i<6;i++){
                    lf.add("item "+i);
                }
                f.setSingleClickItem(lf, new OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
                        {
                            // TODO: Implement this method
                            Toast.makeText(MainActivity.this,lf.get(p3),0).show();
                            f.dismiss();
                        }
                    });
                f.setPositiveButton("Cancel", null);
                f.show();
                break;
            case R.id.single_choice_dialog:
                final MiuiDialog g=new MiuiDialog(MainActivity.this);
                g.setTitle("Title");
                final List<String> lg=new ArrayList<>();
                for(int i=0;i<6;i++){
                    lg.add("item "+i);
                }
                g.setSingleChoiceItems(sp.getInt("item",0), lg, new OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
                        {
                            // TODO: Implement this method
                            edit.putInt("item",p3).commit();
                            Toast.makeText(MainActivity.this,lg.get(p3),0).show();
                            g.dismiss();
                        }
                    });
                g.setPositiveButton("Cancel", null);
                g.show();
                break;
            case R.id.multi_choices_dialog:
                final MiuiDialog h=new MiuiDialog(MainActivity.this);
                h.setTitle("Title");
                final List<String> lh=new ArrayList<>();
                for(int i=0;i<6;i++){
                    lh.add("item "+i);
                }
                h.setMultiChoiceItems(0, lh, new OnItemClickListener(){

                        @Override
                        public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
                        {
                            // TODO: Implement this method
                        
                        }
                 });
                h.setNegativeButton("Cancel", null);
                h.setPositiveButton("confirm", new OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this metho
                            
                        }
                });
                h.show();
                break;
        }
    }
    Button one,two,three,view,mEdit,itemClick,singleChoice,multiChoice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.main);
        
        one=(Button)findViewById(R.id.one);
        one.setOnClickListener(this);
        
        two=(Button)findViewById(R.id.two);
        two.setOnClickListener(this);
        
        three=(Button)findViewById(R.id.three);
        three.setOnClickListener(this);
        
        view=(Button)findViewById(R.id.view_dialog);
        view.setOnClickListener(this);
        
        mEdit=(Button)findViewById(R.id.edit_dialog);
        mEdit.setOnClickListener(this);
        
        itemClick=(Button)findViewById(R.id.click_item_dialog);
        itemClick.setOnClickListener(this);
        
        singleChoice=(Button)findViewById(R.id.single_choice_dialog);
        singleChoice.setOnClickListener(this);
        
        multiChoice=(Button)findViewById(R.id.multi_choices_dialog);
        multiChoice.setOnClickListener(this);
        
        
        
    }
}
