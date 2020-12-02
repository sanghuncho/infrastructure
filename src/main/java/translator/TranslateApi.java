package translator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.*;

public class TranslateApi {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Translate translate = TranslateOptions
                .newBuilder()
                .setCredentials(
                    ServiceAccountCredentials
                                .fromStream(new FileInputStream(
                                        "C:/Users/sanghuncho/Documents/Google_Cloud/gkoo-translator-68c45ea2878f.json")))
                .build().getService();
        
        Translation translation =
                translate.translate(
                    "Neutral Augencreme",
                    Translate.TranslateOption.sourceLanguage("de"),
                    Translate.TranslateOption.targetLanguage("ko"),
                    Translate.TranslateOption.model("nmt"));
        
        System.out.printf("Translated Text:\n\t%s\n", translation.getTranslatedText());
    }
}
