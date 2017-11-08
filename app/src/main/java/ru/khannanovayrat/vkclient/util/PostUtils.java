package ru.khannanovayrat.vkclient.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.khannanovayrat.vkclient.network.attachment.VkAttachment;
import ru.khannanovayrat.vkclient.network.attachment.VkPhoto;
import ru.khannanovayrat.vkclient.network.attachment.VkPhotoAttachment;

/**
 * @author Khannanov Ayrat { 08.11.2017 }.
 */

public final class PostUtils {

    public static String getFormattedDateTime(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = new Date(millis);
        return sdf.format(date);
    }

    public static String selectNonNullUrl(VkPhoto vkPhoto) {
        if (vkPhoto == null) {
            return null;
        }
        String url = vkPhoto.getPhoto1280();
        if (TextUtils.isEmpty(url)) {
            url = vkPhoto.getPhoto807();
            if (TextUtils.isEmpty(url)) {
                url = vkPhoto.getPhoto604();
            }
        }
        return url;
    }

    public static List<VkPhotoAttachment> getPhotoAttachments(List<VkAttachment> attachments) {
        if (attachments == null) {
            return null;
        }
        List<VkPhotoAttachment> photoAttachments = new ArrayList<>();
        for (VkAttachment attachment : attachments) {
            if (attachment instanceof VkPhotoAttachment) {
                photoAttachments.add((VkPhotoAttachment) attachment);
            }
        }
        return photoAttachments;
    }
}
