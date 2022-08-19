package org.example.user.pojo.selector;

import org.example.user.annotation.ExcelDynamicSelect;

public class EffectDeviceTypeSelector implements ExcelDynamicSelect {
    @Override
    public String[] getSource() {
        //TODO 设备类型列表

        return new String[0];
    }
}
