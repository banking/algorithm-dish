package binary.config;

/**
 * Created by banking on 16/6/10.
 */

import binary.config.ConfigField;
import java.lang.reflect.Field;

/**
 * Created by banking on 16/5/24.
 * 参数名可以随意定义
 */
public class BusinessConfig {

    @ConfigField(tag = 0)
    private boolean configCanEat = false;

    @ConfigField(tag = 1)
    private boolean configDoorOpend = false;

    @ConfigField(tag = 2)
    private boolean configIsDied = false;

    @ConfigField(tag = 3)
    private boolean configCoolFace = false;

    @ConfigField(tag = 4)
    private boolean configShowEmoji = false;


    /**
     * 使用二进制int初始化config;参数格式：0b二进制.
     * @ConfigField(tag = x)的属性赋值初始化赋值在rawConfigInt的第x位
     * 比如例子中configIsDied初始化值依赖于第2位数字是0还是1，0则为false,1则为true.
     * @param rawConfigInt
     */
    public static BusinessConfig constructBusinessConfig(int rawConfigInt){

        BusinessConfig instance = null;
        try{
            Field[] fields = Class.forName(BusinessConfig.class.getName()).getDeclaredFields();
            instance = (BusinessConfig)Class.forName(BusinessConfig.class.getName()).newInstance();
            for(int index = 0;index< fields.length;index++){
                fields[index].setAccessible(true);
                ConfigField configField = fields[index].getAnnotation(ConfigField.class);
                if(configField != null){
                    fields[index].set(instance,((1<<configField.tag()) & rawConfigInt) > 0);
                }
            }
        }catch (Exception e){

        }
        return instance;
    }


    @Override
    public String toString () {
        return "BusinessConfig{" +
                "configCanEat=" + configCanEat +
                ", configDoorOpend=" + configDoorOpend +
                ", configIsDied=" + configIsDied +
                ", configCoolFace=" + configCoolFace +
                ", configShowEmoji=" + configShowEmoji +
                '}';
    }
}

