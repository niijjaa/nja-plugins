package com.lucidplugins.api.util;

import net.runelite.api.widgets.Widget;
import net.unethicalite.api.packets.MousePackets;
import net.unethicalite.api.packets.WidgetPackets;
import net.unethicalite.client.Static;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogUtils
{
    public static void queueResumePauseDialog(int widgetId, int childId)
    {
        MousePackets.queueClickPacket();
        WidgetPackets.queueResumePauseWidgetPacket(widgetId, childId);
    }

    public static List<String> getOptions()
    {
        Widget widget = Static.getClient().getWidget(219, 1);
        if (widget == null || widget.isSelfHidden())
        {
            return Collections.emptyList();
        }
        else
        {
            List<String> out = new ArrayList();
            Widget[] children = widget.getChildren();
            if (children == null)
            {
                return out;
            }
            else
            {
                for (int i = 1; i < children.length; ++i)
                {
                    if (children[i] != null && !children[i].getText().isBlank())
                    {
                        out.add(children[i].getText());
                    }
                }

                return out;
            }
        }
    }

    public static int getOptionIndex(String option)
    {
        if (getOptions().isEmpty())
        {
            return -1;
        }

        List<String> options = getOptions();
        for (int index = 0; index < options.size(); index++)
        {
            if (options.get(index).contains(option))
            {
                return index + 1;
            }
        }

        return -1;
    }
}
