package com.miui8.widget;

import java.util.List;
import android.graphics.Color;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.graphics.drawable.*;

public class MiuiDialog
{
    private Dialog mDialog;
    private Context mContext;
    private CharSequence PositiveText=null;
    private CharSequence NegativeText=null;
    private CharSequence NeutralText=null;
    private CharSequence messageText=null;
    private CharSequence setEditText=null;
    private CharSequence hint=null;
    private CharSequence titleText=null;
    private int defStyle;
    private int PositiveId=0;
    private int NegativeId=0;
    private int NeutralId=0;
    private int messageId=0;
    private int EditTextId=0;
    private int titleId=0;
    private int hintId=0;
    private boolean isPositiveButtonShow=false;
    private boolean isNegativeButtonShow=false;
    private boolean isNeutralButtonShow=false;
    private boolean isEditText=false;
    private boolean isListView1=false;
    private boolean isListView2=false;
    private boolean isListView3=false;
    private boolean isListView4=false;
    //private int postion;
    private View mView;
    private EditText mEdit;
    private ListView ChoiceList;
    private String[] items;
    private List<String> arrayList;
    private int defItem;
    private int choiceMode;
    private MiuiDialog.Builder mBuilder;
    private View.OnClickListener  PositiveListener,NegativeListener,NeutralListener;
    private OnItemClickListener mlistener;
    
    public MiuiDialog show(){
        mBuilder=new Builder();
        if(!mDialog.isShowing()){
            mDialog.show();
        }
        return this;
    }
    public MiuiDialog dismiss(){
        mDialog.dismiss();
        return this;
    }
    public MiuiDialog cancel(){
        mDialog.cancel();
        return this;
    }
    public MiuiDialog(Context context){
        this.mContext=context;
        this.defStyle=R.style.CustomDialog;
    }
    private class Builder {
        private Button PositiveButton;
        private Button NegativeButton;
        private Button NeutralButton; 
        private TextView mMessage,mTitle;
        private LinearLayout mButton;
        private FrameLayout mFrameLayout;
        private ListView mChoiceList;
        private ListView mList;
		private View v1view;
		private View neutralview;
		private View positiveview;

