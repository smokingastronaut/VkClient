package ru.khannanovayrat.vkclient.network.wall;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.khannanovayrat.vkclient.network.wall.response.VkWallPost;
import ru.khannanovayrat.vkclient.network.wall.response.VkWallPostResponse;

/**
 * @author Khannanov Ayrat { 06.11.2017 }.
 */

public class VkWallPostResponseDeserializer implements JsonDeserializer<VkWallPostResponse> {

    @Override
    public VkWallPostResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        int count = jsonArray.get(0).getAsInt();
        List<VkWallPost> posts = new ArrayList<>();
        jsonArray.remove(0);
        for (JsonElement jsonElement : jsonArray) {
            VkWallPost wallPost = new VkWallPost();
            JsonObject targetObject = jsonElement.getAsJsonObject();
            int fromId = targetObject.getAsJsonPrimitive("from_id").getAsInt();
            wallPost.setFromId(fromId);
            int date = targetObject.getAsJsonPrimitive("date").getAsInt();
            wallPost.setDate(date);
            String text = targetObject.getAsJsonPrimitive("text").getAsString();
            wallPost.setText(text);
            posts.add(wallPost);
        }
        VkWallPostResponse wallPostResponse = new VkWallPostResponse();
        wallPostResponse.setCount(count);
        wallPostResponse.setPosts(posts);
        return wallPostResponse;
    }
}
