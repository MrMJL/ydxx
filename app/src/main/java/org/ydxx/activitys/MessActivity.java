package org.ydxx.activitys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.ydxx.R;
import org.ydxx.adapters.MessItemAdapter;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Mess;
import org.ydxx.entity.ResultMessage;

public class MessActivity extends BaseActivity {
    private static final String[] operationTypes = new String[] { "回复" };
    private ImageView btn_mess_return, btn_mess_add;
    private MessItemAdapter messItemAdapter;
    private ListView lv_mess;

    private ProgressDialog progressDialog = null;
    private Handler handler;
    private Declare declare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
        declare = (Declare) this.getApplicationContext();

        btn_mess_return = (ImageView) findViewById(R.id.btn_mess_return);
        btn_mess_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessActivity.this, MainActivity.class);
                startActivity(intent);
                MessActivity.this.finish();
            }
        });

        btn_mess_add = (ImageView) findViewById(R.id.btn_mess_add);
        btn_mess_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessActivity.this, MessFAddActivity.class);
                intent.putExtra("isThree",true);
                startActivity(intent);
                MessActivity.this.finish();
            }
        });

        lv_mess = (ListView) findViewById(R.id.lv_mess);
        messItemAdapter = new MessItemAdapter(this.getBaseContext());
        lv_mess.setAdapter(messItemAdapter);
        lv_mess.setOnItemLongClickListener(new ItemOnItemLongClickListener());

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                ResultMessage resultMessage = (ResultMessage) msg.obj;
                if (resultMessage.isStatus() && msg.what == 1) {
                    List<Mess> messes = (List<Mess>) resultMessage.getItems();
                    if (!messes.isEmpty()) {
                        messItemAdapter.setItems(messes);
                    } else {
                        messItemAdapter.setItems(new ArrayList<Mess>());
                    }
                    messItemAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MessActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    class ItemOnItemLongClickListener implements OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            final Mess mess = (Mess) parent.getAdapter().getItem(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(MessActivity.this);
            builder.setTitle("选择操作");
            builder.setItems(operationTypes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    switch (which) {
                        case 0:
                            Intent intent = new Intent(MessActivity.this, MessTAddActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("mess", mess);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            MessActivity.this.finish();

                            break;
                        default:
                            break;
                    }
                }
            });
            builder.show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MessActivity.this, MainActivity.class);
        startActivity(intent);
        MessActivity.this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog = ProgressDialog.show(MessActivity.this, "", "查找中.....", true);
        progressDialog.setCancelable(true);

        new Thread(new findMessRunable()).start();
    }

    class findMessRunable implements Runnable {
        public findMessRunable() {
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
            try {
                //resultMessage = declare.getBusiness().findMess();
            } catch (RuntimeException e) {
                e.printStackTrace();
                resultMessage.setMessage("系统发生异常！");
                resultMessage.setStatus(false);
            } catch (Exception e) {
                e.printStackTrace();
                resultMessage.setMessage("系统发生异常！");
                resultMessage.setStatus(false);
            }
            //List<Mess> data = new ArrayList<>();
            //for (int i = 0; i < 5; i++) {
            //    Mess mess = new Mess();
            //    mess.setFusername("明明 No." + i);
            //    mess.setFmessage("毕业设计好过吗");
            //    //mess.setTusername("Tusername" + i);
            //    //mess.setTmessage("Tmessage" + i);
            //    data.add(mess);
            //}
            //resultMessage.setItems(data);
            List<Mess> allMess =
                LocalDataSource.getInstance(MessActivity.this).getMessDao().getAllMess();
            resultMessage.setItems(allMess);
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }
}
