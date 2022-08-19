package org.example.user.pojo.selector;


import org.example.user.annotation.ExcelDynamicSelect;

public class EffectTypeSelector implements ExcelDynamicSelect {
    @Override
    public String[] getSource() {
        String [] result = {"rgbic","rgb","diy","涂鸦"};
        return result;
    }
}
