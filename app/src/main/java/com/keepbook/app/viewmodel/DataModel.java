package com.keepbook.app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.keepbook.app.model.vo.BillVO;

import java.util.Map;

//ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据
/*
* 如果系统销毁或重新创建界面控制器，则存储在其中的任何瞬态界面相关数据都会丢失。
* 例如，应用可能会在它的某个 Activity 中包含用户列表。为配置更改重新创建 Activity 后，
* 新 Activity 必须重新提取用户列表。对于简单的数据，Activity 可以使用 onSaveInstanceState() 方法从 onCreate() 中的捆绑包恢复其数据，
* 但此方法仅适合可以序列化再反序列化的少量数据，而不适合数量可能较大的数据，如用户列表或位图。
* */
public class DataModel extends ViewModel {
    private MutableLiveData<Map<String,BillVO>> data = new MutableLiveData<>();
    private MutableLiveData<Integer> pos = new MutableLiveData<>();

    public MutableLiveData<Integer> getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos.setValue(pos);
    }

    public MutableLiveData<Map<String,BillVO>> getData() {
        return data;
    }

    public void setData(Map<String,BillVO>data) {
        this.data.setValue(data);;
    }

}
