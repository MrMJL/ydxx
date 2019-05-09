package org.ydxx.activitys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import org.ydxx.adapters.JxzyItemAdapter;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.ResultMessage;
import org.ydxx.entity.Xsdy;

public class JxzyActivity extends BaseActivity {
    private static final String[] operationTypes = new String[] { "订阅" };
    private static final String[] operationTypes2 = new String[] { "修改", "删除" };
    private ImageView  btn_jxzy_add;
    private ImageView  btn_jxzy_return;
    private JxzyItemAdapter jxzyItemAdapter;
    private ListView lv_jxzy;

    private ProgressDialog progressDialog = null;
    private Handler handler;
    private Declare declare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jxzy);
        declare = (Declare) this.getApplicationContext();

        btn_jxzy_return = (ImageView) findViewById(R.id.btn_jxzy_return);
        btn_jxzy_add = (ImageView) findViewById(R.id.btn_jxzy_add);
        btn_jxzy_return.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JxzyActivity.this, MainActivity.class);
                startActivity(intent);
                JxzyActivity.this.finish();
            }
        });
        if (declare.getUser().getType().equals("3")) {
            btn_jxzy_add.setVisibility(View.GONE);
        }else {
            btn_jxzy_add.setVisibility(View.VISIBLE);
        }
        btn_jxzy_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JxzyActivity.this, MessFAddActivity.class);
                startActivity(intent);
                JxzyActivity.this.finish();
            }
        });

        lv_jxzy = (ListView) findViewById(R.id.lv_jxzy);
        jxzyItemAdapter = new JxzyItemAdapter(this.getBaseContext());
        lv_jxzy.setAdapter(jxzyItemAdapter);
        lv_jxzy.setOnItemLongClickListener(new ItemOnItemLongClickListener());

        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (progressDialog.isShowing()) {
                    progressDialog.cancel();
                }
                ResultMessage resultMessage = (ResultMessage) msg.obj;
                if (resultMessage.isStatus() && msg.what == 1) {
                    List<Jxzy> jxzys = (List<Jxzy>) resultMessage.getItems();
                    if (!jxzys.isEmpty()) {
                        jxzyItemAdapter.setItems(jxzys);
                    } else {
                        jxzyItemAdapter.setItems(new ArrayList<Jxzy>());
                        Toast.makeText(JxzyActivity.this, "暂时没有课程资源", Toast.LENGTH_SHORT).show();
                    }
                    jxzyItemAdapter.notifyDataSetChanged();
                } else if (resultMessage.isStatus() && msg.what == 2) {
                    //					progressDialog = ProgressDialog.show(JxzyActivity.this, "", "查找中.....", true);
                    //					progressDialog.setCancelable(true);
                    //					new Thread(new findJxzyRunable()).start();
                    Toast.makeText(JxzyActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                } else if (resultMessage.isStatus() && msg.what == 3) {
                    new Thread(new findJxzyRunable()).start();
                    Toast.makeText(JxzyActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                } else if (resultMessage.isStatus() && msg.what == 4) {
                    new Thread(new findJxzyRunable()).start();
                    Toast.makeText(JxzyActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(JxzyActivity.this, resultMessage.getMessage(),
                        Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    class ItemOnItemLongClickListener implements OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            final Jxzy jxzy = (Jxzy) parent.getAdapter().getItem(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(JxzyActivity.this);
            builder.setTitle("选择操作");
            if (declare.getUser().getType().equals("3")) {
                builder.setItems(operationTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        switch (which) {
                            case 0:
                                progressDialog =
                                    ProgressDialog.show(JxzyActivity.this, "", "订阅中.....", true);
                                progressDialog.setCancelable(true);
                                Xsdy xsdy = new Xsdy();
                                xsdy.setExt1(jxzy.getExt1());
                                xsdy.setExt2(jxzy.getLsxm());
                                xsdy.setJxzyid(jxzy.getId() + "");
                                xsdy.setJxzymc(jxzy.getKcmc());
                                xsdy.setXsid(declare.getUser().getId() + "");
                                xsdy.setXsmc(declare.getUser().getUsername());
                                new Thread(new addXsdyRunable(xsdy)).start();
                                break;
                            default:
                                break;
                        }
                    }
                });
            } else {
                builder.setItems(operationTypes2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        switch (which) {
                            case 0:
                                Intent intent =
                                    new Intent(JxzyActivity.this, MessFAddActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("jxzy", jxzy);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                JxzyActivity.this.finish();
                                break;
                            case 1:
                                progressDialog =
                                    ProgressDialog.show(JxzyActivity.this, "", "修改中.....", true);
                                progressDialog.setCancelable(true);
                                new Thread(new deleteJxzyRunable(jxzy)).start();
                                break;
                            default:
                                break;
                        }
                    }
                });
            }

            builder.show();
            return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog = ProgressDialog.show(JxzyActivity.this, "", "查找中.....", true);
        progressDialog.setCancelable(true);

        new Thread(new findJxzyRunable()).start();
    }

    class findJxzyRunable implements Runnable {
        public findJxzyRunable() {
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 1;
            List<Jxzy> allJxzy =
                LocalDataSource.getInstance(JxzyActivity.this).getJxzyDao().getAllJxzy();
            Log.e("TAG", "run: " + allJxzy.size());
            resultMessage.setStatus(true);
            resultMessage.setItems(allJxzy);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(JxzyActivity.this, MainActivity.class);
        startActivity(intent);
        JxzyActivity.this.finish();
    }

    class deleteJxzyRunable implements Runnable {
        private Jxzy jxzy;

        public deleteJxzyRunable(Jxzy jxzy) {
            this.jxzy = jxzy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 4;
            LocalDataSource.getInstance(JxzyActivity.this).getJxzyDao().deleteJxzy(jxzy);
            resultMessage.setStatus(true);
            resultMessage.setMessage("删除成功！");
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }

    class addXsdyRunable implements Runnable {
        private Xsdy xsdy;

        public addXsdyRunable(Xsdy xsdy) {
            this.xsdy = xsdy;
        }

        @Override
        public void run() {
            Message message = new Message();
            ResultMessage resultMessage = new ResultMessage();
            message.what = 2;
            LocalDataSource.getInstance(JxzyActivity.this).getXsdyDao().insertXsdy(xsdy);
            resultMessage.setMessage("订阅成功！");
            resultMessage.setStatus(true);
            message.obj = resultMessage;
            handler.sendMessage(message);
        }
    }
}
