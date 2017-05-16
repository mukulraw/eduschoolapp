package eskool.com.eskoolapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by User on 3/27/2017.
 */

public class receive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            Intent pushIntent = new Intent(context, NotifyService.class);
            context.startService(pushIntent);
        }
    }
}