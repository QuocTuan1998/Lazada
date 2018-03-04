package com.example.quoctuan.lazada.presenter.home;

import com.example.quoctuan.lazada.model.home.DownloadData;
import com.example.quoctuan.lazada.view.home.ViewDownloadDataImp;

import java.util.concurrent.ExecutionException;

/**
 * Created by quoctuan on 04/03/2018.
 */

public class PresenterDownloadDataLogic implements PresenterDownloadDataImp {
    ViewDownloadDataImp viewDownloadDataImp;
    String str_url = "";
    String parent_code = "";
    public PresenterDownloadDataLogic(ViewDownloadDataImp viewDownloadDataImp, String str_url,String parent_code){
        this.viewDownloadDataImp = viewDownloadDataImp;
        this.str_url = str_url;
        this.parent_code = parent_code;
    }
    @Override
    public void downloadData() {
        DownloadData getData = new DownloadData();
        getData.execute(str_url,parent_code);
        try {
            String data = getData.get();
            if (data == null || data.equals("")) viewDownloadDataImp.DownloadDataFailure();

            viewDownloadDataImp.DownloadDataSuccess(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
