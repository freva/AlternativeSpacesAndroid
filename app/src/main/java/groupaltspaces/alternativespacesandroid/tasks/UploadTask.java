package groupaltspaces.alternativespacesandroid.tasks;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import groupaltspaces.alternativespacesandroid.util.MultipartUtility;

public class UploadTask extends AsyncTask<Void, Void, List<String>> {
    private static final String requestURL = "http://folk.ntnu.no/valerijf/div/AlternativeSpaces/source/backend/forms/uploadphoto.php";
    private String title;
    private String interests;
    private String description;
    private File image;
    private Callback callback;


    public UploadTask(String title, String interests, String description, File image, Callback callback){
        this.title = title;
        this.interests = interests;
        this.description = description;
        this.image = image;
        this.callback = callback;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        List<String> response = null;
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, "UTF-8");

            multipart.addFormField("description", this.description);
            multipart.addFormField("title", this.title);
            multipart.addFormField("interests", this.interests);
            multipart.addFilePart("image", image);
            response = multipart.finish();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(List<String> response) {
        super.onPostExecute(response);
        System.out.println(response.get(0));

        try {
            JSONObject json = new JSONObject(response.get(0));
            boolean status = json.getBoolean("success");

            JSONArray jsonArray = json.getJSONArray("response");
            List<String> messages = new ArrayList<String>();
            for (int i = 0;i<jsonArray.length();i++) messages.add(jsonArray.getString(i));

            if (status) callback.onSuccess();
            else callback.onFail(messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}