        private Builder() {
            mDialog=new Dialog(mContext,defStyle);
//            mDialog.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND, 
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            mDialog.setContentView(R.layout.custom_miui_dialog_layout);
			mDialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
            Window dialogWindow = mDialog.getWindow();
            DisplayMetrics d = mContext.getResources().getDisplayMetrics();
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); 
			mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialogWindow.setGravity(Gravity.CENTER|Gravity.BOTTOM);
			p.x = 0;//设置x坐标
			p.y = 20;//设置y坐标
            p.width =(int)(d.widthPixels*0.95);
            dialogWindow.setAttributes(p);
            setCustomView(mDialog);
        }
        private void setCustomView(Dialog dialog){
            mTitle=(TextView)dialog.findViewById(R.id.custom_miui_dialog_title);
			v1view =(View)dialog.findViewById(R.id.v1);
			neutralview =(View)dialog.findViewById(R.id.neutral_view);
			positiveview =(View)dialog.findViewById(R.id.positive_view);
			PositiveButton=(Button)dialog.findViewById(R.id.positive_button);
            NegativeButton=(Button)dialog.findViewById(R.id.negative_button);
            NeutralButton =(Button)dialog.findViewById(R.id.neutral_button); 
            mButton=(LinearLayout)dialog.findViewById(R.id.custom_miui_dialog_button);
            mFrameLayout=(FrameLayout)dialog.findViewById(R.id.custom_miui_dialog_view);

            ScrollView scrollView=new ScrollView(mContext);
            mMessage=new TextView(mContext);
            mMessage.setPadding(50,0,50,100);
            mMessage.setTextColor(Color.parseColor("#5B5A5A"));
            mMessage.setTextSize(16);
            mMessage.setLineSpacing(1,1.3f);
            scrollView.addView(mMessage);
            mFrameLayout.addView(scrollView);
            
            if(isEditText==true){
                LinearLayout layout=new LinearLayout(mContext);
                layout.setPadding(80,40,80,140);
                mEdit=new EditText(mContext);
                mEdit.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
                mEdit.setBackgroundResource(R.drawable.edit_text_bg);
                mEdit.setTextSize(15.5f);
                mEdit.setMaxLines(3);
                if(EditTextId==0&&getEditText()!=null){
                    mEdit.setText(setEditText);
                }
                else if(EditTextId!=0&&getEditText()==null){
                    mEdit.setText(EditTextId);
                }
                if(hintId==0&&hint!=null){
                    mEdit.setHint(hint);
                }
                else if(hintId!=0&&hint==null){
                    mEdit.setHint(hintId);
                }
                layout.addView(mEdit);
                mFrameLayout.addView(layout);
            }
            if(isListView1==true||isListView2==true){
                LinearLayout l=new LinearLayout(mContext);
                l.setOrientation(LinearLayout.VERTICAL);
                View v=new View(mContext);
                v.setBackgroundColor(Color.parseColor("#dcdcdc"));
                v.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,2));
                l.addView(v);
                mChoiceList=new ListView(mContext);
                if(isListView1==true&&isListView2==false){
                    mChoiceList.setAdapter(new ListViewChoiceAdapter(mContext,items));
               }
               else if(isListView1==false&&isListView2==true){
                   mChoiceList.setAdapter(new ListViewChoiceAdapter(mContext,arrayList));
               }
                mChoiceList.setChoiceMode(choiceMode);
                mChoiceList.setItemChecked(defItem,true);
                mChoiceList.setOnItemClickListener(mlistener);
                ChoiceList=mChoiceList;
                //values= mChoiceList.isItemChecked(postion);
                l.addView(mChoiceList);
                mFrameLayout.addView(l);
             }
            if(isListView3==true||isListView4==true){
                LinearLayout l=new LinearLayout(mContext);
                l.setOrientation(LinearLayout.VERTICAL);
                View v=new View(mContext);
                v.setBackgroundColor(Color.parseColor("#dcdcdc"));
                v.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,2));
                l.addView(v);
                mList=new ListView(mContext);
                if(isListView3==true&&isListView4==false){
                    mList.setAdapter(new ArrayAdapter<String>(mContext,R.layout.single_click_item,R.id.single_click_item_text,items));
                }
                else if(isListView3==false&&isListView4==true){
                    mList.setAdapter(new ArrayAdapter<String>(mContext,R.layout.single_click_item,R.id.single_click_item_text,arrayList));
                }
                mList.setOnItemClickListener(mlistener);
                l.addView(mList);
                mFrameLayout.addView(l);
            }
            if(mView!=null){
                mView.setPadding(50,0,50,80);
                mFrameLayout.addView(mView);
            }
            if(titleId==0&&titleText==null){
                mTitle.setVisibility(View.GONE);
            }
            else if(titleId!=0&&titleText==null){
                mTitle.setText(titleId);
            }
            else if(titleId==0&&titleText!=null){
                mTitle.setText(titleText);
            }
            
            if(messageId==0&&messageText==null){
                mMessage.setVisibility(View.GONE);
            }
            else if(messageId!=0&&messageText==null){
                mMessage.setText(messageId);
            }
            else if(messageId==0&&messageText!=null){
                mMessage.setText(messageText);
            }
            
            if(PositiveId!=0&&PositiveText==null){
            PositiveButton.setText(PositiveId);
            }
            else if(PositiveId==0&&PositiveText!=null){
                PositiveButton.setText(PositiveText);
            }
            
            if(NegativeId!=0&&NegativeText==null){
                NegativeButton.setText(NegativeId);
            }
            else if(NegativeId==0&&NegativeText!=null){
                NegativeButton.setText(NegativeText);
            }
            
            if(NeutralId!=0&&NeutralText==null){
                NeutralButton.setText(NegativeId);
            }
            else if(NeutralId==0&&NeutralText!=null){
                NeutralButton.setText(NeutralText);
            }
            
            if(PositiveListener!=null){
                PositiveButton.setOnClickListener(PositiveListener);
            }
            else{
                PositiveButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            if(NegativeListener!=null){
                NegativeButton.setOnClickListener(NegativeListener);
            }
            else{
                NegativeButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            if(NeutralListener!=null){
                NeutralButton.setOnClickListener(NeutralListener);
            }
            else{
                NeutralButton.setOnClickListener(new OnDialogButtonClickListener());
            }
            viewButton();
            
        }
        private void viewButton(){
            int button_shape_bg=R.drawable.button_shape_bg;
            int button_shape_left=R.drawable.button_shape_left;
            int button_shape_center=R.drawable.button_shape_center;
            int button_shape_right=R.drawable.button_shape_right;

            if(isPositiveButtonShow==true&&isNegativeButtonShow==false&&isNeutralButtonShow==false){
                PositiveButton.setBackgroundResource(button_shape_bg);
                NegativeButton.setVisibility(View.GONE);
                NeutralButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
            }
            if(isPositiveButtonShow==false&&isNegativeButtonShow==true&&isNeutralButtonShow==false){
                PositiveButton.setVisibility(View.GONE);
                NegativeButton.setBackgroundResource(button_shape_bg);
                NeutralButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
            }
            if(isPositiveButtonShow==false&&isNegativeButtonShow==false&&isNeutralButtonShow==true){
                PositiveButton.setVisibility(View.GONE);
                NegativeButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NeutralButton.setBackgroundResource(button_shape_bg);
            }
            if(isPositiveButtonShow==true&&isNegativeButtonShow==true&&isNeutralButtonShow==false){
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setBackgroundResource(button_shape_left);
                NeutralButton.setVisibility(View.GONE);
				neutralview.setVisibility(View.GONE);
            }
            if(isPositiveButtonShow==true&&isNegativeButtonShow==false&&isNeutralButtonShow==true){
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if(isPositiveButtonShow==false&&isNegativeButtonShow==true&&isNeutralButtonShow==true){
                PositiveButton.setVisibility(View.GONE);
				positiveview.setVisibility(View.GONE);
                NegativeButton.setBackgroundResource(button_shape_right);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if(isPositiveButtonShow==true&&isNegativeButtonShow==true&&isNeutralButtonShow==true){
                PositiveButton.setBackgroundResource(button_shape_right);
                NegativeButton.setBackgroundResource(button_shape_center);
                NeutralButton.setBackgroundResource(button_shape_left);
            }
            if(isPositiveButtonShow==false&&isNegativeButtonShow==false&&isNeutralButtonShow==false){
                mButton.setVisibility(View.GONE);
            }
        }
     }
    public MiuiDialog setTitle(CharSequence text){
        this.titleText=text;
        return this;
    }
    public MiuiDialog setTitle(int resourcesId){
        this.titleId=resourcesId;
        return this;
    }
    public MiuiDialog setMessage(CharSequence text){
        this.messageText=text;
        return this;
    }
    public MiuiDialog setMessage(int resourcesId){
        this.messageId=resourcesId;
        return this;
    }
    public MiuiDialog setView(View view){
        this.mView=view;
        return this;
    }
    public MiuiDialog setEditText(CharSequence defaultText,int hintId){
        this.setEditText=defaultText;
        this.hintId=hintId;
        this.isEditText=true;
        return this;
    }
    public MiuiDialog setEditText(int defaultTextId,CharSequence hint){
        this.EditTextId=defaultTextId;
        this.hint=hint;
        this.isEditText=true;
        return this;
    }
    public MiuiDialog setEditText(CharSequence defaultText,CharSequence hint){
        this.setEditText=defaultText;
        this.hint=hint;
        this.isEditText=true;
        return this;
    }
    public MiuiDialog setEditText(int defaultTextId,int hintId){
        this.EditTextId=defaultTextId;
        this.hintId=hintId;
        this.isEditText=true;
        return this;
    }
    public String getEditText(){
        return mEdit.getText().toString();
    }
    public MiuiDialog setSingleChoiceItems(int defaultItem,String[] items,OnItemClickListener listener){
        this.defItem=defaultItem;
        this.items=items;
        this.mlistener=listener;
        this.isListView1=true;
        this.choiceMode=ListView.CHOICE_MODE_SINGLE;
        return this;
    }
    public MiuiDialog setSingleChoiceItems(int defaultItem,List<String> arrayList,OnItemClickListener listener){
        this.defItem=defaultItem;
        this.arrayList=arrayList;
        this.mlistener=listener;
        this.isListView2=true;
        this.choiceMode=ListView.CHOICE_MODE_SINGLE;
        return this;
    }
    public MiuiDialog setMultiChoiceItems(int defaultItem,String[] items,OnItemClickListener listener){
        this.defItem=defaultItem;
        this.items=items;
        this.mlistener=listener;
        this.isListView1=true;
        this.choiceMode=ListView.CHOICE_MODE_MULTIPLE;
        return this;
    }
    public MiuiDialog setMultiChoiceItems(int defaultItem,List<String> arrayList,OnItemClickListener listener){
        this.defItem=defaultItem;
        this.arrayList=arrayList;
        this.mlistener=listener;
        this.isListView2=true;
        this.choiceMode=ListView.CHOICE_MODE_MULTIPLE;
        return this;
    }
    public MiuiDialog setSingleClickItem(String[] items,OnItemClickListener listener){
        this.items=items;
        this.mlistener=listener;
        this.isListView3=true;
        return this;
    }
    public MiuiDialog setSingleClickItem(List<String> arrayList,OnItemClickListener listener){
        this.arrayList=arrayList;
        this.mlistener=listener;
        this.isListView4=true;
        return this;
    }
    public boolean isItemChecked(int postion){
       // this.postion=postion;
        return !ChoiceList.isItemChecked(postion);
    }
    public MiuiDialog setPositiveButton(int resourcesId, final View.OnClickListener listener) {
        this.PositiveId=resourcesId;
        this.PositiveListener=listener;
        this.isPositiveButtonShow=true;
        return this;
    }
    public MiuiDialog setNegativeButton(int resourcesId, final View.OnClickListener listener) {
        this.NegativeId = resourcesId;
        this.NegativeListener = listener;
        this.isNegativeButtonShow=true;
        return this;
    }
    public MiuiDialog setNeutralButton(int resourcesId,final View.OnClickListener listener){
        this.NeutralId=resourcesId;
        this.NeutralListener=listener;
        this.isNeutralButtonShow=true;
        return this;
    }
    public MiuiDialog setPositiveButton(CharSequence text, final View.OnClickListener listener) {
        this.PositiveText=text;
        this.PositiveListener=listener;
        this.isPositiveButtonShow=true;
        return this;
    }
    public MiuiDialog setNegativeButton(CharSequence text, final View.OnClickListener listener) {
        this.NegativeText = text;
        this.NegativeListener = listener;
        this.isNegativeButtonShow=true;
        return this;
    }
    public MiuiDialog setNeutralButton(CharSequence text,final View.OnClickListener listener){
        this.NeutralText=text;
        this.NeutralListener=listener;
        this.isNeutralButtonShow=true;
        return this;
    }
    private class OnDialogButtonClickListener implements OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
            // TODO: Implement this method
            mDialog.dismiss();
        }
    }
}

