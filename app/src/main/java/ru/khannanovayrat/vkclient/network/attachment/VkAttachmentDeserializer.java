package ru.khannanovayrat.vkclient.network.attachment;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author Khannanov Ayrat { 08.11.2017 }.
 */

public class VkAttachmentDeserializer implements JsonDeserializer<VkAttachment> {

    @Override
    public VkAttachment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Attachment attachmentType = Attachment.valueOf(jsonObject.get("type").getAsString().toUpperCase());
        JsonElement photoElement = jsonObject.get("photo");
        VkAttachment vkAttachment;
        if (attachmentType == Attachment.PHOTO) {
            vkAttachment = new VkPhotoAttachment();
            Gson gson = new Gson();
            ((VkPhotoAttachment) vkAttachment).setPhoto(gson.fromJson(photoElement, VkPhoto.class));
        } else {
            vkAttachment = new VkAttachment();
        }
        vkAttachment.setType(attachmentType);
        return vkAttachment;
    }
}
