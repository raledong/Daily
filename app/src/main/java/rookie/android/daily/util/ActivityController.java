package rookie.android.daily.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rale on 5/19/16.
 *
 * 实现对activity的的管理
 */
public class ActivityController {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
;
}
